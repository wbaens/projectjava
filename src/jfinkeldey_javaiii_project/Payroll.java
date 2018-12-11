/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfinkeldey_javaiii_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Alaafow
 */
public class Payroll extends GridPane {
    
     public Payroll (){

    }
    
          public static void displayRecords(){
               Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;
        String query = "Select * from Whiteflour.Employees";
//        String query = "select * from Whiteflour.Employees cross join Whiteflour.Timesheet cross join  Whiteflour.Users";

        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/EmployeeDatabase", "whiteflour", "123456");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            meta = rs.getMetaData();
            int columnNo = meta.getColumnCount();
//           System.out.println(columnNo);

            for (int i = 1; i <= columnNo; i++) {

//            }
            System.out.print(meta.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {

           for (int i = 1; i <= columnNo; i++) {
                    System.out.print(rs.getObject(i) + "\t");
                }                System.out.println();

           } 
       } catch (SQLException e) {
           e.printStackTrace();

        }

    }
  
}
