package br.com.vibbra.hourglass.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {
    private String token;
    private String user;
}
