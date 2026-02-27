package com.app.ggshop.v1.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MainPageDTO {
    private String displayName;
    private int activePostCount;
    private List<MainCardDTO> recentCards;
}
