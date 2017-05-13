/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jesusgaitan
 */
public class UndeployedTests {
    public static Scene setScene(Teacher t) {
        GridPane grdp = new GridPane();
        //grdp.setAlignment(Pos.CENTER);
        grdp.setHgap(10);
        grdp.setVgap(10);
        grdp.setPadding(new Insets(10,25,10,25));
        
        Label TeacherName = new Label("Teacher Name: ___________");
        grdp.add(TeacherName, 0, 0);        

        Label UT = new Label("Undeployed Tests:");
        grdp.add(UT, 0, 2);   
        
        
        TestsArray tests = t.UndeployedTests;
        for(int i =0; i < tests.testArray.size(); i++){
            tests.testArray.get(i);
        }
        
        
        //Buttons
        Button btn1 = new Button("Previous Page");
        Button btn2 = new Button("Edit Test");
        grdp.add(btn1, 0, 10);
        grdp.add(btn2, 0, 4);
        

        //Scene
        Scene UndeployedTests = new Scene(grdp, 550, 350);
        return UndeployedTests;
    }


    
}