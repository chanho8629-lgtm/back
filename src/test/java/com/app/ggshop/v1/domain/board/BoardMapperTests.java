package com.app.ggshop.v1.domain.board;


import com.app.ggshop.v1.dto.board.BoardDTO;
import com.app.ggshop.v1.mapper.board.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void insertTest() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("test12");
        boardDTO.setBoardContent("testconten12t");
        boardDTO.setBoardMemberId(1L);

        boardMapper.insert(boardDTO);
        assertThat(boardDTO.getId()).isNotNull();
    }
}
