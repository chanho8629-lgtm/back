package com.app.ggshop.v1.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MainNotificationDTO {
    private Long id;
    private Long boardId;
    private String boardTitle;
    private String actorName;
    private String commentContent;
    private String createdDate;
}
