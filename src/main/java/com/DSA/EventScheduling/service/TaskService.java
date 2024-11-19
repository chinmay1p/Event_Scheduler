package com.DSA.EventScheduling.service;

import com.DSA.EventScheduling.model.AVLTree;
import com.DSA.EventScheduling.model.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final AVLTree tree = new AVLTree();

    public void add(Task task) {
        tree.insert(task);
    }

    public void delete(String name) {
        Task task = search(name);
//        if (task != null) {
            tree.deletes(task);

    }

    public void update(Task task) {
        delete(task.getName());
        tree.insert(task);
    }

    public Task getnxt() {
        return tree.findnxt();
    }

    public Task search(String name) {
        return tree.searchs(name);
    }

    public List<Task> getall() {
        return tree.getsall();
    }
}
