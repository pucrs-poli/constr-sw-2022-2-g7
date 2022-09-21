package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.UserChangePasswordDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

import java.util.List;

public interface KeycloakService {
    AuthenticationResponseDTO generateToken(AuthenticationRequestDTO dto);

    List<UserResponseDTO> findAllUsers();

    void saveUser(UserRequestDTO userRequestDTO);

    void updateUser(UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO findUserById(String id);

    void changePassword(String id, UserChangePasswordDTO userChangePasswordDTO);

    void deleteUser(String id);
}
