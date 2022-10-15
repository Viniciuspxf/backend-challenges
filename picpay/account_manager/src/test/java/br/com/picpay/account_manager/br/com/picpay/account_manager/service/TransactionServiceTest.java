package br.com.picpay.account_manager.br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.mapper.ProfileMapper;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.AuthorizationResponseDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import br.com.picpay.account_manager.repository.ProfileRepository;
import br.com.picpay.account_manager.service.ProfileService;
import br.com.picpay.account_manager.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    @Mock
    private ProfileService profileService;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowInvalidTransactionError() {
        when(profileService.isAbleToTransferMoney(Mockito.any())).thenReturn(false);
        TransactionDTO transaction = new TransactionDTO();
        transaction.setPayer(1L);

        assertThrows(InvalidTransactionException.class, ()->transactionService.makeTransaction(transaction));
        verifyNoInteractions(restTemplate);
        verify(profileService).isAbleToTransferMoney(1L);
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void shouldThrowNotAuthorizedError() {
        when(profileService.isAbleToTransferMoney(Mockito.any())).thenReturn(true);
        TransactionDTO transaction = new TransactionDTO();
        transaction.setPayer(1L);

        AuthorizationResponseDTO authorizationResponse = new AuthorizationResponseDTO();
        authorizationResponse.setMessage("Não autorizado");
        ResponseEntity<AuthorizationResponseDTO> responseEntity = ResponseEntity.badRequest().body(authorizationResponse);

        when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(),  eq(AuthorizationResponseDTO.class)))
                .thenReturn(responseEntity);
        assertThrows(InvalidTransactionException.class, ()->transactionService.makeTransaction(transaction));
        verify(profileService).isAbleToTransferMoney(1L);
        verifyNoMoreInteractions(profileService);
    }




}
