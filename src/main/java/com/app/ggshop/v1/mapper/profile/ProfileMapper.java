package com.app.ggshop.v1.mapper.profile;

import com.app.ggshop.v1.domain.MemberFileVO;
import com.app.ggshop.v1.dto.profile.ProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ProfileMapper {
//    파일 등록
    public void insertMemberFile(MemberFileVO memberFileVO);
//    파일 교체 시 일어나는 델리트
    public void deleteByProfile(Long memberId);

    Optional<ProfileDTO> selectProfileByMemberId(Long memberId);
}