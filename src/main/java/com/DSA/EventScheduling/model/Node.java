package com.DSA.EventScheduling.model;

public class Node {
    Task task;
    Node left;
    Node right;
    int height;
    public Node(Task task){
        this.task=task;
        height=1;
    }
}
