package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.CommentVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentContent;
    private Status commentStatus;
    private String createdDate;
    private String updatedDate;
    private Long commentBoardId;
    private Long commentMemberId;
    private String memberNickname;

    public CommentVO toVO() {
        return CommentVO.builder()
                .id(id)
                .commentContent(commentContent)
                .commentStatus(commentStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .commentBoardId(commentBoardId)
                .commentMemberId(commentMemberId)
                .build();
    }
}
