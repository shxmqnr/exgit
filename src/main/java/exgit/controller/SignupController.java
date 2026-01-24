package exgit.controller;

import exgit.command.SignupCommand;
import exgit.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupCommand", new SignupCommand());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupCommand signupCommand,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "signup";
        }

        if (memberService.isUserIdTaken(signupCommand.getUserId())) {
            result.rejectValue("userId", null, "이미 사용 중인 아이디입니다.");
            return "signup";
        }

        memberService.signup(signupCommand);
        
        // 가입 완료 후 사용자 아이디와 이메일을 모델에 담아서
        model.addAttribute("userId", signupCommand.getUserId());
        model.addAttribute("email", signupCommand.getEmail());

        return "signupSuccess";  // 가입 완료 페이지 뷰 이름

        //return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
    }
}
