package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.domain.BoardTagVO;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class BoardTagDTO {
    private Long id;
    private String tagName;
    private Long tagBoardId;

    public BoardTagVO toVO(){
        return BoardTagVO.builder()
                .id(id)
                .tagName(tagName)
                .tagBoardId(tagBoardId)
                .build();
    }
}













