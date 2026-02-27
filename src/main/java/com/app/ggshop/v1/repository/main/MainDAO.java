package com.app.ggshop.v1.repository.main;

import com.app.ggshop.v1.dto.main.MainCardDTO;
import com.app.ggshop.v1.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainDAO {
    private final HomeMapper homeMapper;

    public List<MainCardDTO> findRecentCards(int limit) {
        return homeMapper.selectRecentCards(limit);
    }

    public int findActivePostCount() {
        return homeMapper.selectActivePostCount();
    }
}
