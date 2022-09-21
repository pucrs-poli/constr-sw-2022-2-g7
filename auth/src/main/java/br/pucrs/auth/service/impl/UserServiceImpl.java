package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.UserChangePasswordDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;
import br.pucrs.auth.exceptions.AuthBadRequestException;
import br.pucrs.auth.i18n.Translator;
import br.pucrs.auth.service.KeycloakService;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final KeycloakService keycloakService;
    private final Translator translator;

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO dto) {
        if (isNull(dto.getUsername()) || isNull(dto.getPassword())) {
            throw new AuthBadRequestException(translator.toLocale("msg_Invalid_User_Or_Password"));
        }

        return this.keycloakService.generateToken(dto);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return this.keycloakService.findAllUsers();
    }

    @Override
    public void save(UserRequestDTO userRequestDTO) {
        this.validate(userRequestDTO);
        userRequestDTO.toBuilder().enabled("S");
        this.keycloakService.saveUser(userRequestDTO);
    }

    @Override
    public void update(UserUpdateRequestDTO userUpdateRequestDTO) {
        if (isNull(userUpdateRequestDTO.getId())) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_id")
            ));
        }
        this.keycloakService.updateUser(userUpdateRequestDTO);
    }

    @Override
    public UserResponseDTO findById(String id) {
        return this.keycloakService.findUserById(id);
    }

    @Override
    public void changePassword(String id, UserChangePasswordDTO userChangePasswordDTO) {
        if (isNull(userChangePasswordDTO.getPassword())) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_Password")
            ));
        }
        this.keycloakService.changePassword(id, userChangePasswordDTO);
    }

    @Override
    public void delete(String id) {
        this.keycloakService.deleteUser(id);
    }

    private void validate(UserRequestDTO userRequestDTO) {
        if (isNull(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException(translator.toLocale(
                "msg_Field_0_is_Required",
                translator.toLocale("msg_Username")
            ));
        }
        if (isNull(userRequestDTO.getFirstName())) {
            throw new IllegalArgumentException(translator.toLocale(
                "msg_Field_0_is_Required",
                translator.toLocale("msg_First_Name")
            ));
        }
        if (isNull(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException(translator.toLocale(
                "msg_Field_0_is_Required",
                translator.toLocale("msg_Email")
            ));
        }
    }
}
