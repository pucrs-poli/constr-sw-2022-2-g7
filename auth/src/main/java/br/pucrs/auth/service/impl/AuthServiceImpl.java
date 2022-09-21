package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.RefreshTokenRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.service.AuthService;
import br.pucrs.auth.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final KeycloakService keycloakService;

    @Override
    public String getLoggedUserToken() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @Override
    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO dto) {
        return this.keycloakService.refreshToken(dto);
    }
}
