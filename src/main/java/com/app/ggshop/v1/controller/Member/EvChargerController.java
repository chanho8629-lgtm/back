package com.app.ggshop.v1.controller.Member;

//import ch.qos.logback.core.model.Model;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.service.EvChargerService;
import com.app.ggshop.v1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/ev")
@RequiredArgsConstructor
@Slf4j
public class EvChargerController {

    private final EvChargerService evChargerService;

    // 목록
    @GetMapping("/evcharger/list")
    public String getEvChargerList(Model model) {
        Long companyId = 1L; // 예시, 실제 로그인한 회사 ID로 대체 가능
        // 서비스에서 리스트 받아오기
        List<EvChargerVO> chargerList = evChargerService.getEvChargerList(companyId);
        // 모델에 리스트 담기
        model.addAttribute("chargerList", chargerList);
        return "/company-admin/company-charger"; // Thymeleaf 뷰 이름
    }

    // 등록 페이지
    @GetMapping("/evcharger")
    public String goToEvChargerForm() {
        return "/company-admin/company-charger-create";
    }

    // 등록 처리
    @PostMapping("/evcharger")
    public String registerEvCharger(EvChargerDTO evChargerDTO) {
        log.info("▶ 충전소 등록 요청 DTO: {}", evChargerDTO);
        evChargerService.registerEvCharger(evChargerDTO);

        return "redirect:/ev/evcharger/list";
    }

    @GetMapping("/list")
    public String getEvChargerList_(Model model) {
        Long companyId = 1L;
        List<EvChargerVO> chargerList = evChargerService.getEvChargerList(companyId);
        log.info("컨트롤러 회사 ID: {} 충전소 리스트: {}", companyId, chargerList);
        // 모델에 담기
        model.addAttribute("chargerList", chargerList);
        return "/company-admin/company-charger";
    }

    @GetMapping("/login/test")
    public String login_test() {

        return "/login/login_mobile_check";
//        return "/p2p/p2p_list";
    }

}
