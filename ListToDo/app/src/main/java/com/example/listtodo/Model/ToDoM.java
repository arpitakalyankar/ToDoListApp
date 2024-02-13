package com.example.listtodo.Model;

public class ToDoM {
    public int getTask;
    private String task;

    public String getTask(String string) {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int id,status;

    public String getTask() {
        return null;
    }
}
