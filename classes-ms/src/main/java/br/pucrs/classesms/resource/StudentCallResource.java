package br.pucrs.classesms.resource;

import br.pucrs.classesms.dto.request.StudentCallRequestDTO;
import br.pucrs.classesms.dto.response.StudentCallResponseDTO;
import br.pucrs.classesms.service.StudentCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student-call")
@RequiredArgsConstructor
public class StudentCallResource {
    private final StudentCallService service;

    @PostMapping
    public ResponseEntity<StudentCallResponseDTO> save(@RequestBody StudentCallRequestDTO studentCallRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentCallRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StudentCallResponseDTO>> findAllByClassId(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findAllByClassId(id));
    }
}
