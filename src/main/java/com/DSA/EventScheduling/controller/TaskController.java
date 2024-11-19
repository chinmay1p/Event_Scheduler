package com.DSA.EventScheduling.controller;

import com.DSA.EventScheduling.model.Task;
import com.DSA.EventScheduling.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public void add(@RequestBody Task task) {
        taskService.add(task);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String name) {
        taskService.delete(name);
    }

    @PutMapping("/update")
    public void update(@RequestBody Task task) {
        taskService.update(task);
    }

    @GetMapping("/next")
    public Task getnxt() {
        return taskService.getnxt();
    }

    @GetMapping("/search")
    public Task search(@RequestParam String name) {
        return taskService.search(name);
    }

    @GetMapping("/all")
    public List<Task> getall() {
        return taskService.getall();
    }
}
