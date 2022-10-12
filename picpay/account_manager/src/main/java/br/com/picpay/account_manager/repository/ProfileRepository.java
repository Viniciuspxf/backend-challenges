package br.com.picpay.account_manager.repository;

import br.com.picpay.account_manager.model.Profile;
import org.springframework.data.repository.Repository;

public interface ProfileRepository extends Repository<Profile, Long> {
    Profile findById(Long id);
    Profile deleteById(Long id);
    Profile save(Profile profile);
}
