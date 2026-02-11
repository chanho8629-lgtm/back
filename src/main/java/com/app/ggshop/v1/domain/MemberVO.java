package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@EqualsAndHashCode(of = "id", callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
//@SuperBuilder
public class MemberVO extends Period {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
    private String memberAddress;
}
