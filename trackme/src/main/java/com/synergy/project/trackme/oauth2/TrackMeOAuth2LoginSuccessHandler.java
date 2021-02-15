package com.synergy.project.trackme.oauth2;

import com.synergy.project.trackme.model.User;
import com.synergy.project.trackme.repository.TrackMeUserRepository;
import com.synergy.project.trackme.service.TrackMeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
public class TrackMeOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    TrackMeUserRepository trackMeUserRepository;

    @Autowired
    TrackMeUserService trackMeUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        TrackMeCustomOAuth2User trackMeCustomOAuth2User = (TrackMeCustomOAuth2User) authentication.getPrincipal();
        String email = trackMeCustomOAuth2User.getEmail();

       Optional<User> user = trackMeUserRepository.findByEmail(email);


        if(user.isPresent()){

            trackMeUserService.updateUser(user.get(),trackMeCustomOAuth2User.getFullName(),trackMeCustomOAuth2User.getName());
        }
       else{

            trackMeUserService.saveUser(trackMeCustomOAuth2User.getEmail(), trackMeCustomOAuth2User.getFullName(), trackMeCustomOAuth2User.getName());
        }


        System.out.println(email);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
