package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.UserChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.auth.dto.response.UserInfoResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

import java.util.List;

public interface KeycloakService {
    AuthenticationResponseDTO generateToken(String username, String password);

    AuthenticationResponseDTO refreshToken(String refreshToken);

    UserInfoResponseDTO getUserInfo(String token);

    TokenIntrospectResponseDTO tokenIntrospect(String token);

    List<UserResponseDTO> findAllUsers();

    void saveUser(UserRequestDTO userRequestDTO);

    void updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO findUserById(String id);

    void changePassword(String id, UserChangePasswordRequestDTO userChangePasswordRequestDTO);

    void deleteUser(String id);
}
