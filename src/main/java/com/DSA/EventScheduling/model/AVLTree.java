package com.DSA.EventScheduling.model;

import java.util.ArrayList;
import java.util.List;
    
public class AVLTree {
    private Node root;
    private Task nulltask=new Task("abc",null);

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private Node rotright(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotleft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private int balance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    public void insert(Task task) {
        root = inserts(root, task);
    }

    private Node inserts(Node node, Task task) {
        if (node == null) return new Node(task);

        if (task.compareTo(node.task) < 0)
            node.left = inserts(node.left, task);
        else if (task.compareTo(node.task) > 0)
            node.right = inserts(node.right, task);
        else
            return node;

        node.height = 1 + max(height(node.left),height(node.right));
        int balance = balance(node);

        if (balance > 1 && task.compareTo(node.left.task) < 0)
            return rotright(node);

        if (balance < -1 && task.compareTo(node.right.task) > 0)
            return rotleft(node);

        if (balance > 1 && task.compareTo(node.left.task) > 0) {
            node.left = rotleft(node.left);
            return rotright(node);
        }

        if (balance < -1 && task.compareTo(node.right.task) < 0) {
            node.right = rotright(node.right);
            return rotleft(node);
        }

        return node;
    }

    public void deletes(Task task) {
        root = del(root, task);
    }

    private Node del(Node node, Task task) {
        if (node == null){
            return null;
        }
        if (task.compareTo(node.task) < 0)
            node.left = del(node.left, task);
        else if (task.compareTo(node.task) > 0)
            node.right = del(node.right, task);
        else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                }
                else
                    node = temp;
            }
            else {
                Node temp = minval(node.right);
                node.task = temp.task;
                node.right = del(node.right, temp.task);
            }
        }

        if (node == null) return null;

        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = balance(node);

        if (balance > 1 && balance(node.left) >= 0)
            return rotright(node);

        if (balance > 1 && balance(node.left) < 0) {
            node.left = rotleft(node.left);
            return rotright(node);
        }

        if (balance < -1 && balance(node.right) <= 0)
            return rotleft(node);

        if (balance < -1 && balance(node.right) > 0) {
            node.right = rotright(node.right);
            return rotleft(node);
        }

        return node;
    }

    private Node minval(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    public Task findnxt() {
        if (root == null) return nulltask;
        Node x=root;
        while (x.left != null)x=x.left;
        return x.task;
    }

    public Task searchs(String name) {
        return searchnode(root, name);
    }


    private Task searchnode(Node node, String name) {
        if (node == null) return nulltask;
        if (node.task.getName().equals(name)) {
            return node.task;
        }
        Task found = searchnode(node.left, name);
        if (found != null && found != nulltask) {
            return found;
        }
        return searchnode(node.right, name);
    }



    public List<Task> getsall() {
        List<Task> tasks = new ArrayList<>();
        inorder(root, tasks);
        return tasks;
    }

    private void inorder(Node node, List<Task> tasks) {
        if (node != null) {
            inorder(node.left, tasks);
            tasks.add(node.task);
            inorder(node.right, tasks);
        }
    }
}
