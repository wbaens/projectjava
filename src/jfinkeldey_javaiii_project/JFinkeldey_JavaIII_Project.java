package jfinkeldey_javaiii_project;

/** 
 * @Course: SDEV 450 ~ Java Programming III
 * @Author Name: Jeff
 * @Assignment Name: jfinkeldey_javaiii_project
 * @Date: Oct 8, 2018
 * @Description: 
 */

//Imports
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static jfinkeldey_javaiii_project.ValidationTF.Warning;


//Begin Class JFinkeldey_JavaIII_Project
public class JFinkeldey_JavaIII_Project extends Application {

    static String accesslevel;
    static Integer empID;
    static String fName;
    static String lName;
    static String address;
    static String city;
    static String state;
    static Integer zip;
    static String phone;
    static String email;
    static String dept;
    static String role;
    static Integer level;
    static Integer superv;
    static float rate;
    static String ins;
    static String insID;
    static Integer depend;
    static float insprem;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        

        
    BorderPane pane = new BorderPane();
    
    ValidationTF TFUserID = new ValidationTF();
    TFUserID.setMaxWidth(120);    
    ValidationTF TFPwd = new ValidationTF();  
    TFPwd.setMaxWidth(120);
    
    VBox VBInputs = new VBox();
    VBInputs.setPadding(new Insets(10, 0, 0, 5));
    VBInputs.setSpacing(3);
    VBInputs.getChildren().addAll(new Label("User ID: "), TFUserID, new Label("Password: "), TFPwd);
    
    Button btLogin = new Button("Login");
    Button btExit = new Button("Exit");
    
    btLogin.setMinWidth(75);
    btExit.setMinWidth(75);

    GridPane gpControls = new GridPane();
    gpControls.setHgap(10); 
    gpControls.setVgap(10); 
    gpControls.setPadding(new Insets(5, 5, 5, 5));
    
    gpControls.add(btLogin, 1, 1);
    gpControls.add(btExit, 1, 2);

    VBox VBLeftDisplay = new VBox();
    VBLeftDisplay.getChildren().addAll(VBInputs,gpControls);
    
    pane.setLeft(VBLeftDisplay);

    HBox hbFooter = new HBox();
    hbFooter.setPadding(new Insets(10, 0, 0, 5));
    hbFooter.setSpacing(3);

    Button btSearch = new Button("Search ID");
    btSearch.setPrefWidth(100);

    Button btUpdate = new Button("Update");
    btUpdate.setPrefWidth(100);
    
    Button btClear = new Button("Clear");
    btClear.setPrefWidth(100);
    
    Button btDelete = new Button("Delete");
    btDelete.setPrefWidth(100);
    
    Button btLogout = new Button("Logout");
    btLogout.setPrefWidth(100);

    Button btPayLogout = new Button("Logout");
    btPayLogout.setPrefWidth(100);

    Button btViewReport = new Button("View Current Report");
    btViewReport.setPrefWidth(100);
    
    Button btExport = new Button("Export File");
    btExport.setPrefWidth(100);
    
    VBox vbPayroll = new VBox();
    vbPayroll.getChildren().addAll(btViewReport,btExport);

    //Contact fields
    ValidationTF tfEmpID = new ValidationTF();
    ValidationTF tfFName = new ValidationTF();
    ValidationTF tfLName = new ValidationTF();
    ValidationTF tfAddr = new ValidationTF();
    ValidationTF tfCity = new ValidationTF();
    ValidationTF tfState = new ValidationTF();
    ValidationTF tfZip = new ValidationTF();    
    ValidationTF tfPhone = new ValidationTF();        
    
    //Company fields
    ValidationTF tfDept = new ValidationTF();
    ValidationTF tfRole = new ValidationTF();
    ValidationTF tfLevel = new ValidationTF();
    ValidationTF tfSuper = new ValidationTF();
    ValidationTF tfRate = new ValidationTF();
    ValidationTF tfIns = new ValidationTF();    
    ValidationTF tfInsID = new ValidationTF();        
    ValidationTF tfDep = new ValidationTF();
    ValidationTF tfInsPrem = new ValidationTF();

    //Timesheet fields
    ValidationTF tfPayPeriod = new ValidationTF();
    ValidationTF tfHours = new ValidationTF();
    ValidationTF tfPay = new ValidationTF();
    ValidationTF tfApproved = new ValidationTF();
    ValidationTF tfApprover = new ValidationTF();
    
    String AdID = "Admin";
    String AdPW = "AdminPW";

