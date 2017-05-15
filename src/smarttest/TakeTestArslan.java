/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author arslanwaheed
 */
public class TakeTestArslan {

    public static Scene setScene(int testID, Student student) {
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();

        try {
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=getFromDeployedTests&pincode=" + testID;
            String response = Utils.httpsPost(url, datastr);

            if (response != null) {
                response = response.substring(0, response.length() - 1);
                Test test = (Test) Utils.toObj(response);

                ArrayList<ToggleGroup> togglegroups = new ArrayList<>();

                for (Question question : test.questions) {
                    //add a separator
                    Separator separator = new Separator();
                    box.getChildren().add(separator);

                    Separator separator1 = new Separator();
                    box.getChildren().add(separator1);

                    Label questionLabel = new Label("Question: " + question.question);
                    box.getChildren().add(questionLabel);

                    Label pointsLabel = new Label("Points for this question: " + question.points);
                    box.getChildren().add(pointsLabel);

                    //add a separator
                    Separator separator2 = new Separator();
                    box.getChildren().add(separator2);

                    ToggleGroup group = new ToggleGroup();
                    group.setUserData(question.correctOption);
                    togglegroups.add(group);

                    int optionsCount = 1;
                    for (String op : question.options) {
                        RadioButton rb = new RadioButton(op);
                        rb.setToggleGroup(group);
                        rb.setUserData(optionsCount);
                        if (optionsCount == 1) {
                            rb.setSelected(true);
                        } else {
                            rb.setSelected(false);
                        }
                        box.getChildren().add(rb);
                        optionsCount++;
                    }

                }

                Button submitButton = new Button("Submit Test");
                box.getChildren().add(submitButton);

                submitButton.setOnAction((ActionEvent event) -> {
                    int totalPointsObtained = 0;
                    for (int i = 0; i < togglegroups.size(); i++) {
                        ToggleGroup group = togglegroups.get(i);
                        test.questions.get(i).selectedOption = parseInt(group.getSelectedToggle().getUserData().toString());

                        if (test.questions.get(i).correctOption == parseInt(group.getSelectedToggle().getUserData().toString())) {
                            System.out.println("question#" + (i + 1) + " is correct");
                            totalPointsObtained += test.questions.get(i).points;

                        } else {
                            System.out.println("question#" + (i + 1) + " is wrong");
                        }
                    }
                    test.pointsObtained = totalPointsObtained;
                    test.students.add(student);
                    
                    //update test in database
                    String testString = Utils.toStr(test);
                    String datastr2 = "op=updateTest&str="+testString+"&pincode="+testID;
                    try {
                        System.out.println(Utils.httpsPost(url, datastr2));
                    } catch (Exception ex) {
                        System.out.println("Exception in updateing test in TakeTest caught: " + ex);
                    }
                    
                    student.TakenTests.add(test);
                    //update student in database
                    String userString = Utils.toStr(student);
                    String datastr3 = "op=updateUser&uname="+student.Username+"&password="+student.Password+"&str="+userString;
                    try {
                        System.out.println(Utils.httpsPost(url, datastr3));
                    } catch (Exception ex) {
                        System.out.println("Exception in updateing user in TakeTest caught: " + ex);
                    }
                    
                    
                    Alert resultDisplayAlert = new Alert(Alert.AlertType.INFORMATION);
                    resultDisplayAlert.setContentText("You have got "+totalPointsObtained+" points out of "+test.totalPoints);
                    resultDisplayAlert.showAndWait();
                    
                    
                    Stage s = (Stage) submitButton.getScene().getWindow();
                    s.close();
                });
            }

        } catch (Exception ex) {
            System.out.println("Exception caught in TakeTest :" + ex);
        }

        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}