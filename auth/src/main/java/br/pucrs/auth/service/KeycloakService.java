package br.pucrs.auth.service;

import java.util.List;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

public interface KeycloakService {
    AuthenticationResponseDTO generateToken(AuthenticationRequestDTO dto);

    List<UserResponseDTO> findAllUsers(String auth);
}
