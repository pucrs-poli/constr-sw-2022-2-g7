package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.*;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;
import br.pucrs.auth.feign.KeycloakClient;
import br.pucrs.auth.service.AuthService;
import br.pucrs.auth.service.KeycloakService;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
    private final AuthService authService;
    private final KeycloakClient keycloakClient;

    @Value("${client.keycloak.grant-type}")
    private String grantType;

    @Value("${client.keycloak.client-id}")
    private String clientId;

    @Value("${client.keycloak.client-secret}")
    private String clientSecret;

    @Override
    public AuthenticationResponseDTO generateToken(AuthenticationRequestDTO dto) {
        if (Objects.isNull(dto.getUsername()) || Objects.isNull(dto.getPassword())) {
            throw new IllegalArgumentException("Deve ser enviado Usuario e Senha");
        }

        return this.keycloakClient.generateToken(grantType, clientId, clientSecret, dto.getUsername(),
                dto.getPassword());
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        String token = this.authService.getLoggedUserToken();
        return this.keycloakClient.findAllUsers(token);
    }

    @Override
    public void saveUser(UserRequestDTO userRequestDTO) {
        String token = this.authService.getLoggedUserToken();
        this.keycloakClient.saveUser(token, userRequestDTO);
    }

    @Override
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        String token = this.authService.getLoggedUserToken();
        this.keycloakClient.updateUser(token, userUpdateRequestDTO, userUpdateRequestDTO.getId());
    }

    @Override
    public UserResponseDTO findUserById(String id) {
        String token = this.authService.getLoggedUserToken();
        return this.keycloakClient.findUserById(token, id);
    }

    @Override
    public void changePassword(String id, UserChangePasswordDTO userChangePasswordDTO) {
        String token = this.authService.getLoggedUserToken();
        this.keycloakClient.changePassword(token, userChangePasswordDTO, id);
    }

    @Override
    public void deleteUser(String id) {
        String token = this.authService.getLoggedUserToken();
        this.keycloakClient.deleteUser(token, id);
    }

    @Override
    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO dto) {
        String token = this.authService.getLoggedUserToken();
        return this.keycloakClient.refreshToken(grantType, clientId, clientSecret, token);
    }
}
