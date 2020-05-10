package com.wisekrakr.springboard.repository;

import com.wisekrakr.springboard.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {


    ProjectTask getById(Long id);

}
