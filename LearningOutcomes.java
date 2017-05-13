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

public class LearningOutcomes {
    public static Scene setScene(){
        GridPane grdp = new GridPane();
        grdp.setHgap(10);
        grdp.setVgap(10);
        grdp.setPadding(new Insets(10,25,10,25));
        
        Label TeacherName = new Label("Teacher Name: ___________");
        grdp.add(TeacherName, 0, 0);        

        Label LOG = new Label("Learning Outcome Goals:");
        grdp.add(LOG, 0, 2);   
        
        //Buttons
        Button btn1 = new Button("Previous Page");
        Button btn2 = new Button("Edit Learning Outcome");
        Button btn3 = new Button("Upload Learning Outcome");
        grdp.add(btn1, 0, 20);
        grdp.add(btn2, 0, 4);
        grdp.add(btn3, 0, 6);
        

        //Scene
        Scene LearningOutcomes = new Scene(grdp, 550, 350);
        return LearningOutcomes;
        
    }
}