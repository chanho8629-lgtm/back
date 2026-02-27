package com.app.ggshop.v1.repository.board;

import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.dto.FileDTO;
import com.app.ggshop.v1.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    //    추가
    public void save(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

    //    삭제
    public void delete(Long id) {
        fileMapper.delete(id);
    }

    //    조회
    public Optional<FileVO> findById(Long id){
        return fileMapper.selectById(id);
    }
}
