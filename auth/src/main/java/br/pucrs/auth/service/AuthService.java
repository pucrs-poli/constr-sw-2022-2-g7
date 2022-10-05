package br.pucrs.auth.service;

import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.auth.dto.response.UserInfoResponseDTO;

public interface AuthService {
    AuthenticationResponseDTO login(String username, String password);

    AuthenticationResponseDTO refreshToken(String refreshToken);

    UserInfoResponseDTO getUserInfo();

    TokenIntrospectResponseDTO tokenIntrospect();
}
