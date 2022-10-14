package br.com.picpay.account_manager.service;

import br.com.picpay.account_manager.exception.InvalidTransactionException;
import br.com.picpay.account_manager.exception.NotFoundException;
import br.com.picpay.account_manager.exception.RequestFailedException;
import br.com.picpay.account_manager.mapper.ProfileMapper;
import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.CreateProfileDTO;
import br.com.picpay.account_manager.model.dto.ResponseProfileDTO;
import br.com.picpay.account_manager.model.dto.UpdateProfileDTO;
import br.com.picpay.account_manager.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;
    private ProfileMapper profileMapper;

    @Autowired
    ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public ResponseProfileDTO getProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow( () ->new RequestFailedException("Perfil não criado"));;
        ResponseProfileDTO  responseProfileDTO = profileMapper.toResponseProfileDTO(profile);
        return responseProfileDTO;
    }

    public ResponseProfileDTO createProfile(CreateProfileDTO profileDTO) {
        Profile profile = profileMapper.toProfile(profileDTO);
        profile = profileRepository.save(profile).orElseThrow( () ->new RequestFailedException("Perfil não criado"));
        ResponseProfileDTO responseProfileDTO = profileMapper.toResponseProfileDTO(profile);

        return responseProfileDTO;
    }

    public ResponseProfileDTO updateProfile(Long id, UpdateProfileDTO profileDTO) {
        profileRepository.findById(id).orElseThrow(() ->new NotFoundException("Perfil não encontrado"));
        Profile profile = profileMapper.toProfile(profileDTO);
        profile.setId(id);
        profile = profileRepository.save(profile).orElseThrow(() ->new NotFoundException("Perfil não encontrado"));

        ResponseProfileDTO responseProfileDTO = profileMapper.toResponseProfileDTO(profile);

        return responseProfileDTO;
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public void increaseAmount(Long payee, BigDecimal value) {
        Profile profile = profileRepository.findById(payee).orElseThrow( () ->new NotFoundException("Perfil não encontrado"));
        profile.setBalance(profile.getBalance().add(value));
        profileRepository.save(profile);
    }

    public void decreaseAmount(Long payer, BigDecimal value) {
        Profile profile = profileRepository.findById(payer).orElseThrow( () ->new NotFoundException("Perfil não encontrado"));;

        if (profile.getBalance().compareTo(value) < 0)
            throw new InvalidTransactionException("Saldo insuficiente");

        profile.setBalance(profile.getBalance().subtract(value));
        profileRepository.save(profile);
    }

    public boolean isAbleToTransferMoney(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow( () ->new NotFoundException("Perfil não encontrado"));
        return !profile.isShopKeeper();
    }
}
