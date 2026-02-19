package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.domain.MemberFileVO;
import com.app.ggshop.v1.dto.profile.ProfileDTO;
import com.app.ggshop.v1.mapper.profile.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileDAO {
    private final ProfileMapper memberFileMapper;
    private final ProfileMapper profileMapper;

    //    추가
    public void save(MemberFileVO memberFileVO) {
        memberFileMapper.insertMemberFile(memberFileVO);
    }

    public void deleteByProfile(Long memberId) {
        profileMapper.deleteByProfile(memberId);
    }

    public Optional<ProfileDTO> findProfileByMemberId(Long memberId) {return profileMapper.selectProfileByMemberId(memberId);}

//    목록
//    public List<PostFileDTO>  findAllByPostId(Long id) {
//        return postFileMapper.selectAllByPostId(id);
//    }
}













