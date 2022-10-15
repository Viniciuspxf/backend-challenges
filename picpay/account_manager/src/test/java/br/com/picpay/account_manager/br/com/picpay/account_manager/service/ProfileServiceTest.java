package br.com.picpay.account_manager.br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.exception.NotFoundException;
import br.com.picpay.account_manager.mapper.ProfileMapper;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.ResponseProfileDTO;
import br.com.picpay.account_manager.repository.ProfileRepository;
import br.com.picpay.account_manager.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
public class ProfileServiceTest {
    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowError() {
        Profile profile = new Profile();
        profile.setBalance(new BigDecimal(3));
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);

        assertThrows(InvalidTransactionException.class, ()-> profileService.decreaseAmount(1L, new BigDecimal(30)));
    }

    @Test
    public void shouldDecreaseAmount() {
        Profile profile = new Profile();
        profile.setBalance(new BigDecimal(3));
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);

        profileService.decreaseAmount(1L, new BigDecimal(3));
    }


    @Test
    public void shouldReturnTrue() {
        Profile profile = new Profile();
        profile.setShopKeeper(false);
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);

        assertEquals(true, profileService.isAbleToTransferMoney(1L));
    }

    @Test
    public void shouldReturnFalse() {
        Profile profile = new Profile();
        profile.setShopKeeper(true);
        Optional<Profile> optionalProfile = Optional.of(profile);

        when(profileRepository.findById(1L)).thenReturn(optionalProfile);

        assertEquals(false, profileService.isAbleToTransferMoney(1L));
    }

}
