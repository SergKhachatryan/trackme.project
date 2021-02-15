package com.synergy.project.trackme.config;

import com.synergy.project.trackme.oauth2.TrackMeCustomOAuth2UserService;
import com.synergy.project.trackme.oauth2.TrackMeOAuth2LoginSuccessHandler;
import com.synergy.project.trackme.service.TrackMeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class TrackMeSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private TrackMeUserDetailService trackMeUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TrackMeCustomOAuth2UserService oAuth2UserService;

    @Autowired
    private TrackMeOAuth2LoginSuccessHandler trackMeOAuth2LoginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(trackMeUserDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/static**").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/mainpage").permitAll()
                .antMatchers("/projectlist").hasAnyRole("USER")
                .antMatchers("/registration").permitAll()
                .antMatchers("/activate/*").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().and()
                .oauth2Login().loginPage("/login").userInfoEndpoint().userService(oAuth2UserService)
                .and()
                .successHandler(trackMeOAuth2LoginSuccessHandler).defaultSuccessUrl("/projectlist.html", true)
                .and()
                .logout().logoutSuccessUrl("/logout").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}