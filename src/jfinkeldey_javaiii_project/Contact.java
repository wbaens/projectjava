/**
 * @Course: SDEV 450 ~ Java Programming III
 * Author Name: Jeff
 * Assignment Name: jfinkeldey_javaiii project
 * Date: Dec 1, 2018
 * Description: Contact class is to manage inputs for Contact tab
*/
package jfinkeldey_javaiii_project;

//Begin Class Contact

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class Contact extends GridPane {
    
    //Contact fields
//    ValidationTF tfEmpID = new ValidationTF();
    static ValidationTF tfFName = new ValidationTF();
    static ValidationTF tfLName = new ValidationTF();
    static ValidationTF tfAddr = new ValidationTF();
    static ValidationTF tfCity = new ValidationTF();
    static ValidationTF tfState = new ValidationTF();
    static ValidationTF tfZip = new ValidationTF();    
    static ValidationTF tfPhone = new ValidationTF();  
    static ValidationTF tfEmail = new ValidationTF();
        
    public Contact() {
        
        this.setHgap(10); 
        this.setVgap(25); 
        this.getColumnConstraints().add(new ColumnConstraints(80));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(50));
        this.getColumnConstraints().add(new ColumnConstraints(100));    
        this.setPadding(new Insets(5, 5, 5, 5));    
        this.add(new Label("Emp ID:"), 0, 1);
        this.add(new Label("First Name:"), 0, 2);
        this.add(new Label("Last Name:"), 2, 2);
        this.add(new Label("Address:"), 0, 3);
        this.add(new Label("City:"), 0, 4);
        this.add(new Label("State:"), 2, 4);
        this.add(new Label("Zip:"), 4, 4);
        this.add(new Label("Phone:"), 0, 5);
        this.add(new Label("Email:"), 2, 5);

//        this.add(tfEmpID, 1, 1);    
        this.add(tfFName, 1, 2);    
        this.add(tfLName, 3, 2, 3, 1);    
        this.add(tfAddr, 1, 3, 5, 1);    
        this.add(tfCity, 1, 4);    
        this.add(tfState, 3, 4);    
        this.add(tfZip, 5, 4);    
        this.add(tfPhone, 1, 5);    
        this.add(tfEmail, 3, 5, 3, 1);
    }
    
    public static void clear() {
            tfFName.setText("");
            tfLName.setText("");
            tfAddr.setText("");
            tfCity.setText("");
            tfState.setText("");
            tfZip.setText("");
            tfPhone.setText("");
            tfEmail.setText("");
    }
    
    public static void search(Integer IDin) {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");       
            Connection con=DriverManager.getConnection(  
                    "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
            PreparedStatement pst = con.prepareStatement("Select * from Employees where EmpID=?");

            pst.setInt(1, IDin);

            ResultSet rs = pst.executeQuery();     

            if(rs.next()) {
                tfFName.setText(rs.getString("FName"));
                tfLName.setText(rs.getString("LName"));
                tfAddr.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfZip.setText(String.valueOf(rs.getInt("zip")));
                tfPhone.setText(rs.getString("phone"));
                tfEmail.setText(rs.getString("email"));

    //           return true;    
            }
    //       else
    //           return false;            
    
       }
        catch(Exception e){
           e.printStackTrace();
    //       return false;
       }       

    }//    }

    public static void update(Integer ID) {
        try{
            if (tfFName.Filled("First Name") & tfLName.Filled("Last Name")
                    & tfAddr.Filled("Address") & tfCity.Filled("City")
                    & tfState.Filled("State") & tfZip.Filled("Zip")
                    & tfZip.ValInt("Zip") & tfPhone.Filled("Phone")
                    & tfPhone.ValInt("Phone") & tfEmail.Filled("Email")) {

            Connection con=DriverManager.getConnection(  
                    "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
            PreparedStatement stmt=con.prepareStatement("update Employees Set Fname = ?, "
                    + "Lname = ?, Address = ?, City = ?, State = ?, Zip = ?, Phone = ?, Email = ? "
                    + " where Empid = ?");

            stmt.setString(1,tfFName.getText());
            stmt.setString(2,tfLName.getText());
            stmt.setString(3,tfAddr.getText());
            stmt.setString(4,tfCity.getText());
            stmt.setString(5,tfState.getText());  
            stmt.setInt(6,Integer.parseInt(tfZip.getText()));
            stmt.setString(7,tfPhone.getText());
            stmt.setString(8, tfEmail.getText());
            stmt.setInt(9, ID);

            int i=stmt.executeUpdate();  
            con.close();  
            
            }            

        }catch(Exception e){ System.out.println(e); } 
    }
        
} //End Class Timesheet
