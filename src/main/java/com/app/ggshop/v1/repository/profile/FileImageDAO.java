package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.dto.profile.FileImageDTO;
import com.app.ggshop.v1.dto.profile.ProfileImageDTO;
import com.app.ggshop.v1.mapper.profile.FileImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class FileImageDAO {
    private final FileImageMapper fileMapper;

    //    추가
    public void save(ProfileImageDTO profileImageDTO) {
        fileMapper.insertFile(profileImageDTO.tofileVO());
    }
}
