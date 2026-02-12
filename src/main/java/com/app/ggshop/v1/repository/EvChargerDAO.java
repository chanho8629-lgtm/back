package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.mapper.EvChargerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EvChargerDAO {

    private final EvChargerMapper evChargerMapper;

    public void save(EvChargerVO evChargerVO) {
        evChargerMapper.insertEvCharger(evChargerVO);
    }


    public List<EvChargerVO> findAllByCompany(Long companyId) {
        return evChargerMapper.selectEvChargerList(companyId);
    }



}