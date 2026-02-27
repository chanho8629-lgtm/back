package com.app.ggshop.v1.mapper;


import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.BoardVO;
import com.app.ggshop.v1.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    // 게시글 등록
    void insertBoard(BoardDTO boardDTO);
    List<BoardDTO> findAll();

    // 목록
    List<BoardDTO> selectAll(@Param("criteria") Criteria criteria,
                             @Param("filter") String filter,
                             @Param("keyword") String keyword);

    // 전체 개수
    int selectTotal(@Param("filter") String filter, @Param("keyword") String keyword);

    // 조회
    Optional<BoardDTO> selectById(Long id);

    // 수정
    void update(BoardVO boardVO);

    // 삭제
    void delete(Long id);

}
