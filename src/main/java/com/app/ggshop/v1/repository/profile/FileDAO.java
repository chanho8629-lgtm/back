package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.dto.profile.ProfileDTO;
import com.app.ggshop.v1.mapper.profile.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    //    추가
    public void save(FileVO fileVO) {
        fileMapper.insertFile(fileVO);
    }

}
