package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentVO {
    private Long id;
    private String commentContent;
    private Status commentStatus;
    private String createdDate;
    private String updatedDate;
    private Long commentBoardId;
    private Long commentMemberId;
}
