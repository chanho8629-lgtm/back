package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.enumeration.FileContentType;
import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.dto.profile.ProfileDTO;
import com.app.ggshop.v1.repository.profile.FileDAO;
import com.app.ggshop.v1.repository.profile.ProfileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ProfileService {
    private final FileDAO fileDAO;
    private final ProfileDAO profileDAO;

    public void changeProfileImage(ProfileDTO profileDTO, MultipartFile file) throws IOException {
        profileDAO.deleteByProfile(profileDTO.getMemberId());

        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        UUID uuid = UUID.randomUUID();
        profileDTO.setFilePath(todayPath);
        profileDTO.setFileSize(String.valueOf(file.getSize()));
        profileDTO.setFileOriginalName(file.getOriginalFilename());
        profileDTO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
        profileDTO.setFileContentType(file.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);

        FileVO fileVO = profileDTO.tofileVO();
        fileDAO.save(fileVO);

        profileDTO.setId(fileVO.getId());
        profileDAO.save(profileDTO.toMemberFileVO());

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(path, profileDTO.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    public ProfileDTO getProfile(Long memberId) {
        // 1. DAO를 통해 DB 정보를 가져옴 (보통 Mapper에서 조인 쿼리로 ProfileDTO에 담아올 거예요)
        Optional<ProfileDTO> profileDTO = profileDAO.findProfileByMemberId(memberId);

        // 2. [감지 포인트] 정보가 없거나 파일명이 비어있다면?
        if (profileDTO.isEmpty() || profileDTO.get().getFileName() == null) {
            // 새로 DTO를 만들어서 우리가 약속한 "디폴트 정보"를 강제로 박아줌
            profileDTO = Optional.of(new ProfileDTO());
            profileDTO.get().setFilePath("/image/profile/");      // C:/file/default
            profileDTO.get().setFileName("profile.jpg");  // profile.jpg
            profileDTO.get().setMemberId(memberId);
        }

        return profileDTO.orElse(null);
    }
}
