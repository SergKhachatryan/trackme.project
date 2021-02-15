package com.synergy.project.trackme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Title can't be empty")
    private String title;
    @NotBlank(message = "Status can't be empty")
    @Email(message = "It's not an email")
    private String status;

    @OneToMany(mappedBy = "owner" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Contact> contacts;

}
