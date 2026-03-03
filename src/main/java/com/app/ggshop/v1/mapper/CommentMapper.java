package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.CommentVO;
import com.app.ggshop.v1.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {
    void insert(CommentVO commentVO);

    List<CommentDTO> selectAllByBoardId(@Param("boardId") Long boardId);

    Optional<CommentDTO> selectById(@Param("id") Long id);

    void updateContent(@Param("id") Long id, @Param("commentContent") String commentContent);

    void deleteById(@Param("id") Long id);

    void deleteByBoardId(@Param("boardId") Long boardId);
}
