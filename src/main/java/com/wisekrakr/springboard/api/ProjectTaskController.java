package com.wisekrakr.springboard.api;

import com.wisekrakr.springboard.domain.ProjectTask;
import com.wisekrakr.springboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    /**
     * Adds a new projectTask to the database
     * @param projectTask
     * @param result Interface that gets the result of the validation of projectTask.
     *               It will evaluate if we have any errors by passing in a new projectTask
     * @return ResponseEntity represents the whole HTTP response: status code, headers, and body. Because of it,
     *          we can use it to fully configure the HTTP response.
     */
    @PostMapping("")
    public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){

        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError fieldError: result.getFieldErrors()){
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        ProjectTask newProjectTask = projectTaskService.saveOrUpdateProjectTask(projectTask);

        return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllProjectTasks(){
        return projectTaskService.findAll();
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<?> findProjectTaskById(@PathVariable Long task_id){
        ProjectTask projectTask = projectTaskService.findById(task_id);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<?> deleteById(@PathVariable Long task_id){
        projectTaskService.deleteById(task_id);

        return new ResponseEntity<String>("Project Task Deleted!", HttpStatus.OK);
    }
}
