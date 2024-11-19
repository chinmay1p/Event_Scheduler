package com.DSA.EventScheduling.repository;
import com.DSA.EventScheduling.model.Task;
import java.util.Optional;
public interface TaskRepository {
    void add(Task task);
    void delete(Task task);
    void update(Task task);
    Optional<Task> getNext();
    Optional<Task> search(String name);
}
