package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model) {
        // user page로 그냥 가면 안되고, 이미지 데이터를 들고 가야됨.
        userService.회원프로필(id);
        model.addAttribute("images",null);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) { // Authentication에 바로 접근할 수 있게 해주는 어노테이션 -> 세션에 접근하게 해줌 (12강)
        System.out.println("세션 정보: " + principalDetails.getUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal(); // 다운캐스팅
        System.out.println("직접 찾은 세션 정보: " + mPrincipalDetails.getUser());

        model.addAttribute("principal",principalDetails.getUser());
        return "user/update";
    }
}
