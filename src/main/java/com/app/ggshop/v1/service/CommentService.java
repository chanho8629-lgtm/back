package com.app.ggshop.v1.service;

import com.app.ggshop.v1.dto.CommentDTO;
import com.app.ggshop.v1.repository.board.CommentDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CommentService {
    private final CommentDAO commentDAO;

    public List<CommentDTO> list(Long boardId) {
        return commentDAO.findAllByBoardId(boardId);
    }

    public void write(Long boardId, Long memberId, String commentContent) {
        if (commentContent == null || commentContent.trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentBoardId(boardId);
        commentDTO.setCommentMemberId(memberId);
        commentDTO.setCommentContent(commentContent.trim());
        commentDAO.save(commentDTO.toVO());
        log.info("▶ 댓글 등록 완료 - boardId={}, memberId={}", boardId, memberId);
    }

    public void update(Long commentId, Long memberId, String commentContent) {
        if (commentContent == null || commentContent.trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        CommentDTO found = commentDAO.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (!found.getCommentMemberId().equals(memberId)) {
            throw new IllegalArgumentException("본인 댓글만 수정할 수 있습니다.");
        }

        commentDAO.setContent(commentId, commentContent.trim());
        log.info("▶ 댓글 수정 완료 - commentId={}, memberId={}", commentId, memberId);
    }

    public void delete(Long commentId, Long memberId) {
        CommentDTO found = commentDAO.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (!found.getCommentMemberId().equals(memberId)) {
            throw new IllegalArgumentException("본인 댓글만 삭제할 수 있습니다.");
        }

        commentDAO.delete(commentId);
        log.info("▶ 댓글 삭제 완료 - commentId={}, memberId={}", commentId, memberId);
    }
}
