package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.profile.NicknameDTO;
import com.app.ggshop.v1.mapper.profile.NicknameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NicknameDAO {

    private final NicknameMapper nicknameMapper;

    // 수정
    public void updateNickname(NicknameDTO nicknameDTO) {
        nicknameMapper.updateNickname(nicknameDTO);
    }

    // 조회
    public NicknameDTO findId(Long id) {
        return nicknameMapper.selectNicknameById(id);
    }

}
