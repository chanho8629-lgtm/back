package com.app.ggshop.v1.mapper.profile;

import com.app.ggshop.v1.domain.MemberFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileImageMapper {
    public void insertMemberFile(MemberFileVO memberFileVO);
}