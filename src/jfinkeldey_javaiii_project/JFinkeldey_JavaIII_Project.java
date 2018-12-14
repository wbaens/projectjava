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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import static jfinkeldey_javaiii_project.Payroll.displayRecords;
import static jfinkeldey_javaiii_project.ValidationTF.Warning;


//Begin Class JFinkeldey_JavaIII_Project
public class JFinkeldey_JavaIII_Project extends Application {
    
    private TableView table = new TableView();
     static String accesslevel;
     static Integer empID;
     ObservableList names = FXCollections.observableArrayList();
    
      public static void main(String[] args) {
        launch(args);
    }

     
     
     

      
      
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    BorderPane pane = new BorderPane();
    
    //Login screen fields and setup
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

    //Login screen buttons
    GridPane gpControls = new GridPane();
    gpControls.setHgap(10); 
    gpControls.setVgap(10); 
    gpControls.setPadding(new Insets(5, 5, 5, 5));
    
    gpControls.add(btLogin, 1, 1);
    gpControls.add(btExit, 1, 2);


    VBox VBCenterDisplay = new VBox();
    VBCenterDisplay.getChildren().addAll(VBInputs,gpControls);
    VBCenterDisplay.setPadding(new Insets(120,20,20,80));
    pane.setCenter(VBCenterDisplay);

    //Common footer buttons
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

    hbFooter.setPadding(new Insets(10, 0, 0, 5));
    hbFooter.setSpacing(30);
    
    hbFooter.getChildren().addAll(btSearch, btUpdate, btClear, btDelete, btLogout);

    //  Master ID field
    ValidationTF tfEmpID = new ValidationTF();

    //Contact pane
    Contact gpContact = new Contact();

    gpContact.add(tfEmpID, 1, 1);    
    gpContact.add(hbFooter, 0, 7, 5, 1);

    //Company pane
    GridPane gpCompany = new Company();

    //Timesheet pane    
    Timesheet gpTimesheet = new Timesheet();

    //Payroll pane
    Payroll gpPayroll = new Payroll();
//    GridPane gpPayroll = new GridPane();
        
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

