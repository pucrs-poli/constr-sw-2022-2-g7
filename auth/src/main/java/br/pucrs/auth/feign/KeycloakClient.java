package br.pucrs.auth.feign;

import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(value="keycloakClient", url="${client.keycloak.url}")
public interface KeycloakClient {
    @PostMapping(path = "/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponseDTO generateToken(
            @RequestPart("grant_type") String grantType,
            @RequestPart("client_id") String clientId,
            @RequestPart("client_secret") String clientSecret,
            @RequestPart("username") String username,
            @RequestPart("password") String password
    );
}
