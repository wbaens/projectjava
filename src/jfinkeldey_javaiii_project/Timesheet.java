/**
 * SDEV 350 ~ Java Programming II
 * Author Name: Jeff
 * Assignment Name: jfinkeldey_javaii_week13
 * Date: Dec 10, 2017
 * Description: Timesheet class is to manage inputs for Timesheet tab
*/
package jfinkeldey_javaiii_project;

//Begin Class ValidationTF

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class Timesheet extends GridPane {

    public Timesheet() {

        //Timesheet fields
        ValidationTF tfPayPeriod = new ValidationTF();
        ValidationTF tfHours = new ValidationTF();
        ValidationTF tfPay = new ValidationTF();
        ValidationTF tfApproved = new ValidationTF();
        ValidationTF tfApprover = new ValidationTF();

        this.setHgap(10); 
        this.setVgap(25); 
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.setPadding(new Insets(5, 5, 5, 5));
        this.add(new Label("Emp ID:"), 0, 1);
        this.add(new Label("Pay Period End:"), 0, 2);
        this.add(new Label("Hours:"), 0, 3);
        this.add(new Label("Pay:"), 0, 4);
        this.add(new Label("Approved:"), 0, 5);
        this.add(new Label("Approver:"), 2, 5);

        this.add(tfPayPeriod, 1, 2);    
        this.add(tfHours, 1, 3);    
        this.add(tfPay, 1, 4);    
        this.add(tfApproved, 1, 5);    
        this.add(tfApprover, 3, 5);        
    }
    
} //End Class Timesheet
