package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.model.dto.AuthorizationResponseDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        AuthorizationResponseDTO response = restTemplate.postForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
               transaction, AuthorizationResponseDTO.class).getBody();

        if (response.getMessage() == "Autorizado") {

        }
        else throw new RuntimeException();
    }
}
