package site.metacoding.blogv3.web;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import site.metacoding.blogv3.config.auth.LoginUser;

@Controller
public class MainController {

    @GetMapping({ "/" })
    public String main(@AuthenticationPrincipal LoginUser loginUser) {

        // http.getsession 에서 이제 세션값 가져올 수 없음
        // System.out.println(loginUser.getUsername());
        // System.out.println(loginUser.getUser().getUsername());

        // LoginUser lu = (LoginUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // System.out.println(lu.getUser().getEmail());
        return "main";
    }
}
