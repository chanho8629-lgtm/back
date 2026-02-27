package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.BoardTagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardTagMapper {
    // 태그 등록
    void insertTag(BoardTagVO boardTagVO);

    //    목록
    public List<BoardTagVO> selectAllByBoardId(Long id);

    //    전체 태그(중복 없이)
    public List<String> selectAll();

    //    삭제
    public void delete(Long id);

    //    삭제(게시글 아이디)
    public void deleteByBoardId(Long id);
}