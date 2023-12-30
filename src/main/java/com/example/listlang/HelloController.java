package com.example.listlang;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField task;

    @FXML
    private CheckBox isfinish;

    @FXML
    private ComboBox taskSelected;

    @FXML
    private TextField taskSelectedText;

    @FXML
    private CheckBox isfinishSelected;

    @FXML
    private ListView taskList;

    @FXML
    private ComboBox taskSelectedDelete;
    @FXML
    private TabPane myTab;
    @FXML
    protected void addTask() {

        String taskText = this.task.getText();
        Boolean is_finish =  this.isfinish.isSelected();

        Task taskRequest = new Task();
        taskRequest.addTask(taskText,is_finish);


    }

    @FXML
    protected void modifyTask() {
        TaskInfo selectedTask = (TaskInfo) taskSelected.getSelectionModel().getSelectedItem();
        String taskName = taskSelectedText.getText();
        boolean isChecked = isfinishSelected.isSelected();

        Task myTask = new Task();
        myTask.modifyOneTask(selectedTask.getTaskId(), taskName,isChecked);


    }

    @FXML
    protected void changeToModify() throws SQLException {
        Task taskRequest = new Task();
        ResultSet allTasks = taskRequest.getAllTask();

        // Clearing existing items in the ComboBox
        taskSelected.getItems().clear();

        // Adding fetched tasks (with ID and name) to the ComboBox
        while (allTasks.next()) {
            int taskId = allTasks.getInt("id");
            String taskName = allTasks.getString("name");
            boolean is_active = allTasks.getBoolean("is_active");
            TaskInfo taskInfo = new TaskInfo(taskId, taskName,is_active);
            taskSelected.getItems().add(taskInfo);
        }
    }

    @FXML
    protected void changeToDelete() throws SQLException {
        Task taskRequest = new Task();
        ResultSet allTasks = taskRequest.getAllTask();

        // Clearing existing items in the ComboBox
        taskSelectedDelete.getItems().clear();

        // Adding fetched tasks (with ID and name) to the ComboBox
        while (allTasks.next()) {
            int taskId = allTasks.getInt("id");
            String taskName = allTasks.getString("name");
            boolean is_active = allTasks.getBoolean("is_active");
            TaskInfo taskInfo = new TaskInfo(taskId, taskName,is_active);
            taskSelectedDelete.getItems().add(taskInfo);
        }
    }

    @FXML
    protected  void changeToList(){
        Task taskRequest = new Task();
        ResultSet allTasks = taskRequest.getAllTask();

        try {
            // Clearing existing items in the ListView
            taskList.getItems().clear();

            // Adding fetched tasks to the ListView
            while (allTasks.next()) {
                String taskName = allTasks.getString("name");
                boolean isFinished = allTasks.getBoolean("is_active");
                int id =  allTasks.getInt("id");
                TaskInfo taskInfo = new TaskInfo(id,taskName, isFinished);
                taskList.getItems().add(taskInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }
    }
    @FXML
    protected void selectedTask(){
        TaskInfo selectedTask = (TaskInfo) taskSelected.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            int taskId = selectedTask.getTaskId();
            String taskName = selectedTask.getTaskName();
            boolean is_active = selectedTask.getTaskIs_active();

            taskSelectedText.setText(taskName);
            isfinishSelected.setSelected(is_active);



        }

    }

    @FXML
    public void deleteTask(){
        TaskInfo taskSelected= (TaskInfo)  taskSelectedDelete.getSelectionModel().getSelectedItem();
        if (taskSelected != null) {
            int taskId = taskSelected.getTaskId();
            System.out.println(taskId);

            Task task = new Task();

            task.deleteOneTask(taskId);




        }
    }
}