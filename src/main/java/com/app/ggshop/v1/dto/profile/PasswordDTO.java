package com.app.ggshop.v1.dto.profile;

import com.app.ggshop.v1.domain.MemberVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class PasswordDTO {
    private Long id;
    private String memberPassword;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
            .id(id)
            .memberPassword(memberPassword)
            .build();
    }
}
