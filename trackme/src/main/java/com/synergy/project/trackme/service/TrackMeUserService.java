package com.synergy.project.trackme.service;

import com.synergy.project.trackme.model.AuthenticationProvider;
import com.synergy.project.trackme.model.Role;
import com.synergy.project.trackme.model.User;
import com.synergy.project.trackme.repository.TrackMeUserRepository;
import com.synergy.project.trackme.util.TrackMeMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrackMeUserService {

    @Autowired
    User user;

    @Autowired
    private TrackMeUserRepository trackMeUserRepository;

    @Autowired
    private TrackMeMailSender trackMeMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String userName){
        return tryGetUser(trackMeUserRepository.findByUsername(userName));
    };

    public User findByActivationCode(String code){
      return tryGetUser(trackMeUserRepository.findByActivationCode(code));
    };

    public User findByEmail(String email){
       return tryGetUser(trackMeUserRepository.findByEmail(email));
    };

    User tryGetUser(Optional<User> user){

        if (user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("User not exist");
    }


    public boolean tryAddUser(User user){

        Optional<User> UserFromDb = trackMeUserRepository.findByUsername(user.getUsername());

        if(UserFromDb.isPresent()){
           return false;
        }

        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        user.setEmail(user.getEmail());
        user.setActivationCode(UUID.randomUUID().toString());
        trackMeUserRepository.save(user);

        if(!user.getEmail().isEmpty() && user.getEmail() != null){
            String message = String.format(
                    "Hello, %s! \n" + "Please, visit next link: http:localhost:8080/activate/%s",user.getUsername(),user.getActivationCode());

            trackMeMailSender.send(user.getEmail(),user.getActivationCode(),message);
        }
        return true;
    }

    public boolean tryActivateUser(String code) {
        Optional<User> user =  trackMeUserRepository.findByActivationCode(code);
        if(user.isPresent()){
            user.get().setActivationCode(null);
            trackMeUserRepository.save(user.get());
            return true;
        }
        return false;
    }

    public void updateUser(User user, String username, String name) {

        user.setUsername(username);
        user.setName(name);
        user.setAuthenticationProvider(AuthenticationProvider.GOOGLE);
        trackMeUserRepository.save(user);
    }

    public void saveUser(String email, String fullName, String name) {

        user = new User();
        user.setActive(true);
        user.setAuthenticationProvider(AuthenticationProvider.GOOGLE);
        user.setEmail(email);
        user.setUsername(fullName);
        user.setName(name);
        user.setPassword("hashed value");
        trackMeUserRepository.save(user);
    }
}
