package com.app.ggshop.v1.service.main;

import com.app.ggshop.v1.dto.main.MainCardDTO;
import com.app.ggshop.v1.dto.main.MainPageDTO;
import com.app.ggshop.v1.repository.main.MainDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainService {
    private final MainDAO mainDAO;

    public MainPageDTO getMainPageData(int cardLimit) {
        MainPageDTO mainPageDTO = new MainPageDTO();
        List<MainCardDTO> cards = mainDAO.findRecentCards(cardLimit);
        int activePostCount = mainDAO.findActivePostCount();

        if (cards == null || cards.isEmpty()) {
            cards = buildDummyCards(cardLimit);
            activePostCount = cards.size();
        }

        mainPageDTO.setRecentCards(cards);
        mainPageDTO.setActivePostCount(activePostCount);
        return mainPageDTO;
    }

    private List<MainCardDTO> buildDummyCards(int cardLimit) {
        List<MainCardDTO> dummyCards = new ArrayList<>();

        dummyCards.add(makeDummy(1L, "전기차 완속 충전기 구매 희망", "아파트 주차장 설치용 완속 충전기를 찾고 있습니다.", "구매", "김지훈", "2026-02-26"));
        dummyCards.add(makeDummy(2L, "가정용 V2G 배터리 판매", "주행거리 2만km, 상태 양호한 배터리 판매합니다.", "판매", "박서연", "2026-02-25"));
        dummyCards.add(makeDummy(3L, "급속 충전 커넥터 공동구매", "커넥터 10개 이상 구매 예정이라 같이 구매하실 분 구합니다.", "구매", "이도윤", "2026-02-24"));
        dummyCards.add(makeDummy(4L, "중고 충전 케이블 판매", "CCS 타입 케이블, 사용감 적고 정상 동작 확인했습니다.", "판매", "최민아", "2026-02-23"));
        dummyCards.add(makeDummy(5L, "회사 주차장 충전기 설치 문의", "법인 차량용 충전기 설치 업체 추천받고 싶습니다.", "구매", "정현우", "2026-02-22"));
        dummyCards.add(makeDummy(6L, "충전 스케줄링 장치 판매", "야간 요금 연동 가능한 스케줄러 장치 판매합니다.", "판매", "한유진", "2026-02-21"));

        if (cardLimit < dummyCards.size()) {
            return dummyCards.subList(0, cardLimit);
        }
        return dummyCards;
    }

    private MainCardDTO makeDummy(Long id, String title, String content, String boardFilter, String memberName, String createdDate) {
        MainCardDTO card = new MainCardDTO();
        card.setId(id);
        card.setTitle(title);
        card.setContent(content);
        card.setBoardFilter(boardFilter);
        card.setMemberName(memberName);
        card.setCreatedDate(createdDate);
        return card;
    }
}
