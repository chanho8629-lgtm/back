package com.app.ggshop.v1.repository.profile;

import com.app.ggshop.v1.domain.MemberFileVO;
import com.app.ggshop.v1.mapper.profile.ProfileImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileImageDAO {
    private final ProfileImageMapper memberFileMapper;

//    추가
    public void save(MemberFileVO memberFileVO) {
        memberFileMapper.insertMemberFile(memberFileVO);
    }

//    목록
//    public List<PostFileDTO>  findAllByPostId(Long id) {
//        return postFileMapper.selectAllByPostId(id);
//    }
}













