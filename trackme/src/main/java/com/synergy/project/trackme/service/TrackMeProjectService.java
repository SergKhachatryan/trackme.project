package com.synergy.project.trackme.service;

import com.synergy.project.trackme.Dto.ContactCreationDto;
import com.synergy.project.trackme.model.Project;
import com.synergy.project.trackme.repository.TrackMeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrackMeProjectService {

    @Autowired
    TrackMeProjectRepository trackMeProjectRepository;

    public void addProject(Project project) {
        trackMeProjectRepository.save(project);
    }

    public void editProject(Project project ,ContactCreationDto contactCreationDto) {
        project.setContacts(contactCreationDto.getContacts());
        trackMeProjectRepository.save(project);
    }

    public void deleteProject(int id) {
        trackMeProjectRepository.deleteById(id);
    }

    public Project getProject(int id) {
        Optional<Project> project = trackMeProjectRepository.findById(id);
        return project.orElse(null);
    }
}
