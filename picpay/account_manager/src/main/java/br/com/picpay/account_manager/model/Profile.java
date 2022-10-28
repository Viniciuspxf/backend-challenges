package br.com.picpay.account_manager.model;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.exception.NotFoundException;
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
//        Profile profile = profileRepository.findById(payee).orElseThrow( () ->new NotFoundException("Perfil não encontrado"));
        this.setBalance(this.getBalance().add(value));
//        profileRepository.save(profile);
    }

    public void decreaseAmount(BigDecimal value) {
//        Profile profile = profileRepository.findById(payer).orElseThrow( );;

        if (this.getBalance().compareTo(value) < 0)
            throw new InvalidTransactionException("Saldo insuficiente");

        this.setBalance(this.getBalance().subtract(value));
//        profileRepository.save(profile);
    }

    public boolean isAbleToTransferMoney() {
//        Profile profile = profileRepository.findById(profileId).orElseThrow( () ->new NotFoundException("Perfil não encontrado"));
        return !this.isShopKeeper();
    }
}
