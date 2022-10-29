package br.com.picpay.account_manager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResponseProfileDTO {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private boolean isShopKeeper;
    private BigDecimal balance;
}
