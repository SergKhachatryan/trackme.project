package com.synergy.project.trackme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Component
@Scope("prototype")
@Table(name ="usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank(message = "password can't be empty")
    @Length(min = 8, message = "password mast be at least 8 symbols" )
    private String password;
    private boolean active;
    @NotBlank(message = "email can't be empty")
    @Email(message = "it's not an email")
    private String email;
    private String activationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authenticationProvider;


   // private String userpic;
   // private String gender;
   // private String locale;
  //  private LocalDateTime lastVisit;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "usr_role", joinColumns = @JoinColumn(name = "usr_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
