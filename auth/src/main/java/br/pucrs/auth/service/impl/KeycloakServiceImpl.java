package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.UserChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.auth.dto.response.UserInfoResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;
import br.pucrs.auth.feign.KeycloakClient;
import br.pucrs.auth.i18n.Translator;
import br.pucrs.auth.service.KeycloakService;
import br.pucrs.auth.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakClient keycloakClient;

    private final Translator translator;

    @Value("${client.keycloak.client-id}")
    private String clientId;

    @Value("${client.keycloak.client-secret}")
    private String clientSecret;

    private static final String GRANT_TYPE_PASSWORD = "password";

    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    @Override
    public AuthenticationResponseDTO generateToken(AuthenticationRequestDTO authenticationRequestDTO) {
        if (isNull(authenticationRequestDTO.getUsername()) || isNull(authenticationRequestDTO.getPassword())) {
            throw new IllegalArgumentException("Deve ser enviado Usuario e Senha");
        }

        return this.keycloakClient.generateToken(GRANT_TYPE_PASSWORD, clientId, clientSecret, authenticationRequestDTO.getUsername(),
            authenticationRequestDTO.getPassword());
    }

    @Override
    public AuthenticationResponseDTO refreshToken(String refreshToken) {
        if (isNull(refreshToken)) {
            throw new IllegalArgumentException(translator.toLocale(
                "msg_Field_0_is_Required",
                translator.toLocale("msg_Refresh_Token")
            ));
        }

        return this.keycloakClient.refreshToken(GRANT_TYPE_REFRESH_TOKEN, clientId, clientSecret, refreshToken);
    }

    @Override
    public UserInfoResponseDTO getUserInfo(String token) {
        if (isNull(token)) {
            throw new IllegalArgumentException(translator.toLocale(
                "msg_Field_0_is_Required",
                translator.toLocale("msg_Token")
            ));
        }

        return this.keycloakClient.getUserInfo("Bearer " + token);
    }

    @Override
    public TokenIntrospectResponseDTO tokenIntrospect(String token) {
        if (isNull(token)) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_Token")
            ));
        }

        return this.keycloakClient.tokenIntrospect(clientId, clientSecret, token);
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        String token = AuthUtils.getLoggedUserToken();
        return this.keycloakClient.findAllUsers(token);
    }

    @Override
    public void saveUser(UserRequestDTO userRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.keycloakClient.saveUser(token, userRequestDTO);
    }

    @Override
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.keycloakClient.updateUser(token, userUpdateRequestDTO, userUpdateRequestDTO.getId());
    }

    @Override
    public UserResponseDTO findUserById(String id) {
        String token = AuthUtils.getLoggedUserToken();
        return this.keycloakClient.findUserById(token, id);
    }

    @Override
    public void changePassword(String id, UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.keycloakClient.changePassword(token, userChangePasswordRequestDTO, id);
    }

    @Override
    public void deleteUser(String id) {
        String token = AuthUtils.getLoggedUserToken();
        this.keycloakClient.deleteUser(token, id);
    }
}
