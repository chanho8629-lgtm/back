package com.app.ggshop.v1.mapper.profile;

import com.app.ggshop.v1.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileImageMapper {
//  파일 테이블에 파일 이미지(추가)
    public void insertFile(FileVO fileVO);
//    public Optional<FileDTO> selectActiveFile(Long id);
}
