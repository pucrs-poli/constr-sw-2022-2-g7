package br.pucrs.auth.resource;

import br.pucrs.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity generateToken(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
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
