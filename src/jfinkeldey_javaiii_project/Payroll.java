/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfinkeldey_javaiii_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Alaafow
 */
public class Payroll extends GridPane {
    
     public Payroll (){
         
    this.setHgap(30); 
    this.setVgap(25); 
    this.setPadding(new Insets(75, 75, 75, 75));


    }
//      
          public static void displayRecords(){
              
              GridPane gpPayView = new GridPane();
              
              TextArea taPay = new TextArea();
              taPay.setMinWidth(850);
              taPay.setMinHeight(325);

              Stage subStage = new Stage();
              subStage.setMinHeight(400);
              subStage.setMinWidth(900);
              
              Button btClose = new Button("Close");
              btClose.setPrefWidth(100);
              
              btClose.setOnAction(e -> subStage.close());
              gpPayView.getChildren().add(taPay);
              gpPayView.add(btClose, 0, 20);
              
              Scene payrollReport = new Scene(gpPayView, 900, 400); 
              subStage.setScene(payrollReport);
              subStage.show();
              subStage.setTitle("Employee Report");

              try {
                  Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/EmployeeDatabase", "whiteflour", "123456");
                  PreparedStatement pst = con.prepareStatement("Select * from Whiteflour.Employees");       
                  ResultSet rs = pst.executeQuery();
                  ResultSetMetaData meta = rs.getMetaData();
                  int columnNo = meta.getColumnCount();

                  for (int i = 1; i <= columnNo; i++) {
                      taPay.appendText(meta.getColumnName(i) + "\t");
                      }
                  taPay.appendText("\n");
                  
                  while (rs.next()) {
                      for (int i = 1; i <= columnNo; i++) {
                          taPay.appendText(rs.getObject(i) + "\t\t");
                          }                
                      taPay.appendText("\n");
                      } 
                  } catch (SQLException e) {
                   e.printStackTrace();

                }  
            }
  
}
