package br.pucrs.auth.feign;

import br.pucrs.auth.dto.request.KeycloakUserRequestDTO;
import br.pucrs.auth.dto.request.UserChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.auth.dto.response.UserInfoResponseDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(value = "keycloakClient", url = "${client.keycloak.url}")
public interface KeycloakClient {
    @PostMapping(path = "/realms/constr-sw-2022-2/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponseDTO generateToken(
        @RequestPart("grant_type") String grantType,
        @RequestPart("client_id") String clientId,
        @RequestPart("client_secret") String clientSecret,
        @RequestPart("username") String username,
        @RequestPart("password") String password);

    @PostMapping(path = "/realms/constr-sw-2022-2/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponseDTO refreshToken(
        @RequestPart("grant_type") String grantType,
        @RequestPart("client_id") String clientId,
        @RequestPart("client_secret") String clientSecret,
        @RequestPart("refresh_token") String refreshToken);

    @PostMapping(path = "/realms/constr-sw-2022-2/protocol/openid-connect/token/introspect", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectResponseDTO tokenIntrospect(
        @RequestPart("client_id") String clientId,
        @RequestPart("client_secret") String clientSecret,
        @RequestPart("token") String token
    );

    @PostMapping(path = "/realms/constr-sw-2022-2/protocol/openid-connect/userinfo", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    UserInfoResponseDTO getUserInfo(@RequestHeader("Authorization") String token);

    @GetMapping(path = "/admin/realms/constr-sw-2022-2/users", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    List<UserResponseDTO> findAllUsers(@RequestHeader("Authorization") String token);

    @PostMapping(path = "/admin/realms/constr-sw-2022-2/users")
    void saveUser(@RequestHeader("Authorization") String token, @RequestBody KeycloakUserRequestDTO keycloakUserRequestDTO);

    @PutMapping("/admin/realms/constr-sw-2022-2/users/{id}")
    void updateUser(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO, @PathVariable("id") String id);

    @GetMapping("/admin/realms/constr-sw-2022-2/users/{id}")
    UserResponseDTO findUserById(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/admin/realms/constr-sw-2022-2/users/{id}/reset-password")
    void changePassword(@RequestHeader("Authorization") String token, @RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO, @PathVariable("id") String id);

    @DeleteMapping("/admin/realms/constr-sw-2022-2/users/{id}")
    void deleteUser(@RequestHeader("Authorization") String token, @PathVariable("id") String id);
}
