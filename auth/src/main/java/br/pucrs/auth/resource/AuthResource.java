package br.pucrs.auth.resource;

import br.pucrs.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity generateToken(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(this.authService.login(username, password));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken(@RequestParam("refresh-token") String refreshToken) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshToken));
    }

    @GetMapping("/user-info")
    public ResponseEntity getUserInfo(@RequestParam("token") String token) {
        return ResponseEntity.ok(this.authService.getUserInfo(token));
    }

    @GetMapping("/token-introspect")
    public ResponseEntity tokenIntrospect(@RequestParam("token") String token) {
        return ResponseEntity.ok(this.authService.tokenIntrospect(token));
    }
}
