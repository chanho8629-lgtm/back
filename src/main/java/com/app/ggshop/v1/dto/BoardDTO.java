package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.BoardFilter;
import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.BoardVO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String title;           // [구매자] 구매 희망합니다
    private String content;         // 내용
    private String summary;         // 요약
    private BoardFilter boardFilter;
    private Status boardStatus;
    private String createdDate;
    private String updatedDate;
    private Long boardMemberId;
    private String representativeFilePath;
    private String representativeFileName;

    /**
     * 제목에서 [구매자] 또는 [판매자] 파싱
     */
    public void parseTitleAndSetFilter() {
        if (this.title == null || this.title.isEmpty()) {
            this.boardFilter = BoardFilter.ALL;
            return;
        }

        if (this.title.startsWith("[구매자]")) {
            this.boardFilter = BoardFilter.BUY;
        } else if (this.title.startsWith("[판매자]")) {
            this.boardFilter = BoardFilter.SELL;
        } else {
            this.boardFilter = BoardFilter.ALL;
        }
    }

    private List<BoardTagDTO> boardTags = new ArrayList<>();
    private String[] tagIdsToDelete;

    private List<BoardFileDTO> boardFiles = new ArrayList<>();
    private String[] fileIdsToDelete;


    public BoardVO toVO() {
        return BoardVO.builder()
                .id(id)
                .title(title)
                .content(content)
                .boardFilter(boardFilter)
                .boardStatus(boardStatus)
                .createdDate (createdDate)
                .updatedDate (updatedDate)
                .boardMemberId(boardMemberId)
                .build();
    }
}
