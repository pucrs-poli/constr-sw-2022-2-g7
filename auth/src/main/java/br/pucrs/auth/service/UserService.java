package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;

public interface UserService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO dto);
}