                // the statement below checks ID using the private function validate_login against specified table results
                if(validate_login(TFUserID.getText(),TFPwd.getText())) {

                    gpTimesheet.sendEmail("harrya251@aol.com","jfinkeldey@aol.com","spudmb@aol.com","Hi","Email code works!");
                    
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
            Contact.search(Integer.parseInt(tfEmpID.getText()));
            Company.search(Integer.parseInt(tfEmpID.getText()));
            Timesheet.search(Integer.parseInt(tfEmpID.getText()));            

//            tfEmail = rs.getString("email");
        }
    }
    );

    //Update adds data to appropriate table
    btUpdate.setOnAction((event) -> {
        if (tbContact.isSelected()) {   
            Contact.update(Integer.parseInt(tfEmpID.getText()));
        }
        if (tbCompany.isSelected()) {
            Company.update(Integer.parseInt(tfEmpID.getText()));            
        }

        if (tbTimesheet.isSelected()) {
            Timesheet.update(Integer.parseInt(tfEmpID.getText()));
        }
    }
    );

    //Clear empties fields
    btClear.setOnAction((event) -> {
        if (tbContact.isSelected()) {
            Contact.clear();
        }
        if (tbCompany.isSelected()) {
            Company.clear();
        }
        if (tbTimesheet.isSelected()) {
            Timesheet.clear();
        }
        tfEmpID.setText("");        
        tfEmpID.requestFocus();        
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
//                    Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
                Class.forName("org.apache.derby.jdbc.ClientDriver");    
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
    
    //View Payroll report
    btViewReport.setOnAction((event) -> {
//        Stage subStage = new Stage();
//        Label label2= new Label("This is the second scene");
//        Button button2= new Button("Close");
//        button2.setOnAction(e -> subStage.close());
//        gpPayView.getChildren().addAll(label2, button2);
//        Scene payrollReport = new Scene(gpPayView, 700, 300); 
//        subStage.setScene(payrollReport);
//        subStage.show();
        
      
            Payroll.displayRecords();
            

           

  
//    StackPane root = new StackPane();
//    root.getChildren().add(table);
//    primaryStage.setScene(new Scene(root, 1900, 450));
//    primaryStage.show();
//    primaryStage.showAndWait();
      VBox tableView = new VBox();       
    table.setEditable(true);

    TableColumn firstNameCol = new TableColumn("FNAME");
    firstNameCol.setMinWidth(40);
     firstNameCol.setCellValueFactory(
          new PropertyValueFactory<ContactClass, String>("firstNameCol"));
    
    TableColumn lastNameCol = new TableColumn("LNAME");
    lastNameCol.setMinWidth(40);
    
    TableColumn addressCol = new TableColumn("ADDRESS");
    addressCol.setMinWidth(200);
    
     TableColumn cityCol = new TableColumn("CITY");
    cityCol.setMinWidth(40);
    
    TableColumn stateCol = new TableColumn("STATE");
    stateCol.setMinWidth(40);
    
    TableColumn zipCol = new TableColumn("ZIP");
    zipCol.setMinWidth(200);
    
      TableColumn emailCol = new TableColumn("PHONE");
    emailCol.setMinWidth(40);
    
    TableColumn departmentCol = new TableColumn("DEPARTMENT");
    departmentCol.setMinWidth(40);
    
    TableColumn roleCol = new TableColumn("EMAIL");
    roleCol.setMinWidth(200);
    
     TableColumn levelCol = new TableColumn("LEVEL");
    levelCol.setMinWidth(40);
    
    TableColumn supervisorCol = new TableColumn("SUPERVISOR");
    supervisorCol.setMinWidth(40);
    
    TableColumn rateCol = new TableColumn("RATE");
    rateCol.setMinWidth(200);
    
      TableColumn insCol = new TableColumn("INS");
    insCol.setMinWidth(40);
    
    TableColumn insidCol = new TableColumn("INSID");
    insidCol.setMinWidth(40);
    
    table.setItems(getDataObsList());
    TableColumn dependentsCol = new TableColumn("DEPENDENTS");
    dependentsCol.setMinWidth(200);
     
      table.setItems(getDataObsList());
    table.getColumns().addAll(firstNameCol, lastNameCol, addressCol, cityCol, stateCol, zipCol, emailCol, departmentCol, roleCol, levelCol, supervisorCol, rateCol, insCol, insidCol, dependentsCol);
    tableView.getChildren().addAll(table);
//    emailCol.setVisible(false);

//    Scene scene = new Scene(pane, 1000, 510);
//    primaryStage.setTitle("Employee Management System Login");         // Set the stage title
//    primaryStage.setScene(scene);               // Place the scene in the stage
//    primaryStage.show();  
//    primaryStage.showAndWait();

        StackPane root = new StackPane();
        root.getChildren().add(table);
        primaryStage.setScene(new Scene(root, 1900, 450));
        primaryStage.show();
        primaryStage.showAndWait();

        Stage record = new Stage();
        record.setTitle("Employee Management Application");
        BorderPane layout = new BorderPane();
        record.initModality(Modality.APPLICATION_MODAL);
        layout.setPadding(new Insets(10, 10, 10, 10));
    
     }
    );
    
    

       

  public ObservableList<ContactClass> getDataObsList() {
      
      
                Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;
        String query = "Select * from Whiteflour.Employees";
//        final String DB_URL = "jdbc:derby://localhost:1527/EmployeeDatabase", "whiteflour", "123456";
        
        ObservableList <ContactClass> dbData = FXCollections.observableArrayList();

        try {
//            // Create a connection to the database.
//            Connection con = DriverManager.getConnection(DB_URL);
//
//            // Create a Statement object.
//            Statement stmt = con.createStatement();
//
//            // Create a string with a SELECT statement.
//            String sqlStatement = "SELECT * FROM Whiteflour.Employees";
//
//            // Send the statement to the DBMS.
//            ResultSet rs = stmt.executeQuery(sqlStatement);
con = DriverManager.getConnection("jdbc:derby://localhost:1527/EmployeeDatabase", "whiteflour", "123456");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            // Display the contents of the result set.
            while (rs.next()) {
                      dbData.add(new ContactClass(rs.getString("FNAME"),
                      rs.getString("LNAME"),
                       rs.getString("ADDRESS"),
                        rs.getString("CITY"),
                        rs.getString("STATE"),
                        rs.getString("ZIP"),
                       rs.getString("EMAIL"),
                       rs.getString("DEPARTMENT"),
                       rs.getString("ROLE"),
                        rs.getString("LEVEL"),
                        rs.getString("SUPERVISOR"),
                        rs.getString("INS"),
                       rs.getString("INSID")));
            }

            // Close the connection.
            con.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return dbData;
    }


  
// Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 1000, 510);
    primaryStage.setTitle("Employee Management System Login");         // Set the stage title
    primaryStage.setScene(scene);               // Place the scene in the stage
    primaryStage.show();                        // Display the stage
    

     ImageView login = new ImageView();
     Image image1 = new Image("file:src\\image\\LOGIN.jpg");
     login.setImage(image1);
     login.setFitHeight(500);
     login.setFitWidth(700);
     pane.setLeft(login);
     pane.setStyle("-fx-background-color: LIGHTBLUE");
   
//public static void main(String args[]){  

//    
try{  
//    Class.forName("com.mysql.jdbc.Driver");  
Class.forName("org.apache.derby.jdbc.ClientDriver");
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
//       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
Class.forName("org.apache.derby.jdbc.ClientDriver");
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

} //End Class JFinkeldey_JavaIII_Project