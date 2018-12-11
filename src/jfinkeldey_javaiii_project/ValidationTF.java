/**
 * SDEV 350 ~ Java Programming II
 * Author Name: Jeff
 * Assignment Name: jfinkeldey_javaii_week13
 * Date: Dec 10, 2017
 * Description: ValidationTF class to to incorporate validation routines
*/
package jfinkeldey_javaiii_project;

//Begin Class ValidationTF

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ValidationTF extends TextField {

    public Boolean Filled(String Field) {
        Boolean good = true;
        if ("".equals(getText())) {
            Warning("Missing field\n",Field + " is missing.");
            this.requestFocus();
            good = false;
        }
        return good;
    }
        
    public Boolean ValInt(String Field) {
        Boolean good = true;
        if (!"".equals(getText()) && (!getText().matches("^\\d+$")) || "".equals(getText())) {
            Warning("Invalid format\n",Field + " must be an integer greater than 0");
            this.requestFocus();
            good = false;
        }
        return good;
    }
    
    public Boolean ValDouble(String Field) {
        Boolean good = true;
        if (!"".equals(getText()) && (!getText().matches("^[\\+\\-]{0,1}[0-9]+[\\.]{1}[0-9]+$") || Double.valueOf(getText()) == 0.0)) {
            Warning("Invalid number format\n",Field + " must contain a number other than 0 with decimals and without commas");
            good = false;
            this.requestFocus();
        }
        return good;
    }

    public static void Warning(String header, String content) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }
    
} //End Class ValidationTF
