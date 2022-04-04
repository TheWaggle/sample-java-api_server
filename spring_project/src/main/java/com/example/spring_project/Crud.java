package com.example.spring_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Crud {
    static Connection connection;
    static String URL = "jdbc:sqlite:sqlitesample.db";

    public static String insertData(Integer score) {
        String sql = "INSERT INTO scores (score) VALUES(" + score + ")";

        try {
            connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(sql);
            statement.close();
            return "ok";
        } catch(SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return e.getMessage();
        } finally {
            try {
                if(connection != null)
                connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public static Object getData() {
        try {
            // create a database connection
            connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT * FROM scores ORDER BY score DESC limit 10");
//            List<Integer> scoreList = new ArrayList<>(); //java版APIはリストで返す
            List<String> scoreList = new ArrayList<>(); //c版APIは文字列で返す
            while(rs.next()){
                scoreList.add(rs.getString("score")); //c版APIは文字列で返す
//                scoreList.add(Integer.parseInt(rs.getString("score"))); //java版APIはリストで返す
            }
            // add scoreList to String.//c版APIは文字列で返す
            StringBuilder str = new StringBuilder(); 
            for (String score : scoreList) {
                str.append(score);
                str.append(",");
            }
            return str; //C版APIは文字列で返す
//            return screList; // Java版APIはリストで返す

        } catch(SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return e.getMessage();
        } finally {
            try {
                if(connection != null)
                connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
