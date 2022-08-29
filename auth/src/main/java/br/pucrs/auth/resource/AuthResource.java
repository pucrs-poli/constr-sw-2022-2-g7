package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final KeycloakService service;

    @PostMapping("/token")
    public ResponseEntity generateToken(@RequestBody AuthenticationRequestDTO dto) {
        return ResponseEntity.ok(this.service.generateToken(dto));
    }
}
