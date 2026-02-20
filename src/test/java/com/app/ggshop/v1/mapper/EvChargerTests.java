package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.service.EvChargerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class EvChargerTests {

    @Autowired
    private EvChargerMapper evChargerMapper;

    @Autowired
    private EvChargerService evChargerService;

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


    @Test
    void selectAllEvChargerList_Test() {

        // given
        int page = 1;
        int size = 10;
        int offset = (page - 1) * size;

        Criteria criteria = new Criteria(page, size);

        // when
        List<EvChargerDTO> chargerList = evChargerMapper.selectAll(criteria); // ✅


        int totalCount = evChargerMapper.selectTotalCount();

        // then
        log.info("▶ 전체 충전소 개수: {}", totalCount);
        log.info("▶ 현재 페이지 데이터 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getStationNumber());
            log.info("주소          : {}", charger.getInstallAddress());
            log.info("특이사항      : {}", charger.getChargerNote());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getInstallDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });


    }

    @Test
    void list_ShouldReturnPagedDTO_With10PerPage() {
        // given
        int page = 1;

        // when
        PostWithPagingDTO result = evChargerService.list(page);

        // then
        assertNotNull(result);
        assertNotNull(result.getEvChargerList());

        List<EvChargerDTO> list = result.getEvChargerList();

        System.out.println("▶ 전체 조회 페이지: " + page);
        System.out.println("▶ 현재 페이지 데이터 개수: " + list.size());
        System.out.println("▶ Criteria 확인: " + result.getCriteria());

        // DB 11개 기준, 한 페이지 10개
        assertEquals(10, list.size());
        assertEquals(1, result.getCriteria().getPage());

        // 일부 데이터 확인
        EvChargerDTO first = list.get(0);
        System.out.println("첫 번째 충전소 UID: " + first.getStationNumber());
        System.out.println("첫 번째 충전소 주소: " + first.getInstallAddress());
        System.out.println("첫 번째 충전소 상태: " + first.getEvChargerStatus());
    }


}