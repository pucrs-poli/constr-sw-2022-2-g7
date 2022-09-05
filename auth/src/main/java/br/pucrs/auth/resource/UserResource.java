package br.pucrs.auth.resource;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.auth.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    @GetMapping
    public ResponseEntity getAllUsers(@RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(this.service.findAll(auth));
    }
}
