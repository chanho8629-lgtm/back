package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testInsert() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("test@gmail.com");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberName("test");
        memberDTO.setProvider(Provider.THREETIER);

        memberMapper.insert(memberDTO);
        memberMapper.insertOauth(memberDTO.toOAuthVO());


    }
}
