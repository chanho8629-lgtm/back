package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.EvChargerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class EvChargerTests {

    @Autowired
    private EvChargerMapper evChargerMapper;

    @Test
    void insertEvChargerTest() {
        // given
        EvChargerVO evChargerVO = EvChargerVO.builder()
                .evChargerUid("EV-TEST-1533333")
                .evChargerAddress("서울시 강남구 테헤란로 123")
                .evChargerContent("지하 1층, 장애인 주차구역 옆")
                .evChargerStatus(Status.ACTIVE)   // enum('active','inactive')
                .evChargerMod(Status.ACTIVE)      // enum('active','inactive')
                .setupDate("2026-02-01 10:00:00") // DATETIME
                .companyId(2L)                    // 반드시 tbl_company에 존재
                .build();

        // when
        evChargerMapper.insertEvCharger(evChargerVO);

        // then
        log.info("▶ 생성된 충전기 ID: {}", evChargerVO.getId());
    }

    @Test
    void selectEvChargerListTest() {
        // given
        Long companyId = 1L;

        // when
        List<EvChargerVO> chargerList =
                evChargerMapper.selectEvChargerList(companyId);

        // then
        log.info("▶ 충전소 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getEvChargerUid());
            log.info("주소          : {}", charger.getEvChargerAddress());
            log.info("특이사항      : {}", charger.getEvChargerContent());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getSetupDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });
    }


    @Test
    void selectAllEvChargerListTest() {
        List<EvChargerVO> chargerList =
                evChargerMapper.selectAllEvChargers();

        log.info("▶ 전체 충전소 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getEvChargerUid());
            log.info("주소          : {}", charger.getEvChargerAddress());
            log.info("특이사항      : {}", charger.getEvChargerContent());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getSetupDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });
    }
}