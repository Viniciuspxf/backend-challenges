package br.com.vibbra.hourglass.controller;

import br.com.vibbra.hourglass.dto.TimeRequestDTO;
import br.com.vibbra.hourglass.dto.TimeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/times")
public class TimeController {

    @PostMapping
    public ResponseEntity<TimeResponseDTO> registerTime(@RequestBody TimeRequestDTO timeRequestDTO) {
        return ResponseEntity.ok(new TimeResponseDTO());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<TimeResponseDTO> getTime(@PathVariable int projectId) {
        return ResponseEntity.ok(new TimeResponseDTO());
    }

    @PutMapping("/{timeId}")
    public ResponseEntity<TimeResponseDTO> updateTime(@PathVariable int timeId) {
        return ResponseEntity.ok(new TimeResponseDTO());
    }

}
