package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.KeyCloakChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.KeycloakUserRequestDTO;
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
    public AuthenticationResponseDTO generateToken(String username, String password) {
        return this.keycloakClient.generateToken(GRANT_TYPE_PASSWORD, clientId, clientSecret, username, password);
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
        this.validateTokenExists(token);

        return this.keycloakClient.getUserInfo(token);
    }

    @Override
    public TokenIntrospectResponseDTO tokenIntrospect(String token) {
        this.validateTokenExists(token);
        return this.keycloakClient.tokenIntrospect(clientId, clientSecret, token.replaceFirst("Bearer ", ""));
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        return this.keycloakClient.findAllUsers(token);
    }

    @Override
    public void saveUser(KeycloakUserRequestDTO keycloakUserRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        this.keycloakClient.saveUser(token, keycloakUserRequestDTO);
    }

    @Override
    public void updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        this.keycloakClient.updateUser(token, userUpdateRequestDTO, id);
    }

    @Override
    public UserResponseDTO findUserById(String id) {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        return this.keycloakClient.findUserById(token, id);
    }

    @Override
    public void changePassword(String id, KeyCloakChangePasswordRequestDTO keyCloakChangePasswordRequestDTO) {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        this.keycloakClient.changePassword(token, keyCloakChangePasswordRequestDTO, id);
    }

    @Override
    public void deleteUser(String id) {
        String token = AuthUtils.getLoggedUserToken();
        this.validateTokenExists(token);
        this.keycloakClient.deleteUser(token, id);
    }

    private void validateTokenExists(String token) {
        if (isNull(token)) {
            throw new IllegalArgumentException(translator.toLocale(
                    "msg_Field_0_is_Required",
                    translator.toLocale("msg_Token")
            ));
        }
    }
}
