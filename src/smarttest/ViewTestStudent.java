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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author arslanwaheed
 */
public class ViewTestStudent {

    public static Scene setScene(Test test) {

        ArrayList<Question> questions = test.getQuestions();
        VBox box = new VBox();

        Label testLabel = new Label("Test Pincode #" + test.testID);
        box.getChildren().add(testLabel);

        Label testPointsLabel = new Label("Total Points " + test.totalPoints);
        box.getChildren().add(testPointsLabel);

        ScrollPane pane = new ScrollPane();
        for (int i = 0; i < questions.size(); i++) {
            //add a separator
            Separator separator = new Separator();
            box.getChildren().add(separator);

            Separator separator1 = new Separator();
            box.getChildren().add(separator1);

            Label questionLabel = new Label("Question #" + (i + 1));
            box.getChildren().add(questionLabel);

            Label points = new Label("Points for this question: " + questions.get(i).getPoints());
            box.getChildren().add(points);

            Label correctOption = new Label("Correct Option: option# " + questions.get(i).correctOption);
            box.getChildren().add(correctOption);
            
            Label selectedOptionLabel = new Label("Selected Option: option# " + questions.get(i).selectedOption);
            box.getChildren().add(selectedOptionLabel);

            Label loLabel = new Label("Learning Outcomes for question:");
            box.getChildren().add(loLabel);

            ArrayList<LearningOutcome> outcomeArr = questions.get(i).getOutcomes();
            for (int k = 0; k < outcomeArr.size(); k++) {
                Text LO = new Text("Category: " + outcomeArr.get(k).category + ", Subcategory: " + outcomeArr.get(k).name);
                box.getChildren().add(LO);
            }
            //add a separator
            Separator separator2 = new Separator();
            box.getChildren().add(separator2);

            Text Q = new Text("Question: " + questions.get(i).getQuestion());
            box.getChildren().add(Q);

            ArrayList<String> op = questions.get(i).getOptions();
            for (int j = 0; j < op.size(); j++) {
                Text OP = new Text("Option# " + (j + 1) + " " + op.get(j));
                box.getChildren().add(OP);
            }
        }

        pane.setContent(box);
        pane.setFitToWidth(true);

        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}
