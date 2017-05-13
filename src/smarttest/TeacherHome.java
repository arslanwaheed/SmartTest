package smarttest;

/**
 *
 * @author arslanwaheed
 */

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TeacherHome {
    
    public static Scene setScene(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //adding name label
        Label nameLabel = new Label("Teacher Name");
        nameLabel.setId("nameLabel");
        nameLabel.autosize();
        grid.add(nameLabel, 25, 0,30,1);
        
        //adding logout button
        Button logoutButton = new Button("Logout");
        grid.add(logoutButton, 65,0);
        
        //setting up action for logout button
        logoutButton.setOnAction((ActionEvent event) -> {
            //program what happens when logout clicked
        });
        
        //add a separator
        Separator separator1 = new Separator();  
        grid.add(separator1,0, 1, 67,1);
        //add another separator
        Separator separator2 = new Separator();  
        grid.add(separator2,0, 2, 67,1);
        
        //add submit button
        Button newTest = new Button("Create Test");
        grid.addRow(3, newTest);
        
        //setting up action for submit button
        newTest.setOnAction((ActionEvent event) -> {
            Scene makeTest = CreateTest.setScene();
            
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create Test");
            primaryStage.setScene(makeTest);
            primaryStage.showAndWait();
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

