package com.app.ggshop.v1.repository.board;

import com.app.ggshop.v1.domain.BoardTagVO;
import com.app.ggshop.v1.mapper.BoardTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final BoardTagMapper boardTagMapper;

    //    추가
    public void save(BoardTagVO boardTagVO) {
        boardTagMapper.insertTag(boardTagVO);
    }


    //    목록
    public List<BoardTagVO> findAllByBoardId(Long id){
        return boardTagMapper.selectAllByBoardId(id);
    }

    //    전체 태그(중복 없이)
    public List<String> findAll(){
        return boardTagMapper.selectAll();
    }


    //    삭제
    public void delete(Long id){
        boardTagMapper.delete(id);
    }

    //    삭제(게시글 아이디)
    public void deleteByBoardId(Long id){
        boardTagMapper.deleteByBoardId(id);
    }
}











