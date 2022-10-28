package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.RequestFailedException;
import br.com.picpay.account_manager.model.dto.AuthorizationResponseDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class AuthorizationService {
    private static final String URL = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
    private final RestTemplate restTemplate;

    @Autowired
    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public boolean isAuthorized(TransactionDTO transaction) {
        AuthorizationResponseDTO response;
        
        try {
            response = restTemplate.postForEntity(URL,
                    transaction, AuthorizationResponseDTO.class).getBody();
        }
        catch (Exception e) {
            throw new RequestFailedException("Não foi possível validar a transação.");
        }

        return Objects.equals(response.getMessage(), "Autorizado");
    }
}
