package com.app.ggshop.v1.dto;


import com.app.ggshop.v1.common.enumeration.Filter;
import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.domain.OAuthVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    @JsonIgnore
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
    private String memberAddress;
    private Filter memberFilter;
    private Status memberStatus;
    private String createdDate;
    private String updatedDate;
    private Provider provider;
    private boolean remember;

    public MemberVO toMemberVO() {
        return MemberVO.builder()

                .id(id)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberAddress(memberAddress)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();

    }
    public OAuthVO toOAuthVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}
