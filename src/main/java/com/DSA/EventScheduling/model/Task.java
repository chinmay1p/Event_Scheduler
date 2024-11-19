package com.DSA.EventScheduling.model;
import java.time.LocalDateTime;
public class Task implements Comparable<Task>{
    private String name;
    private LocalDateTime dateTime;
    public Task(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public int compareTo(Task x) {
        return this.dateTime.compareTo(x.dateTime);
    }
}
