package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.UserChangePasswordRequestDTO;
import br.pucrs.auth.dto.request.UserRequestDTO;
import br.pucrs.auth.dto.request.UserUpdateRequestDTO;
import br.pucrs.auth.dto.response.UserResponseDTO;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserRequestDTO userRequestDTO) {
        this.service.save(userRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        this.service.update(id, userUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity changePassword(@PathVariable("id") String id, @RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        this.service.changePassword(id, userChangePasswordRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.status(204).build();
    }
}
