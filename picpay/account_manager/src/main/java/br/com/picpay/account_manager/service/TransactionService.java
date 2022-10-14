package br.com.picpay.account_manager.service;

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
            throw new RuntimeException("Este tipo de usuário não pode transferir dinheiro.");

        AuthorizationResponseDTO response = restTemplate.postForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
               transaction, AuthorizationResponseDTO.class).getBody();

        if (Objects.equals(response.getMessage(), "Autorizado")) {
            profileService.decreaseAmount(transaction.getPayer(),transaction.getValue());
            profileService.increaseAmount(transaction.getPayee(), transaction.getValue());
        }
        else throw new RuntimeException("Não autorizado");
    }
}
