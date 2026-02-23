package com.app.ggshop.v1.mapper.profile;

import com.app.ggshop.v1.dto.profile.NicknameDTO;
import com.app.ggshop.v1.dto.profile.PasswordDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasswordMapper {
    public void updatePassword(PasswordDTO passwordDTO);

    public PasswordDTO selectPasswordById(Long id);

}