    hbFooter.setPadding(new Insets(10, 0, 0, 5));
    hbFooter.setSpacing(30);
    
    hbFooter.getChildren().addAll(btSearch, btUpdate, btClear, btDelete, btLogout);

    //Contact pane
    GridPane gpContact = new GridPane();
    gpContact.setHgap(10); 
    gpContact.setVgap(25); 
    gpContact.getColumnConstraints().add(new ColumnConstraints(80));
    gpContact.getColumnConstraints().add(new ColumnConstraints(150));
    gpContact.getColumnConstraints().add(new ColumnConstraints(100));
    gpContact.getColumnConstraints().add(new ColumnConstraints(50));
    gpContact.getColumnConstraints().add(new ColumnConstraints(100));    
    gpContact.setPadding(new Insets(5, 5, 5, 5));    
    gpContact.add(new Label("Emp ID:"), 0, 1);
    gpContact.add(new Label("First Name:"), 0, 2);
    gpContact.add(new Label("Last Name:"), 2, 2);
    gpContact.add(new Label("Address:"), 0, 3);
    gpContact.add(new Label("City:"), 0, 4);
    gpContact.add(new Label("State:"), 2, 4);
    gpContact.add(new Label("Zip:"), 4, 4);
    gpContact.add(new Label("Phone:"), 0, 5);

    gpContact.add(tfEmpID, 1, 1);    
    gpContact.add(tfFName, 1, 2);    
    gpContact.add(tfLName, 3, 2, 3, 1);    
    gpContact.add(tfAddr, 1, 3, 5, 1);    
    gpContact.add(tfCity, 1, 4);    
    gpContact.add(tfState, 3, 4);    
    gpContact.add(tfZip, 5, 4);    
    gpContact.add(tfPhone, 1, 5);    

    gpContact.add(hbFooter, 0, 7, 5, 1);

    //Company pane
    GridPane gpCompany = new GridPane();
    gpCompany.setHgap(10); 
    gpCompany.setVgap(25); 
    gpCompany.getColumnConstraints().add(new ColumnConstraints(100));
    gpCompany.getColumnConstraints().add(new ColumnConstraints(100));
    gpCompany.getColumnConstraints().add(new ColumnConstraints(100));
    gpCompany.getColumnConstraints().add(new ColumnConstraints(100));
    gpCompany.getColumnConstraints().add(new ColumnConstraints(100));    
    gpCompany.setPadding(new Insets(5, 5, 5, 5));
    gpCompany.add(new Label("Emp ID:"), 0, 1);
    gpCompany.add(new Label("Department:"), 0, 2);
    gpCompany.add(new Label("Role:"), 0, 3);
    gpCompany.add(new Label("Level:"), 0, 4);
    gpCompany.add(new Label("Supervisor:"), 2, 4);
    gpCompany.add(new Label("Rate:"), 4, 4);
    gpCompany.add(new Label("Insurance:"), 0, 5);
    gpCompany.add(new Label("Insurance ID:"), 2, 5);
    gpCompany.add(new Label("Dependents:"), 4, 5);    
    gpCompany.add(new Label("Premium:"), 0, 6);

    gpCompany.add(tfDept, 1, 2, 4, 1);    
    gpCompany.add(tfRole, 1, 3, 4, 1);    
    gpCompany.add(tfLevel, 1, 4);    
    gpCompany.add(tfSuper, 3, 4);    
    gpCompany.add(tfRate, 5, 4);    
    gpCompany.add(tfIns, 1, 5);    
    gpCompany.add(tfInsID, 3, 5);    
    gpCompany.add(tfDep, 5, 5);    
    gpCompany.add(tfInsPrem, 1, 6);    

    //Timesheet pane    
    Timesheet gpTimesheet = new Timesheet();

    //Payroll pane
    GridPane gpPayroll = new GridPane();
    gpPayroll.setHgap(30); 
    gpPayroll.setVgap(25); 
    gpPayroll.setPadding(new Insets(75, 75, 75, 75));
    gpPayroll.add(btViewReport, 0, 0);
    gpPayroll.add(btExport, 0, 1);
    gpPayroll.add(btPayLogout, 0, 3);
    
    //Create Tabs
    Tab tbContact = new Tab();
    tbContact.setText("Contact Data                     ");
    tbContact.setClosable(false);
    tbContact.setContent(gpContact);

    Tab tbCompany = new Tab();
    tbCompany.setText("Company Data                     ");
    tbCompany.setClosable(false);                

    Tab tbTimesheet = new Tab();
    tbTimesheet.setClosable(false);                
    tbTimesheet.setText("Timesheet Data                 ");

