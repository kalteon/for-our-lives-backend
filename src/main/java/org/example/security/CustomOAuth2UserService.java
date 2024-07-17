package org.example.security;

import lombok.RequiredArgsConstructor;
import org.example.domain.Member;
import org.example.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        Member member = memberRepository.findByEmail(email).orElseGet(() -> {
            Member newMember = Member.builder()
                    .email(email)
                    .name(oAuth2User.getAttribute("name"))
                    .build();
            return memberRepository.save(newMember);
        });

        String token = jwtTokenProvider.createToken(email);
        ((DefaultOAuth2User) oAuth2User).getAttributes().put("token", token);

        return oAuth2User;
    }
}

