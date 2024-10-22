package com.br.todolist.services;


import com.br.todolist.model.Task;
import com.br.todolist.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> finTaskById(Long id){
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateTaskById(Task task, Long id){
        return taskRepository.findById(id)
                .map(taskToUpadate ->{
                    taskToUpadate.setTitle(task.getTitle());
                    taskToUpadate.setDescription(task.getDescription());
                    taskToUpadate.setDeadLine(task.getDeadLine());
                    Task update = taskRepository.save(taskToUpadate);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById (Long id){
        return taskRepository.findById(id)
                .map(taskToDelete ->{
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                } ).orElse(ResponseEntity.notFound().build());
    }

}

























