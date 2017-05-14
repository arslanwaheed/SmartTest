/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

/**
 *
 * @author arslanwaheed
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class StudentHome {
    
    public static Scene setScene(Student s){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //adding name label
        Label nameLabel = new Label(s.FirstName + " " + s.LastName);
        nameLabel.setId("nameLabel");
        nameLabel.autosize();
        grid.add(nameLabel, 25, 0,30,1);
        
        //adding logout button
        Button logoutButton = new Button("Logout");
        grid.add(logoutButton, 65,0);
        
        //setting up action for logout button
        logoutButton.setOnAction((ActionEvent event) -> {
            Stage tempStage = new Stage();
            Scene scene = LoginPage.setScene();
            tempStage.setScene(scene);
            tempStage.show();
            
            Stage st = (Stage)logoutButton.getScene().getWindow();
            st.close();
        });
        
        //add a separator
        Separator separator1 = new Separator();  
        grid.add(separator1,0, 1, 67,1);
        //add another separator
        Separator separator2 = new Separator();  
        grid.add(separator2,0, 2, 67,1);
        
        //add text field for pincode
        TextField pincode = new TextField("Enter pincode for test");
        grid.add(pincode, 15, 3, 40, 1);
        
        //add submit button
        Button submitPincode = new Button("Take Test");
        grid.addRow(3, submitPincode);
        
        //setting up action for submit button
        submitPincode.setOnAction((ActionEvent event) -> {
            String pin = pincode.getText();
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=getFromDeployedTests&pincode="+pin;
            
            String response;
            try {
                response = Utils.httpsPost(url, datastr);
                if(response != null){
                    response = response.substring(0, response.length()-1);
                    Test tempTest = (Test)Utils.toObj(response);
                }
            } catch (Exception ex) {
                System.out.println("Exception in submit pincode button:"+ ex);
            }

        });
        
        //add a separator
        Separator separator3 = new Separator();  
        grid.add(separator3,0, 4, 67,1);
        
        //adding Test History label
        Label historyLabel = new Label("Test History");
        historyLabel.setId("historyLabel");
        historyLabel.autosize();
        grid.add(historyLabel, 25, 5,30,1);
        
        //get all tests and display them
        
        TestsArray tests = new TestsArray();
        //get tests from server
        
        for(Test test : tests){           
            Label testTitle = new Label(""+test.testID);
            grid.addColumn(1, testTitle);
        }
        
        
        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
    

}
