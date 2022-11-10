package br.pucrs.classesms.resource;

import br.pucrs.classesms.entity.Call;
import br.pucrs.classesms.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("call")
@RequiredArgsConstructor
public class CallResource {
    private final CallService service;

    @PostMapping
    public ResponseEntity<Call> save(@RequestBody Call call) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(call));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Call> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/find-by-class/{id}")
    public ResponseEntity<Call> findByClassId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByClassId(id));
    }
}
