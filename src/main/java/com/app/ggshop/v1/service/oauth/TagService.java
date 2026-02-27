package com.app.ggshop.v1.service.oauth;

import com.app.ggshop.v1.repository.board.TagDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TagService {
    private final TagDAO tagDAO;

    public List<String> selectAll(){
        return tagDAO.findAll();
    }
}