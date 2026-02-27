package com.app.ggshop.v1.repository.board;


import com.app.ggshop.v1.domain.BoardFileVO;
import com.app.ggshop.v1.dto.BoardFileDTO;
import com.app.ggshop.v1.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardFileDAO {
    private final BoardFileMapper boardFileMapper;


    //    추가
    public void save(BoardFileVO boardFileVO) {
        boardFileMapper.insert(boardFileVO);
    }
    //    목록
    public List<BoardFileDTO>  findAllByBoardId(Long id) {
        return boardFileMapper.selectAllByBoardId(id);
    }
    //    삭제
    public void delete(Long id){
        boardFileMapper.delete(id);
    }
}

