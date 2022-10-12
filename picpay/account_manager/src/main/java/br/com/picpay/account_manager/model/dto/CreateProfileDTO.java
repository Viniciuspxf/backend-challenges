package br.com.picpay.account_manager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProfileDTO {
    private String name;
    private String cpf;
    private String email;
    private String password;
    private boolean isShopKeeper;
    private BigDecimal balance;
}
