package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.common.exception.LoginFailException;
import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    private final MemberDAO memberDAO;

    //    이메일 검사(true: 사용가능)
    public boolean checkEmail(String memberEmail){
        return memberDAO.findByMemberEmail(memberEmail).isEmpty();
    }
    //    회원가입
    public void join(MemberDTO memberDTO){
        memberDTO.setProvider(Provider.THREETIER);
        memberDAO.save(memberDTO);
        memberDAO.saveOAuth(memberDTO.toOAuthVO());
    }

    //    회원가입
    public void kakaoJoin(MemberDTO memberDTO){
        memberDTO.setProvider(Provider.KAKAO);
        memberDAO.save(memberDTO);
        memberDAO.saveOAuth(memberDTO.toOAuthVO());
    }

    //    로그인
    public MemberDTO login(MemberDTO memberDTO){
        Optional<MemberVO> foundMember = memberDAO.findForLogin(memberDTO);
        return toDTO(foundMember.orElseThrow(LoginFailException::new));
    }

    public MemberDTO toDTO(MemberVO memberVO){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberVO.getId());
        memberDTO.setMemberEmail(memberVO.getMemberEmail());
        memberDTO.setMemberName(memberVO.getMemberName());
        memberDTO.setCreatedDate(memberVO.getCreatedDate());
        memberDTO.setUpdatedDate(memberVO.getUpdatedDate());
        return memberDTO;
    }
}