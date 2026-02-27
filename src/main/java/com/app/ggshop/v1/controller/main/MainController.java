package com.app.ggshop.v1.controller.main;

import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.main.MainPageDTO;
import com.app.ggshop.v1.service.main.MainService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping({"/main", "/ev/main"})
    public String showMainPage(HttpSession session, Model model) {
        MainPageDTO mainPage = mainService.getMainPageData(6);
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member != null) {
            mainPage.setDisplayName(member.getMemberName());
        } else {
            mainPage.setDisplayName("게스트");
        }

        model.addAttribute("mainPage", mainPage);
        return "main_4";
    }
}
