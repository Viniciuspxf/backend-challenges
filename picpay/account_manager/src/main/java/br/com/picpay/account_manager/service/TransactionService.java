package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public void makeTransaction(TransactionDTO transaction) {
        System.out.println(transaction.getValue());
    }
}
