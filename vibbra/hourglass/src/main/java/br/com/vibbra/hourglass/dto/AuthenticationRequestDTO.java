package br.com.vibbra.hourglass.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDTO {
    private String login;
    private String password;
}
