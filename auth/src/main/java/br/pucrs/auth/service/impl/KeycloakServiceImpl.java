package br.pucrs.auth.service.impl;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.feign.KeycloakClient;
import br.pucrs.auth.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
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

        return this.keycloakClient.generateToken(grantType, clientId, clientSecret, dto.getUsername(), dto.getPassword());
    }
}
