package br.com.vibbra.hourglass.controller;

import br.com.vibbra.hourglass.dto.AuthenticationRequestDTO;
import br.com.vibbra.hourglass.dto.AuthenticationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO)  {
        return ResponseEntity.ok(new AuthenticationResponseDTO());
    }
}
