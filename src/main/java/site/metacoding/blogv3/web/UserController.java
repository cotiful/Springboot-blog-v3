package site.metacoding.blogv3.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.handler.ex.CustomException;
import site.metacoding.blogv3.service.UserService;
import site.metacoding.blogv3.util.UtilValid;
import site.metacoding.blogv3.util.email.EmailUtil;
import site.metacoding.blogv3.web.dto.user.JoinReqDto;
import site.metacoding.blogv3.web.dto.user.PasswordResetReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {
        return "/user/passwordResetForm";
    }

    @PostMapping("/user/password-reset")
    public String passwordReset(@Valid PasswordResetReqDto passwordResetReqDto, BindingResult bindingResult) {

        UtilValid.요청에러처리(bindingResult);

        // 아이디, 이메일 같은거 있는지 확인하기
        userService.패스워드초기화(passwordResetReqDto);
        return "redirect:/login-form";
    }

    // ResponseEntity 는 @responseBody를 붙이지 않아도 data를 리턴한다.
    @GetMapping("/api/user/username-same-check")
    public ResponseEntity<?> usernameSameCheck(String username) {
        boolean isNotSame = userService.유저네임중복검사(username); // true(같지 않다)
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {
        // System.out.println(bindingResult.hasErrors());

        UtilValid.요청에러처리(bindingResult);
        userService.회원가입(joinReqDto.toEntity()); // toEntity 로 하면 user Object로 바뀜..!!

        return "redirect:/login-form";

    }
}
