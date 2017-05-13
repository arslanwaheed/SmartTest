/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Gib
 */
public class ViewTest {
    
    public static Scene setScene(){
        Test test = new Test();
      
        ArrayList<Question> questions = test.getQuestions();
        VBox box = new VBox();
        ScrollPane pane = new ScrollPane();
        for(int i = 0; i<questions.size(); i++){
            Label questionLabel = new Label("Question #" + (i+1));
            Text Q = new Text(questions.get(i).getQuestion());
            box.getChildren().add(Q);
            
            ArrayList<String> op = questions.get(i).getOptions();
            for(int j = 0; j<op.size(); j++){
                Text OP = new Text(op.get(j));
                box.getChildren().add(OP);
            }
            ArrayList<LearningOutcome> outcomeArr = questions.get(i).getOutcomes();
            for(int k = 0; k<outcomeArr.size(); k++){
                Text LO = new Text(outcomeArr.get(k).name + " " + outcomeArr.get(i).category);
                box.getChildren().add(LO);
            }
                    
        }
        
        pane.setContent(box);
        pane.setFitToWidth(true);
        
        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
    
}
