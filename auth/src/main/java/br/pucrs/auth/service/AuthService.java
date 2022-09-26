package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.auth.dto.response.UserInfoResponseDTO;

public interface AuthService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);

    AuthenticationResponseDTO refreshToken(String refreshToken);

    UserInfoResponseDTO getUserInfo(String token);

    TokenIntrospectResponseDTO tokenIntrospect(String token);
}
