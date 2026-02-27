package com.app.ggshop.v1.domain;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardFileVO {
    private Long id;
    private Long boardid;
}
