package com.app.ggshop.v1.dto.board;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.BoardVO;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

@Getter@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private Status boardStatus;
    private Long boardMemberId;
    private String boardFilter;
    private String createdDate;
    private String updatedDate;

    public BoardVO toVO(){
        return BoardVO.builder()
                .id(id)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardMemberId(boardMemberId)
                .boardStatus(boardStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .boardFilter(boardFilter)
                .build();
    }


}
