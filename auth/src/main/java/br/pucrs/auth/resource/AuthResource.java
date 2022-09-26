package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity generateToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return ResponseEntity.ok(this.authService.login(authenticationRequestDTO));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken(@RequestBody String refreshToken) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshToken));
    }

    @GetMapping("/user-info")
    public ResponseEntity getUserInfo(@RequestBody String token) {
        return ResponseEntity.ok(this.authService.getUserInfo(token));
    }

    @GetMapping("/token-introspect")
    public ResponseEntity tokenIntrospect(@RequestBody String token) {
        return ResponseEntity.ok(this.authService.tokenIntrospect(token));
    }
}
