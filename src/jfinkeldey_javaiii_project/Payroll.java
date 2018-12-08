/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfinkeldey_javaiii_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alaafow
 */
public class Payroll extends GridPane {
    
     public Payroll (){
        

    }
    
          public static void displayRecords(){
       try{
//              Class.forName("org.apache.derby.jdbc.ClientDriver");
             Connection con = DriverManager.getConnection(  
               "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");
                
                 Statement stmt = con.createStatement();
       
                  ResultSet rs = stmt.executeQuery("Select * from WHITEFLOUR.EMPLOYEES"); 

        while (rs.next()){
            

           
            int empID = rs.getInt("EMPID");
           String fName = rs.getString("FNAME");

        }
                 
        }catch(SQLException e){
            e.printStackTrace();
        }
   }
    
    
    
}
