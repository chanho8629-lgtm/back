package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FileMapper {
    // 파일 등록
    void insertFile(FileDTO fileDTO);

    // 게시글-파일 연결
    void insertBoardFile(@Param("fileId") Long fileId, @Param("boardId") Long boardId);

    // 게시글의 파일 목록 조회
    List<FileDTO> selectFilesByBoardId(Long boardId);

    //    삭제
    public void delete(Long id);
    //    조회
    public Optional<FileVO> selectById(Long id);
}
