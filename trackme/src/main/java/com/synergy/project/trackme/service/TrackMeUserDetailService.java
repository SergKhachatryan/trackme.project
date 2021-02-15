package com.synergy.project.trackme.service;

import com.synergy.project.trackme.model.User;
import com.synergy.project.trackme.repository.TrackMeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrackMeUserDetailService implements UserDetailsService {

    @Autowired
    private TrackMeUserRepository trackMeUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = trackMeUserRepository.findByUsername(username);
        if(userByUsername.isPresent()){
            return userByUsername.map(TrackMeUserDetails::new).get();
        }
        Optional<User> userByEmail = trackMeUserRepository.findByEmail(username);
        if(userByEmail.isPresent()){
            return userByEmail.map(TrackMeUserDetails::new).get();
        }

        throw  new UsernameNotFoundException("Not found: " + username);
    }
}
