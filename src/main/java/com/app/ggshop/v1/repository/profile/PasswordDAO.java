package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.dto.profile.NicknameDTO;
import com.app.ggshop.v1.dto.profile.PasswordDTO;
import com.app.ggshop.v1.mapper.profile.NicknameMapper;
import com.app.ggshop.v1.mapper.profile.PasswordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PasswordDAO {

    private final PasswordMapper passwordMapper;

    // 수정
    public void updatePassword(PasswordDTO passwordDTO) {
        passwordMapper.updatePassword(passwordDTO);
    }
    // 조회
    public PasswordDTO findId(Long id) {
        return passwordMapper.selectPasswordById(id);
    }

}
