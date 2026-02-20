package com.app.ggshop.v1.repository.Board;


import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.BoardVO;
import com.app.ggshop.v1.dto.board.BoardDTO;
import com.app.ggshop.v1.mapper.board.BoardMapper;
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

    //    추가
    public void save(BoardDTO boardDTO) {
        boardMapper.insert(boardDTO);
    }

    //    목록
//    public List<BoardDTO> findAll(Criteria criteria, Search search){
//        return boardMapper.selectAll(criteria, search);
//    }

    //    전체 개수
//    public int findTotal(Search search){
//        return boardMapper.selectTotal(search);
//    }

    //    조회
//    public Optional<BoardDTO> findById(Long id){
//        return boardMapper.selectById(id);
//    }

    //    수정
    public void setPost(BoardVO boardVO){
        boardMapper.update(boardVO);
    }

    //    삭제
    public void delete(Long id){
        boardMapper.delete(id);
    }
}