    Tab tbPayroll = new Tab();
    tbPayroll.setClosable(false);                

    tbPayroll.setText("Payroll Administration           ");
    
    //Login checks the User ID and Password versus the User table for validity
    btLogin.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (TFUserID.Filled("User ID") & TFPwd.Filled("Password")) {
                
                //check User ID and Password valid 

                // Only one of the two sets of statement below should be uncommented JLF 2 Nov 18
                
                // the statement below checks ID using the private function validate_login against specified table results
                if(validate_login(TFUserID.getText(),TFPwd.getText())) {

                // the statement below checks ID against hardcoded strings AdID and AdPW                    
                //if (TFUserID.getText().equals( AdID ) & TFPwd.getText().equals( AdPW )) {

                tfEmpID.setText(empID.toString());
                
                primaryStage.close();
                Group root = new Group();
                Scene scene = new Scene(root, 800, 500, Color.WHITE);
                
                TabPane tabPane = new TabPane();
                BorderPane mainPane = new BorderPane();
                

                //Creating the Tab Window
                tabPane.getTabs().addAll(tbContact,tbCompany,tbTimesheet,tbPayroll);

                //Access restrictions for tabs go here
                if (accesslevel.equals("01")) {
                    btDelete.setDisable(true);
                    tbPayroll.setDisable(true);
                    }
                else {
                    btDelete.setDisable(false);
                    tbPayroll.setDisable(false);
                    }

                tbContact.setOnSelectionChanged(e -> {
                        if (tbContact.isSelected()) {
                            //Remove common content from other tabs
                            tbCompany.setContent(null);
                            tbTimesheet.setContent(null);
                            tbPayroll.setContent(null);                            
                            gpCompany.getChildren().remove(tfEmpID);
                            gpTimesheet.getChildren().remove(tfEmpID);
                            gpCompany.getChildren().remove(hbFooter);
                            gpTimesheet.getChildren().remove(hbFooter);
                            //Add common content
                            gpContact.add(tfEmpID, 1, 1);
                            gpContact.add(hbFooter, 0, 7, 5, 1);
                            tbContact.setContent(gpContact);
                        }
                }
                );
                tbCompany.setOnSelectionChanged(e -> {
                        if (tbCompany.isSelected()) {
                            //Remove common content from other tabs
                            tbContact.setContent(null);
                            tbTimesheet.setContent(null);
                            tbPayroll.setContent(null);                            
                            gpContact.getChildren().remove(tfEmpID);                            
                            gpTimesheet.getChildren().remove(tfEmpID);                            
                            gpContact.getChildren().remove(hbFooter);
                            //Add common content
                            gpCompany.add(tfEmpID, 1, 1);
                            gpCompany.add(hbFooter, 0, 7, 5, 1);
                            tbCompany.setContent(gpCompany);
                        }
                }
                );
                tbTimesheet.setOnSelectionChanged(e -> {
                        if (tbTimesheet.isSelected()) {
                            //Remove common content from other tabs
                            tbCompany.setContent(null);
                            tbContact.setContent(null);
                            tbPayroll.setContent(null);
                            gpContact.getChildren().remove(tfEmpID);                            
                            gpCompany.getChildren().remove(tfEmpID);                            
                            gpCompany.getChildren().remove(hbFooter);
                            gpContact.getChildren().remove(hbFooter);
                            //Add common content
                            gpTimesheet.add(tfEmpID, 1, 1);
                            gpTimesheet.add(hbFooter, 0, 7, 5, 1);
                            tbTimesheet.setContent(gpTimesheet);
                        }
                }
                );
                tbPayroll.setOnSelectionChanged(e -> {
                        if (tbPayroll.isSelected()) {
                            //Remove common content from other tabs
                            tbCompany.setContent(null);
                            tbContact.setContent(null);
                            tbTimesheet.setContent(null);                            
                            gpContact.getChildren().remove(tfEmpID);                            
                            gpCompany.getChildren().remove(tfEmpID);                            
                            gpTimesheet.getChildren().remove(tfEmpID);                            
                            gpCompany.getChildren().remove(hbFooter);
                            gpContact.getChildren().remove(hbFooter);
                            gpTimesheet.getChildren().remove(hbFooter);
                            //Add common content
                            tbPayroll.setContent(gpPayroll);

                            }
                }
                );
                
                mainPane.setCenter(tabPane);
                
                mainPane.prefHeightProperty().bind(scene.heightProperty());
                mainPane.prefWidthProperty().bind(scene.widthProperty());
                
                root.getChildren().add(mainPane);
                primaryStage.setScene(scene);
                primaryStage.show();
                
            }   
                else {
                    // Failed Login
                    TFUserID.clear();
                    TFPwd.clear();
                    Warning("Invalid login\n","User ID and/or password are incorrect.\nPlease try again."); 
                    TFUserID.requestFocus();
                    }
        }
        }
    });

    //ID Lookup searches for appropriate data
    btSearch.setOnAction((event) -> {
        //If ID not null, check table for match
        if (tfEmpID.Filled("Employee ID")) {
            //not blank, check for match via actionDB...
            if(actionDB("tbContact",Integer.parseInt(tfEmpID.getText()),"search")) {
            tfFName.setText(fName);
            tfLName.setText(lName); 
            tfAddr.setText(address);
            tfCity.setText(city);
            tfState.setText(state);
            tfZip.setText(zip.toString());
            tfPhone.setText(phone);
//            tfEmail = rs.getString("email");
            tfDept.setText(dept);
            tfRole.setText(role);
            tfLevel.setText(level.toString());
            tfSuper.setText(superv.toString());
            tfRate.setText(String.valueOf(rate));
            tfIns.setText(ins);
            tfInsID.setText(insID);
            tfDep.setText(depend.toString());
            tfInsPrem.setText(String.valueOf(insprem));
            }
        }
    }
    );
    

    //Update adds data to appropriate table
    btUpdate.setOnAction((event) -> {
        if (tbContact.isSelected() || tbCompany.isSelected()) {
            // if on Contact or Company tab, update Employees table
            try{
                Connection con=DriverManager.getConnection(  
                        "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
                PreparedStatement stmt=con.prepareStatement("update Employees Set Fname = ?, "
                        + "Lname = ?, Address = ?, City = ?, State = ?, Zip = ?, Phone = ?, "
                        + "Department = ?, Role = ?, Level = ?, Supervisor = ?, Rate = ?, Ins = ?, InsID = ?, "
                        + "Dependents = ?, InsPrem = ? "
                        + " where Empid = ?");

                stmt.setString(1,tfFName.getText());
                stmt.setString(2,tfLName.getText());
                stmt.setString(3,tfAddr.getText());
                stmt.setString(4,tfCity.getText());
                stmt.setString(5,tfState.getText());  
                stmt.setInt(6,Integer.parseInt(tfZip.getText()));
                stmt.setString(7,tfPhone.getText());
                
                stmt.setString(8,tfDept.getText());
                stmt.setString(9,tfRole.getText());
                stmt.setInt(10,Integer.parseInt(tfLevel.getText()));
                stmt.setInt(11,Integer.parseInt(tfSuper.getText()));
                stmt.setFloat(12,Float.parseFloat(tfRate.getText()));
                stmt.setString(13,tfIns.getText());
                stmt.setString(14,tfInsID.getText());
                stmt.setInt(15,Integer.parseInt(tfDep.getText()));
                stmt.setFloat(16,Float.parseFloat(tfInsPrem.getText()));
                stmt.setInt(17,Integer.parseInt(tfEmpID.getText()));
                
                int i=stmt.executeUpdate();  
                
                con.close();  
            }catch(Exception e){ System.out.println(e); } 
        }

        if (tbTimesheet.isSelected()) {
            Timesheet.update(Integer.parseInt(tfEmpID.getText()));
        }
    }
    );

    //Clear empties fields
    btClear.setOnAction((event) -> {
        if (tbContact.isSelected()) {
            tfEmpID.setText("");
            tfFName.setText("");
            tfLName.setText("");
            tfAddr.setText("");
            tfCity.setText("");
            tfState.setText("");
            tfZip.setText("");
            tfPhone.setText("");
            tfEmpID.requestFocus();
        }
        if (tbCompany.isSelected()) {
            tfEmpID.setText("");
            tfDept.setText("");
            tfRole.setText("");
            tfLevel.setText("");
            tfSuper.setText("");
            tfRate.setText("");
            tfIns.setText("");
            tfInsID.setText("");
            tfDep.setText("");
            tfInsPrem.setText("");
            tfEmpID.requestFocus();
        }
        if (tbTimesheet.isSelected()) {
            Timesheet.clear();
        }
    }
    );    

    //Delete removes records from the Employee or Timesheet table, 
    //depending on the active tab when clicked
    btDelete.setOnAction((event) -> {
        if (tbContact.isSelected() || tbCompany.isSelected()) {
            try{
                
                //Confirmation before deleting
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setTitle("Confirmation");
                conf.setHeaderText("Delete confirmation");
                conf.setContentText("Are you sure you want to delete employee "+tfEmpID.getText()+"?");
                
                Optional<ButtonType> result = conf.showAndWait();
                
                if(result.get() == ButtonType.OK) {
                    Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
                    Connection con=DriverManager.getConnection(
                            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");
                    PreparedStatement pst = con.prepareStatement("Delete from Users where EmpID=?");
                    pst.setString(1, tfEmpID.getText());
                    int del = pst.executeUpdate();
                    PreparedStatement pst2 = con.prepareStatement("Delete from Timesheet where EmpID=?");
                    pst2.setString(1, tfEmpID.getText());
                    int del2 = pst2.executeUpdate();
                    PreparedStatement pst3 = con.prepareStatement("Delete from Employees where EmpID=?");
                    pst3.setString(1, tfEmpID.getText());
                    int del3 = pst3.executeUpdate();                    
                }
    
//              System.out.println("User "+tfEmpID.getText()+" has been deleted.");      
                }
            catch(Exception e){
                e.printStackTrace();
                }
            }
        if (tbTimesheet.isSelected()) {
        }
    }
    );        
    
    //Exit closes the app from  the Login Screen
    btExit.setOnAction((event) -> {
        primaryStage.close();
    }
    );

    //Logout closes the app from the contact, company, and timesheet tabs
    btLogout.setOnAction((event) -> {
        primaryStage.close();
    }
    );

    //PayLogout exits the Payroll tab
    btPayLogout.setOnAction((event) -> {
        primaryStage.close();
    }
    );

    
// Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 500, 250);
    primaryStage.setTitle("Employee Management System Login");         // Set the stage title
    primaryStage.setScene(scene);               // Place the scene in the stage
    primaryStage.show();                        // Display the stage

//public static void main(String args[]){  
//
//    
try{  
    Class.forName("com.mysql.jdbc.Driver");  
    System.out.println("Driver Loaded!");
}catch(Exception e){ System.out.println(e);}     

try{   
    Connection con=DriverManager.getConnection(  
            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
    //here employeedatabase is database name, root is username and password  
    Statement stmt=con.createStatement();  
    ResultSet rs = stmt.executeQuery("select EmpID from Users");  
    while(rs.next())  
    con.close();  
}catch(Exception e){ System.out.println(e); }     
// 
//System.out.println(" End of code.");

    }
  
private boolean validate_login(String username,String password) {
   try{           
       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
       Connection con=DriverManager.getConnection(  
            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
       PreparedStatement pst = con.prepareStatement("Select * from users where Username=? and Password=?");
       pst.setString(1, username); 
       pst.setString(2, password);
       ResultSet rs = pst.executeQuery();                        
       if(rs.next()) {
           empID = rs.getInt("EmpID");
           accesslevel = rs.getString("Access");
           System.out.println("User "+rs.getString(1)+" access level "+rs.getString("Access"));      
           return true;    
       }           
       else
           return false;            
   }
   catch(Exception e){
       e.printStackTrace();
       return false;
   }       
}

private boolean actionDB(String tabin, Integer IDin, String actionin) {
   try{           
       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
       Connection con=DriverManager.getConnection(  
            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
       PreparedStatement pst = con.prepareStatement("Select * from Employees where EmpID=?");
       
       if (tabin.equals("tbContact") || tabin.equals("tbCompany")) {
           //pst.setString(1, "Employees");    
       }
       if (tabin.equals("tbTimeSheet")) {
           //pst.setString(1, "Timesheet");  
       }
       pst.setInt(1, IDin);
               
       ResultSet rs = pst.executeQuery();     
       
       if(rs.next()) {
           fName = rs.getString("FName");
           lName = rs.getString("LName");
           address = rs.getString("address");
           city = rs.getString("city");
           state = rs.getString("state");
           zip = rs.getInt("zip");
           phone = rs.getString("phone");
           email = rs.getString("email");
           dept = rs.getString("department");
           role = rs.getString("role");
           level = rs.getInt("level");
           superv = rs.getInt("supervisor");
           rate = rs.getFloat("rate");
           ins = rs.getString("ins");
           insID = rs.getString("insid");
           depend = rs.getInt("dependents");
           insprem = rs.getFloat("insprem");
           
           System.out.println("FName "+fName);
           return true;    
       }           
       else
           return false;            
   }
   catch(Exception e){
       e.printStackTrace();
       return false;
   }       
    
}

} //End Class JFinkeldey_JavaIII_Project