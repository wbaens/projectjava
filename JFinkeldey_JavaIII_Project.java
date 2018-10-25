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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    GridPane gpTimesheet = new GridPane();
    gpTimesheet.setHgap(10); 
    gpTimesheet.setVgap(25); 
    gpTimesheet.getColumnConstraints().add(new ColumnConstraints(100));
    gpTimesheet.getColumnConstraints().add(new ColumnConstraints(100));
    gpTimesheet.getColumnConstraints().add(new ColumnConstraints(100));
    gpTimesheet.getColumnConstraints().add(new ColumnConstraints(100));
    gpTimesheet.setPadding(new Insets(5, 5, 5, 5));
    gpTimesheet.add(new Label("Emp ID:"), 0, 1);
    gpTimesheet.add(new Label("Pay Period:"), 0, 2);
    gpTimesheet.add(new Label("Hours:"), 0, 3);
    gpTimesheet.add(new Label("Pay:"), 0, 4);
    gpTimesheet.add(new Label("Approved:"), 0, 5);
    gpTimesheet.add(new Label("Approver:"), 2, 5);

    gpTimesheet.add(tfPayPeriod, 1, 2);    
    gpTimesheet.add(tfHours, 1, 3);    
    gpTimesheet.add(tfPay, 1, 4);    
    gpTimesheet.add(tfApproved, 1, 5);    
    gpTimesheet.add(tfApprover, 3, 5);    
    
    //Payroll pane
    GridPane gpPayroll = new GridPane();
    gpPayroll.setHgap(30); 
    gpPayroll.setVgap(25); 
    gpPayroll.setPadding(new Insets(75, 75, 75, 75));
    gpPayroll.add(btViewReport, 0, 0);
    gpPayroll.add(btExport, 0, 1);
    gpPayroll.add(btPayLogout, 0, 3);
    
    //Exit closes window
    btLogin.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (TFUserID.Filled("User ID") & TFPwd.Filled("Password")) {
                
                //check User ID and Password valid - for now hardcode
                // to AdID and AdPW
                
                if (TFUserID.getText().equals( AdID ) & TFPwd.getText().equals( AdPW )) {
                
                primaryStage.close();
                Group root = new Group();
                Scene scene = new Scene(root, 800, 500, Color.WHITE);
                
                TabPane tabPane = new TabPane();
                BorderPane mainPane = new BorderPane();
                
                //Create Tabs
                Tab tbContact = new Tab();
                tbContact.setText("Contact Data                     ");
                tbContact.setClosable(false);
                tbContact.setContent(gpContact);
                                
                Tab tbCompany = new Tab();
                tbCompany.setText("Company Data                     ");
                tbCompany.setClosable(false);                

                Tab tbTimesheet = new Tab();
                tbTimesheet.setText("Timesheet Data                 ");
                
                Tab tbPayroll = new Tab();
                tbPayroll.setText("Payroll Administration           ");

                //Creating the Tab Window
                tabPane.getTabs().addAll(tbContact,tbCompany,tbTimesheet,tbPayroll);
                

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
   
    //Clear empties fields
    btClear.setOnAction((event) -> {
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
    );

    //Logout exits
    btLogout.setOnAction((event) -> {
        primaryStage.close();
    }
    );

    //PayLogout exits
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
//try{  
//    Class.forName("com.mysql.jdbc.Driver");  
//    System.out.println("Driver Loaded!");
//}catch(Exception e){ System.out.println(e);}     
//
//try{   
//    Connection con=DriverManager.getConnection(  
//            "jdbc:mysql://localhost:3306/emp1","root","$$Admin123");  
//    //here emp1 is database name, root is username and password  
//    Statement stmt=con.createStatement();  
//    ResultSet rs = stmt.executeQuery("select FName from demog");  
//    while(rs.next())  
//    //    System.out.println("Line "+rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//        System.out.println("Line "+rs.getString(1));      
//    con.close();  
//}catch(Exception e){ System.out.println(e);}     
// 
//System.out.println(" End of code.");


}  

} //End Class JFinkeldey_JavaIII_Project