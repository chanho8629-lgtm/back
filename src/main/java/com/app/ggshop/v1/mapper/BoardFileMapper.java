package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.BoardFileVO;
import com.app.ggshop.v1.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    //    추가
    public void insert(BoardFileVO boardFileVO);
    //    목록
    public List<BoardFileDTO> selectAllByBoardId(Long id);
    //    삭제
    public void delete(Long id);
    //    삭제(게시글 아이디)
    public void deleteByBoardId(Long id);

}
