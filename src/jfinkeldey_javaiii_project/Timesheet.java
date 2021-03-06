/**
 * @Course: SDEV 450 ~ Java Programming III
 * Author Name: Jeff
 * Assignment Name: jfinkeldey_javaiii project
 * Date: Dec 14, 2018
 * Description: Timesheet class is to manage inputs for Timesheet tab
*/
package jfinkeldey_javaiii_project;

//Begin Class Timesheet

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Timesheet extends GridPane {
    
    //Timesheet fields
    static ValidationTF tfPayPeriod = new ValidationTF();
    static ValidationTF tfHours = new ValidationTF();
    static ValidationTF tfPay = new ValidationTF();
    static ValidationTF tfApproved = new ValidationTF();
    static ValidationTF tfApprover = new ValidationTF();
        
    public Timesheet() {

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
        
        //Default disabled timesheet fields
        tfApproved.setDisable(true);
        tfApprover.setDisable(true);
    }
    
    public static void clear() {
        tfPayPeriod.setText("");
        tfHours.setText("");
        tfPay.setText("");
        tfApproved.setText("");
        tfApprover.setText("");
    }
    
    public static void unlock() {
        tfApproved.setDisable(false);
        tfApprover.setDisable(false);
    }
    
      public static boolean search(Integer IDin) {
   try{           
//       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
    Class.forName("org.apache.derby.jdbc.ClientDriver");
       Connection con=DriverManager.getConnection(  
            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
       PreparedStatement pst = con.prepareStatement("Select * from Timesheet where EmpID=?");
       
       pst.setInt(1, IDin);
               
       ResultSet rs = pst.executeQuery();   
              
       if(rs.next()) {
           tfPayPeriod.setText(rs.getString("PayPeriodEnd"));
           tfHours.setText(rs.getString("Hours"));
           tfPay.setText(String.valueOf(rs.getInt("Pay")));
           tfApproved.setText(rs.getString("Approved"));
           tfApprover.setText(String.valueOf(rs.getInt("Approver")));
           
           return true;    
       }           
       else {
           ValidationTF.Warning("Data Not Found", "No Data for that ID");           
           return false;            
       }
   }
   catch(Exception e){
       e.printStackTrace();
       return false;
   }       
}
    
    public static void update(Integer ID) {
        try{
            if (tfPay.Filled("Pay Period End") & tfHours.Filled("Hours")
                    & tfPayPeriod.Filled("Pay")) {
                        
            Connection con=DriverManager.getConnection(  
                    "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
            PreparedStatement stmt=con.prepareStatement("update Timesheet Set Payperiodend = ?,"
                    + "Hours = ?, Pay = ? "
                    + "where Empid = ?");

            stmt.setString(1,tfPayPeriod.getText());
            stmt.setInt(2,Integer.parseInt(tfHours.getText()));
            stmt.setFloat(3,Float.parseFloat(tfPay.getText()));
            stmt.setInt(4, ID);

            int i=stmt.executeUpdate(); 

            if(i == 0) {
                ValidationTF.Warning("Insert Error", "No timesheet for ID "+ID);
                
                //Confirmation before deleting
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setTitle("Insert Error");
                conf.setHeaderText("No matching record");
                conf.setContentText("No timesheet for ID "+ID+". Do you want to add it?");
                
                Optional<ButtonType> result = conf.showAndWait();
                
                if(result.get() == ButtonType.OK) {
//                    Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
                    PreparedStatement pst = con.prepareStatement("Insert into Timesheet (EmpID, "
                            + "PayperiodEnd, Hours, Pay) "
                            + "Values (?, ?, ?, ?)");
                    pst.setInt(1, ID);
                    pst.setString(2,tfPayPeriod.getText());
                    pst.setInt(3,Integer.parseInt(tfHours.getText()));
                    pst.setFloat(4,Float.parseFloat(tfPay.getText()));

                    int ins = pst.executeUpdate();     
                }
                
            }
//            Timesheet.clear();            
            con.close();  
            }
            else {
                ValidationTF.Warning("Missing Information", "Data not saved, missing information.");
            }
            }catch(Exception e){ System.out.println(e); } 

    }

    public static void approve(Integer ID,String Email) {
                    try{
                        if (tfApprover.Filled("Approver") & tfApproved.Filled("Approved Date"))
                                {
                                    Connection con=DriverManager.getConnection(  
                                            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
                                    PreparedStatement stmt=con.prepareStatement("update Timesheet Set Payperiodend = ?,"
                                            + "Hours = ?, Pay = ?, Approved = ?, Approver = ? "
                                            + "where Empid = ?");
                                    stmt.setString(1,tfPayPeriod.getText());
                                    stmt.setInt(2,Integer.parseInt(tfHours.getText()));
                                    stmt.setFloat(3,Float.parseFloat(tfPay.getText()));
                                    stmt.setString(4,tfApproved.getText());
                                    stmt.setInt(5,Integer.parseInt(tfApprover.getText()));
                                    stmt.setInt(6, ID);
                                    
                                    int i=stmt.executeUpdate();  
                                    System.out.println("update "+i);
                                    if(i == 1) {
                                        PreparedStatement mailsend=con.prepareStatement("select * from Employees where EmpID=?");
                                        mailsend.setInt(1,ID);
                                        
                                        ResultSet Mailrs = mailsend.executeQuery();

                                        if(Mailrs.next()) {
                                                Email= Mailrs.getString("Email");

                                        sendEmail("harrya251@aol.com","jfinkeldey@aol.com","spudmb@aol.com","Hi","Timesheet approve works!");                    
                                        ValidationTF.Warning("Approval", "Email sent to "+Email);
                                        }
                                    }
                                    con.close();  }
                        }catch(Exception e){ System.out.println(e); } 

    }
    
    //EMAIL STUFF
            
    public static void sendEmail(String to, String cc, String from, String subject, String text) {
        String host = "smtp.aol.com";

        try {

    //Set the host smtp address
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");    
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", true);

    Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("spudmb", "java2018");
        }
});

    emailSession.setDebug(false);
    
    Message emailMessage = new MimeMessage(emailSession);
    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
    emailMessage.setFrom(new InternetAddress(from));
    emailMessage.setSubject(subject);
    emailMessage.setText(text);

    emailSession.setDebug(true);

    Transport.send(emailMessage);
    
    } catch (AddressException e) {
        e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
            }
        }
    
} //End Class Timesheet
