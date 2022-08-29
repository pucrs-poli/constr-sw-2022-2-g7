package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;

public interface KeycloakService {
    AuthenticationResponseDTO generateToken(AuthenticationRequestDTO dto);
}
