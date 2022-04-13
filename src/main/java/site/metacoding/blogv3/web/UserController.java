package site.metacoding.blogv3.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.handler.ex.CustomException;
import site.metacoding.blogv3.service.UserService;
import site.metacoding.blogv3.web.dto.user.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

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

        if (bindingResult.hasErrors()) {
            // 키와 밸류값을 같이 넣기엔 map 이 좋다.
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {

                // System.out.println(fe.getField());
                // System.out.println(fe.getDefaultMessage());

                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            // 이 부분에서 data 리턴인지 html 리턴인지 이것만 구분해서 터트려라
            throw new CustomException(errorMap.toString());
        }
        // 핵심 로직
        userService.회원가입(joinReqDto.toEntity()); // toEntity 로 하면 user Object로 바뀜..!!

        return "redirect:/login-form";

    }
}
