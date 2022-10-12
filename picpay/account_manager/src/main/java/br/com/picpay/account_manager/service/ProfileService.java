package br.com.picpay.account_manager.service;

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

    @Autowired
    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ResponseProfileDTO getProfile(Long id) {
        Profile profile = profileRepository.findById(id);
        ResponseProfileDTO  responseProfileDTO = new ResponseProfileDTO();

        responseProfileDTO.setId(profile.getId());
        responseProfileDTO.setName(profile.getName());
        responseProfileDTO.setCpf(profile.getCpf());
        responseProfileDTO.setEmail(profile.getEmail());
        responseProfileDTO.setShopKeeper(profile.getIsShopKeeper());
        responseProfileDTO.setBalance(profile.getBalance());
        return responseProfileDTO;
    }

    public ResponseProfileDTO createProfile(CreateProfileDTO profileDTO) {
        Profile profile = new Profile();
        ResponseProfileDTO responseProfileDTO = new ResponseProfileDTO();

        profile.setName(profileDTO.getName());
        profile.setCpf(profileDTO.getCpf());
        profile.setEmail(profileDTO.getEmail());
        profile.setPassword(profileDTO.getPassword());
        profile.setIsShopKeeper(profileDTO.isShopKeeper());
        profile.setBalance(profileDTO.getBalance());

        profile = profileRepository.save(profile);

        responseProfileDTO.setId(profile.getId());
        responseProfileDTO.setName(profile.getName());
        responseProfileDTO.setCpf(profile.getCpf());
        responseProfileDTO.setEmail(profile.getEmail());
        responseProfileDTO.setShopKeeper(profile.getIsShopKeeper());
        responseProfileDTO.setBalance(profile.getBalance());

        return responseProfileDTO;
    }

    public ResponseProfileDTO updateProfile(Long id, UpdateProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id);
        ResponseProfileDTO responseProfileDTO = new ResponseProfileDTO();

        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        profile.setIsShopKeeper(profileDTO.isShopKeeper());
        profile.setBalance(profileDTO.getBalance());

        profile = profileRepository.save(profile);

        responseProfileDTO.setId(profile.getId());
        responseProfileDTO.setName(profile.getName());
        responseProfileDTO.setCpf(profile.getCpf());
        responseProfileDTO.setEmail(profile.getEmail());
        responseProfileDTO.setShopKeeper(profile.getIsShopKeeper());
        responseProfileDTO.setBalance(profile.getBalance());

        return responseProfileDTO;
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

}
