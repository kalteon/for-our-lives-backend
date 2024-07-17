package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.security.JwtTokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/loginSuccess")
    public String loginSuccess(HttpServletRequest request) {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2User user = authentication.getPrincipal();
        String token = (String) user.getAttributes().get("token");
        return "Bearer " + token;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        response.setHeader("Set-Cookie", "JSESSIONID=; HttpOnly; Path=/; Max-Age=0; SameSite=None; Secure");
    }
}

