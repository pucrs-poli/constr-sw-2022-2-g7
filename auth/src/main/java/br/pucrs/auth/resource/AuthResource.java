package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.request.RefreshTokenRequestDTO;
import br.pucrs.auth.service.AuthService;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthResource {
    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity generateToken(@RequestBody AuthenticationRequestDTO dto) {
        return ResponseEntity.ok(this.userService.login(dto));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequestDTO dto) {
        return ResponseEntity.ok(this.authService.refreshToken(dto));
    }

}
