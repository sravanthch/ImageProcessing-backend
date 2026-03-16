package com.example.imageprocessing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Void> wakeUp() {
        return ResponseEntity.ok().build();
    }
}
