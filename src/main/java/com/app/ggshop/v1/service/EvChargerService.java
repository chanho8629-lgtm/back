package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.repository.EvChargerDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EvChargerService {

    private final EvChargerDAO evChargerDAO;

    //    충전소 등록
    public void registerEvCharger(EvChargerDTO dto) {

        // 기본 상태값 세팅 (form에 없으므로)
        dto.setEvChargerStatus(Status.ACTIVE);
        dto.setEvChargerMod(Status.ACTIVE);

        // TODO: 로그인 사용자 회사 ID 세팅
        dto.setCompanyId(1L);

        EvChargerVO evChargerVO = dto.toVO();

        log.info("▶ 충전소 등록 VO: {}", evChargerVO);

        evChargerDAO.save(evChargerVO);

//        evChargerDAO.insertEvCharger(evChargerVO);
    }

//    private final EvChargerDAO evChargerDAO;

//    public List<EvChargerVO> getEvChargerList(Long companyId) {
//        return evChargerDAO.findAllByCompany(companyId);
//    }

    public List<EvChargerVO> getEvChargerList(Long companyId) {
        List<EvChargerVO> chargerList = evChargerDAO.findAllByCompany(companyId);

        // 로그 출력
        log.info("목록 리스트 확인 회사 ID: {} 충전소 리스트: {}", companyId, chargerList);

        // System.out.println("회사 ID: " + companyId + " 충전소 리스트: " + chargerList);

        return chargerList;
    }

}
