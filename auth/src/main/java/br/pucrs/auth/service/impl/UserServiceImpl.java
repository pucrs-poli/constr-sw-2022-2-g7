package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.*;
import br.pucrs.auth.dto.response.UserResponseDTO;
import br.pucrs.auth.enums.UserGroup;
import br.pucrs.auth.i18n.Translator;
import br.pucrs.auth.service.KeycloakService;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final KeycloakService keycloakService;
    private final Translator translator;

    private static final String PASSWORD_TYPE = "password";

    @Override
    public List<UserResponseDTO> findAll() {
        return this.keycloakService.findAllUsers();
    }

    @Override
    public void save(UserRequestDTO userRequestDTO) {
        this.validate(userRequestDTO);

        KeycloakUserRequestDTO keycloakUserRequestDTO = KeycloakUserRequestDTO.builder()
                .username(userRequestDTO.getUsername())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .enabled(true)
                .email(userRequestDTO.getEmail())
                .groups(this.getGroups(userRequestDTO))
                .credentials(this.buildKeycloakUserCredentialsRequestDTO(userRequestDTO.getPassword()))
                .build();

        this.keycloakService.saveUser(keycloakUserRequestDTO);
    }

    @Override
    public void update(String id, UserUpdateRequestDTO userUpdateRequestDTO) {
        if (isNull(id)) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_id")
            ));
        }
        this.keycloakService.updateUser(id, userUpdateRequestDTO);
    }

    @Override
    public UserResponseDTO findById(String id) {
        return this.keycloakService.findUserById(id);
    }

    @Override
    public void changePassword(String id, UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        if (isNull(userChangePasswordRequestDTO.getPassword())) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_Password")
            ));
        }
        this.keycloakService.changePassword(id, userChangePasswordRequestDTO);
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
        if (isNull(userRequestDTO.getPassword())) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_Password")
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

    private List<String> getGroups(UserRequestDTO userRequestDTO) {
        return userRequestDTO.getGroups().stream().map(UserGroup::getDescription).collect(Collectors.toList());
    }

    private List<KeycloakUserCredentialsRequestDTO> buildKeycloakUserCredentialsRequestDTO(String password) {
        return Collections.singletonList(KeycloakUserCredentialsRequestDTO.builder()
                .type(PASSWORD_TYPE)
                .value(password)
                .temporary(false)
                .build());
    }
}
