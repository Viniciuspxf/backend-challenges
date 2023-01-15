package br.com.vibbra.hourglass.controller;

import br.com.vibbra.hourglass.dto.UserRequestDTO;
import br.com.vibbra.hourglass.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(new UserResponseDTO());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable int userId) {
        return  ResponseEntity.ok(new UserResponseDTO());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable int userId,
                                                        @RequestBody UserRequestDTO userRequestDTO) {
        return  ResponseEntity.ok(new UserResponseDTO());
    }


}
