package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO dto);

    List<UserResponseDTO> findAll();

    void save(UserRequestDTO userRequestDTO);
}
