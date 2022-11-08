package br.pucrs.classesms.component.impl;

import br.pucrs.classesms.component.TokenComponent;
import br.pucrs.classesms.dto.CustomUserDetails;
import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import br.pucrs.classesms.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenComponentImpl implements TokenComponent {
    private final AuthService authService;

    @SneakyThrows
    @Override
    public UserInfoResponseDTO getUserInfo(String token) {
        try {
            return this.authService.userInfo("Bearer " + token);
        } catch (Exception e) {
            log.error("Exception ao chamar getUserInfo - " + e.getMessage() + " - token == " + token);
            throw new Exception(e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public Authentication getAuthentication(String token, UserInfoResponseDTO userInfoResponseDTO) {
        ArrayList<SimpleGrantedAuthority> groups = userInfoResponseDTO.getGroups().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(ArrayList::new));

        CustomUserDetails principal = CustomUserDetails.builder()
                .token(token)
                .authorities(groups)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", groups);
    }
}
