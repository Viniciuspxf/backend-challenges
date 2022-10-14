package br.com.picpay.account_manager.repository;

import br.com.picpay.account_manager.model.Profile;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProfileRepository extends Repository<Profile, Long> {
    Optional<Profile> findById(Long id);
    void deleteById(Long id);
    Optional<Profile> save(Profile profile);
}
