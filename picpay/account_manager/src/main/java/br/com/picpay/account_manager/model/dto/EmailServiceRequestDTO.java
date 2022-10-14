package br.com.picpay.account_manager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailServiceRequestDTO {
    private String email;
    private String message;
}
