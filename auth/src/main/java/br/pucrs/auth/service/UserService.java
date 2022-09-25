package br.pucrs.auth.service;

import br.pucrs.auth.dto.request.UserChangePasswordDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAll();

    void save(UserRequestDTO userRequestDTO);

    void update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO findById(String id);

    void changePassword(String id, UserChangePasswordDTO userChangePasswordDTO);

    void delete(String id);
}
