package br.pucrs.classesms.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classes")
@RequiredArgsConstructor
public class ClassResource {
    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().build();
    }
}
