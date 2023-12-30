package com.example.listlang;

import java.sql.*;

public class Task {

    public ResultSet getAllTask(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","");
            String query = "SELECT * FROM `tasks` ";
            stmt = conn.createStatement();
            return rs =  stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public TaskInfo getOneTask(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","");
            String query = "SELECT * tasks where id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_task = rs.getInt("id");
                String name_task = rs.getString("name");
                boolean is_active = rs.getBoolean("is_active");
                return new TaskInfo(id_task ,name_task,is_active);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean addTask(String name,Boolean isfinish ){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isGood = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","");
            String query = "INSERT INTO tasks  (name,is_active) VALUES(?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setBoolean(2,isfinish);
            return   stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean modifyOneTask(int id, String name, boolean is_active){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","");
            String query = "UPDATE tasks SET name = ?, is_active = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setBoolean(2,is_active);
            stmt.setInt(3,id);
            return stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOneTask(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","");
            String query = "DELETE FROM tasks  WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
