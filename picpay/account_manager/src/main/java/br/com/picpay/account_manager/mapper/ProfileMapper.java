package br.com.picpay.account_manager.mapper;

import br.com.picpay.account_manager.model.Profile;
import br.com.picpay.account_manager.model.dto.CreateProfileDTO;
import br.com.picpay.account_manager.model.dto.ResponseProfileDTO;
import br.com.picpay.account_manager.model.dto.UpdateProfileDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(CreateProfileDTO profileDTO);
    Profile toProfile(UpdateProfileDTO profileDTO);
    ResponseProfileDTO toResponseProfileDTO(Profile profile);
}