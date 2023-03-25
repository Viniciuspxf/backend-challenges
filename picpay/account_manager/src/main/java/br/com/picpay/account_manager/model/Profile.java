package br.com.picpay.account_manager.model;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isShopKeeper;

    @Column(nullable = false)
    private BigDecimal balance;

    public void increaseAmount(BigDecimal value) {
        this.setBalance(this.getBalance().add(value));
    }

    public void decreaseAmount(BigDecimal value) {
        if (this.getBalance().compareTo(value) < 0)
            throw new InvalidTransactionException("Saldo insuficiente");

        this.setBalance(this.getBalance().subtract(value));
    }

    public boolean isAbleToTransferMoney() {
        return !this.isShopKeeper();
    }
}
