package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.RefreshTokenRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;

public interface AuthService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO dto);

    String getLoggedUserToken();

    AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO dto);
}
