package br.com.picpay.account_manager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProfileDTO {
    private String name;
    private String email;
    private boolean isShopKeeper;
    private String password;
    private BigDecimal balance;
}
