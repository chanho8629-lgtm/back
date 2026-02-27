package com.app.ggshop.v1.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MainCardDTO {
    private Long id;
    private String title;
    private String content;
    private String boardFilter;
    private String memberName;
    private String createdDate;
}
