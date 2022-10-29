package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.NotFoundException;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.EmailServiceRequestDTO;
import br.com.picpay.account_manager.model.dto.EmailServiceResponseDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import br.com.picpay.account_manager.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessagesService {

    private final ProfileRepository profileRepository;
    private final RestTemplate restTemplate;
    private static final String URL = "http://o4d9z.mocklab.io/notify";

    @Autowired
    public MessagesService(ProfileRepository profileRepository, RestTemplate restTemplate) {
        this.profileRepository = profileRepository;
        this.restTemplate = restTemplate;
    }
    public void sendTransactionMessage(TransactionDTO transaction) {
        EmailServiceRequestDTO request = getEmailServiceRequestDTO(transaction);
        postMessage(request);
    }

    private void postMessage(EmailServiceRequestDTO request) {
        EmailServiceResponseDTO response;
        try {
            response = restTemplate.postForEntity(URL,
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
            postMessage(request);
        }
    }

    private EmailServiceRequestDTO getEmailServiceRequestDTO(TransactionDTO transaction) {
        Profile payer = profileRepository.findById(transaction.getPayer())
                .orElseThrow(() ->new NotFoundException("Perfil não encontrado"));

        Profile payee = profileRepository.findById(transaction.getPayee())
                .orElseThrow(() ->new NotFoundException("Perfil não encontrado"));

        EmailServiceRequestDTO request = new EmailServiceRequestDTO(payee.getEmail(),
                "Você recebeu R$" + transaction.getValue() + " do " + payer.getName());
        return request;
    }
}
