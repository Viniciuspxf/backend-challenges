package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.exception.RequestFailedException;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture.runAsync(()-> sendTransactionMessage(transaction));
    }

    public void sendTransactionMessage(TransactionDTO transaction) {
        ResponseProfileDTO payer = profileService.getProfile(transaction.getPayer());
        ResponseProfileDTO payee = profileService.getProfile(transaction.getPayee());
        EmailServiceRequestDTO request = new EmailServiceRequestDTO(payee.getEmail(),
                "Você recebeu R$" + transaction.getValue() + " do " + payer.getName());


        EmailServiceResponseDTO response;
        try {
            response = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",
                    request, EmailServiceResponseDTO.class,
                    EmailServiceResponseDTO.class).getBody();

            if (!response.getMessage().equals("Success"))
                throw new RuntimeException("Serviço externo não conseguiu enviar a mensagem");
            System.out.println("Mensagem enviada com sucesso");
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            sendTransactionMessage(transaction);
        }
    }
}
