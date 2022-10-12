package br.com.picpay.account_manager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDTO {
    private BigDecimal value;
    private Long payer;
    private Long payee;

}
