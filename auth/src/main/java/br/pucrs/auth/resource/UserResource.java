package br.pucrs.auth.resource;

import br.pucrs.auth.dto.request.UserChangePasswordDTO;
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
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        this.service.update(userUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity changePassword(@PathVariable("id") String id, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        this.service.changePassword(id, userChangePasswordDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
