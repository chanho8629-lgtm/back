package com.app.ggshop.v1.repository.board;


import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.BoardVO;
import com.app.ggshop.v1.dto.BoardDTO;
import com.app.ggshop.v1.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardDAO {

    private final BoardMapper boardMapper;

    public void save(BoardDTO boardDTO) {
        boardMapper.insertBoard(boardDTO);
    }

    // 전체 개수
    public int findTotal(String filter, String keyword){
        return boardMapper.selectTotal(filter, keyword);
    }

    // 목록
    public List<BoardDTO> findAll(Criteria criteria, String filter, String keyword){
        return boardMapper.selectAll(criteria, filter, keyword);
    }

    // 조회
    public Optional<BoardDTO> findById(Long id){
        return boardMapper.selectById(id);
    }

    // 수정
    public void setBoard(BoardVO boardVO){
        boardMapper.update(boardVO);
    }

    // 삭제
    public void delete(Long id){
        boardMapper.delete(id);
    }

}
