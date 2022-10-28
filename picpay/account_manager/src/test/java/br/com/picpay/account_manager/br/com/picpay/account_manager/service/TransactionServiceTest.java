package br.com.picpay.account_manager.br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import br.com.picpay.account_manager.repository.ProfileRepository;
import br.com.picpay.account_manager.service.AuthorizationService;
import br.com.picpay.account_manager.service.MessagesService;
import br.com.picpay.account_manager.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    @Mock
    private MessagesService messagesService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private AuthorizationService authorizationService;

    private TransactionDTO transaction;

    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        transaction = new TransactionDTO();
        transaction.setPayer(1L);
        transaction.setPayee(2L);
    }

    @Test
    public void shouldThrowInvalidTransactionError() {
        Profile profile = mock(Profile.class);
        when(profile.isAbleToTransferMoney()).thenReturn(false);
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);
        when(profileRepository.findById(2L)).thenReturn(Optional.of(new Profile()));

        assertThrows(InvalidTransactionException.class, ()->transactionService.makeTransaction(transaction));
        verifyNoInteractions(authorizationService);
        verify(profile).isAbleToTransferMoney();
    }

    @Test
    public void shouldThrowNotAuthorizedError() {
        Profile profile = mock(Profile.class);
        when(profile.isAbleToTransferMoney()).thenReturn(true);
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);
        when(profileRepository.findById(2L)).thenReturn(Optional.of(new Profile()));

        when(profile.isAbleToTransferMoney()).thenReturn(true);
        when(authorizationService.isAuthorized(Mockito.any())).thenReturn(false);
        assertThrows(InvalidTransactionException.class, ()->transactionService.makeTransaction(transaction));
    }




}
