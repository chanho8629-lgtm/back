package com.app.ggshop.v1.mapper.board;

import com.app.ggshop.v1.domain.BoardVO;
import com.app.ggshop.v1.dto.board.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    //    추가
    public void insert(BoardDTO boardDTO);
    //    목록
//    public List<BoardDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);
    //    전체 개수
//    public int selectTotal(@Param("search") Search search);
    //    조회
//    public Optional<BoardDTO> selectById(Long id);
    //    수정
    public void update(BoardVO boardDTO);
    //    삭제
    public void delete(Long id);
}

