package br.pucrs.classesms.resource;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("classes")
@RequiredArgsConstructor
public class ClassResource {
    private final ClassService service;

    @PostMapping
    public ResponseEntity<ClassResponseDTO> save(@RequestBody ClassRequestDTO classRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(classRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PutMapping
    public ResponseEntity<ClassResponseDTO> update(ClassRequestDTO classRequestDTO) {
        return ResponseEntity.ok(this.service.update(classRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.deleteById(id));
    }

    @GetMapping("/simple-query")
    public ResponseEntity<List<ClassResponseDTO>> findAllBySimpleQuery(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(this.service.findAllBySimpleQuery(params));
    }

    @GetMapping("/complex-query")
    public ResponseEntity<List<ClassResponseDTO>> findAllByComplexQuery(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(this.service.findAllByComplexQuery(params));
    }
}
