package com.app.ggshop.v1.controller.profile;

import com.app.ggshop.v1.dto.profile.ProfileDTO;
import com.app.ggshop.v1.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/profile/**")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final HttpSession session;

    @GetMapping("settings")
    public String profileMovement(Model model) {
        session.setAttribute("memberId", 1L);
        Long memberId = (Long) session.getAttribute("memberId");
        model.addAttribute("profileDTO", profileService.getProfile(memberId));
        return "myPageSettings/myPageSettings";
    }

    @PostMapping("update")
    @ResponseBody
    public String changeProfileImage(ProfileDTO profileDTO,
                                      @RequestParam("file") MultipartFile file) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "fail: no session"; // 세션 만료 대응
        }
        profileDTO.setMemberId(memberId);
        profileService.changeProfileImage(profileDTO, file);
        return "success";
    }
}
