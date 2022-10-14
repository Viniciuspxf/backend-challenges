package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.exception.RequestFailedException;
import br.com.picpay.account_manager.model.dto.AuthorizationResponseDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class TransactionService {
    ProfileService profileService;
    RestTemplate restTemplate;

    @Autowired
    TransactionService(ProfileService profileService, RestTemplate restTemplate) {
        this.profileService = profileService;
        this.restTemplate = restTemplate;
    }
    public void makeTransaction(TransactionDTO transaction) {
        if (!profileService.isAbleToTransferMoney(transaction.getPayer()))
            throw new InvalidTransactionException("Este tipo de usuário não pode transferir dinheiro.");

        AuthorizationResponseDTO response;
        try {
            response = restTemplate.postForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                    transaction, AuthorizationResponseDTO.class).getBody();
        }
        catch (Exception e) {
            throw new RequestFailedException("Não foi possível validar a transação.");
        }

        if (Objects.equals(response.getMessage(), "Autorizado")) {
            profileService.decreaseAmount(transaction.getPayer(),transaction.getValue());
            profileService.increaseAmount(transaction.getPayee(), transaction.getValue());
        }
        else throw new InvalidTransactionException("Não autorizado");
    }
}
