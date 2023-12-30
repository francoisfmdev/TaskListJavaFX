package com.example.listlang;

public class TaskInfo {
    private int taskId;
    private String taskName;

    private boolean is_active;

    public TaskInfo(int taskId, String taskName,boolean is_active) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.is_active = is_active;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getTaskIs_active(){
        return this.is_active;
    }
    @Override
    public String toString() {
        return "ID: " + taskId + " - " + taskName + " - " + (is_active ? "Fait" : "Non fait");
    }
}