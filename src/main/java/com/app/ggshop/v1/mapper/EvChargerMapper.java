package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.EvChargerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvChargerMapper {

    void insertEvCharger(EvChargerVO evChargerVO);
    List<EvChargerVO> selectEvChargerList(Long companyId);

    // companyId 상관없이 전체 조회
    List<EvChargerVO> selectAllEvChargers();
}
