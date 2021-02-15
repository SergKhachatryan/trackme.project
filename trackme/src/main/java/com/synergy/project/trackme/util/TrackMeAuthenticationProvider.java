package com.synergy.project.trackme.util;

import com.synergy.project.trackme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class TrackMeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

        public Authentication authenticate(Authentication authentication) throws AuthenticationException
        {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();

            User user = (User) userDetailsService.loadUserByUsername(username);

            if(user != null && (user.getUsername().equals(username) || user.getUsername().equals(username)))
            {
                if(!passwordEncoder.matches(password, user.getPassword()))
                {
                    throw new BadCredentialsException("Wrong password");
                }

                Collection<? extends GrantedAuthority> authorities = user.getRoles();

                return new UsernamePasswordAuthenticationToken(user, password, authorities);
            }
            else
                throw new BadCredentialsException("Username not found");
        }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
