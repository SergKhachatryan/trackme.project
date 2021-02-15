package com.synergy.project.trackme.repository;

import com.synergy.project.trackme.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface TrackMeProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findById(int id);

    List<Project> findAll();

}
