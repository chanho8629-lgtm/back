package com.app.ggshop.v1.repository.board;

import com.app.ggshop.v1.domain.CommentVO;
import com.app.ggshop.v1.dto.CommentDTO;
import com.app.ggshop.v1.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDAO {
    private final CommentMapper commentMapper;

    public void save(CommentVO commentVO) {
        commentMapper.insert(commentVO);
    }

    public List<CommentDTO> findAllByBoardId(Long boardId) {
        return commentMapper.selectAllByBoardId(boardId);
    }

    public Optional<CommentDTO> findById(Long id) {
        return commentMapper.selectById(id);
    }

    public void setContent(Long id, String commentContent) {
        commentMapper.updateContent(id, commentContent);
    }

    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

    public void deleteByBoardId(Long boardId) {
        commentMapper.deleteByBoardId(boardId);
    }
}
