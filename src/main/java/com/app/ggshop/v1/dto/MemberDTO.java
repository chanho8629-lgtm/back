package com.app.ggshop.v1.dto;


import com.app.ggshop.v1.common.enumeration.Filter;
import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.MemberVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
    private String memberAddress;
    private Filter memberFilter;
    private Status memberStatus;
    private String createdDate;
    private String updatedDate;

    public MemberVO toMemberVO() {
        return MemberVO.builder()

                .id(id)

    }
}
