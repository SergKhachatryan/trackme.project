package com.synergy.project.trackme.repository;

import com.synergy.project.trackme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TrackMeUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByActivationCode(String code);

    Optional<User> findByEmail(String email);
}
