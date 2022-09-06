package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserRequestDTO userRequestDTO) {
        this.service.save(userRequestDTO);
        return ResponseEntity.ok().build();
    }
}
