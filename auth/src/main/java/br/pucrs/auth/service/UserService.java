package br.pucrs.auth.service;

import java.util.List;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

public interface UserService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO dto);

    List<UserResponseDTO> findAll(String auth);
}
