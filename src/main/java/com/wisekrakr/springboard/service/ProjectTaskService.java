package com.wisekrakr.springboard.service;

import com.wisekrakr.springboard.domain.ProjectTask;
import com.wisekrakr.springboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is the routing mechanism and should have minimal logic.
 * The middleman between the repository and the controller
 */

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask){

        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAll(){
        return projectTaskRepository.findAll();
    }

    public ProjectTask findById(Long id){
        return projectTaskRepository.getById(id);
    }

    public void deleteById(Long id){
        projectTaskRepository.deleteById(id);

    }
}
