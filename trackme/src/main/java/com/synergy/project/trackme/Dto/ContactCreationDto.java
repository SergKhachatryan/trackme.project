package com.synergy.project.trackme.Dto;

import com.synergy.project.trackme.model.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.LinkedList;
import java.util.List;

// Class wrapper needed to get the list of contacts from form with thymeleaf

@Data
@NoArgsConstructor
public class ContactCreationDto {

    private List<Contact> contacts = new LinkedList<>();

    public ContactCreationDto(List<Contact> contactList) {
        this.contacts = contactList;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }


}
