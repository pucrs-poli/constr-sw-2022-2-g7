package br.pucrs.auth.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

}
