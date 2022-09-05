package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final KeycloakService keycloakService;
    private final Translator translator;

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO dto) {
        if (Objects.isNull(dto.getUsername()) || Objects.isNull(dto.getPassword())) {
            throw new AuthBadRequestException(translator.toLocale("msg_Invalid_User_Or_Password"));
        }

        return this.keycloakService.generateToken(dto);
    }

    @Override
    public List<UserResponseDTO> findAll(String auth) {
        return this.keycloakService.findAllUsers(auth);
    }
}
