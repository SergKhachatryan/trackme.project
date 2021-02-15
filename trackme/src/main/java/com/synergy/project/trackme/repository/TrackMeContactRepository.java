package com.synergy.project.trackme.repository;

import com.synergy.project.trackme.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrackMeContactRepository extends JpaRepository<Contact, Integer> {

    @Override
    List<Contact> findAll();
}
