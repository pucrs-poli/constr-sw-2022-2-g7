package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.UserChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAll();

    void save(UserRequestDTO userRequestDTO);

    void update(String id, UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO findById(String id);

    void changePassword(String id, UserChangePasswordRequestDTO userChangePasswordRequestDTO);

    void delete(String id);
}
