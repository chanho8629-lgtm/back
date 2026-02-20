package com.app.ggshop.v1.controller.Member;

import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.service.Login.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class MemberController {

    private final MemberService memberService;

//      이메일 입력 화면 및 비밀번호 입력 화면
    @GetMapping("/join_update_6")
    public String joinEmailPage(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String error,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMsg", "wrongPassword".equals(error)
                    ? "비밀번호가 일치하지 않습니다."
                    : "계정 정보 확인 중 오류가 발생했습니다.");
        }

        boolean showPassword = false;

        if (email != null && !email.isBlank()) {
            // memberService.checkEmail이 true(사용가능=DB에없음)이면 회원가입으로
            // false(중복=DB에있음)이면 비밀번호 입력창으로
            boolean isNewUser = memberService.checkEmail(email);

            if (!isNewUser) {
                // DB에 존재함 (기존 회원) -> 비밀번호 입력창 노출
                showPassword = true;
                model.addAttribute("email", email);
            } else {
                // DB에 없음 (신규 회원) -> 회원가입 페이지 이동
                return "redirect:/login/join_second?email=" + email;
            }
        }

        model.addAttribute("showPassword", showPassword);
        return "login/join_update_6";
    }

//      이메일  (로그인 / 회원가입)

    @PostMapping("/join_update_6")
    public String processEmail(
            @RequestParam("username") String email,
            @RequestParam(value = "password", required = false) String password,
            HttpSession session) {

        session.removeAttribute("loginMember");

        // DB 존재 여부 확인 (false면 DB에 있음)
        boolean isNewUser = memberService.checkEmail(email);

        if (!isNewUser) {
            // [CASE 1] 기존 회원 (DB에 있음)
            if (password == null || password.isBlank()) {
                // 비밀번호가 없으면 입력창 띄우러 다시 GET 호출
                return "redirect:/login/join_update_6?email=" + email;
            }

            // 로그인 로직 실행
            MemberDTO dto = new MemberDTO();
            dto.setMemberEmail(email);
            dto.setMemberPassword(password);

            try {
                MemberDTO loginMember = memberService.login(dto);
                session.setAttribute("loginMember", loginMember);

                return "redirect:/login/main";
            } catch (Exception e) {
                return "redirect:/login/join_update_6?error=wrongPassword&email=" + email;
            }

        } else {
            // [CASE 2] 신규 회원 (DB에 없음) -> 회원가입 이동
            return "redirect:/login/join_second?email=" + email;
        }
    }

//     * [GET] 메인 페이지 (로그인 성공 후 접속)

    @GetMapping("/main")
    public String mainPage(HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/login/join_update_6";
        }
        // src/main/resources/templates/main.html을 호출
        return "main";
    }

    @GetMapping("/join_second")
    public String joinSecond(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "login/join_second";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/join_update_6";
    }


//   POST 회원가입 완료 처리

    @PostMapping("/join_second")
    public String register(MemberDTO memberDTO) {
        memberService.join(memberDTO);
        System.out.println("{}}.............: " + memberDTO.toString());
        return "redirect:/login/join_update_6";
    }

//      로그인/카카오 로그인 선택 페이지

    @GetMapping("/login_mobile_check")
    public String loginStartPage(@ModelAttribute("kakaoMember") MemberDTO kakaoMember, Model model) {

        // 카카오 로그인을 통해 정보를 가지고 돌아온 경우
        if (kakaoMember != null && kakaoMember.getMemberEmail() != null) {
            model.addAttribute("email", kakaoMember.getMemberEmail());
            model.addAttribute("isKakaoReturn", true); // 카카오 인증 후 돌아왔음을 표시
        }

        return "login/login_mobile_check";
    }


    @GetMapping("/go_email_join")
    public String goEmailJoin() {

        return "redirect:/login/join_update_6";
    }

}