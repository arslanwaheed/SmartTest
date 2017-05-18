/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import static java.lang.Integer.parseInt;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author arslanwaheed
 */
public class EditTest {
    public static Scene setScene(Test test, Teacher t){
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        Label testLabel = new Label("Test Pincode #" + test.testID);
        box.getChildren().add(testLabel);
            
        Label totalPointsLabel = new Label("Total Points " + test.totalPoints);
        box.getChildren().add(totalPointsLabel);
        
        
        TextField numberOfOptions = new TextField("Enter number of options for new question");
        box.getChildren().add(numberOfOptions);
        
        Button addQuestionButton = new Button("ADD Question");
        box.getChildren().add(addQuestionButton);
        
        addQuestionButton.setOnAction((ActionEvent event) -> {
            int num = parseInt(numberOfOptions.getText());
            Question question = new Question();

            Stage tempStage = new Stage();
            Scene tempScene = CreateQuestion.setScene(question, num);
            tempStage.setScene(tempScene);
            tempStage.showAndWait();
            test.addQuestion(question);

            String str = Utils.toStr(t);
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=updateUser&uname=" + t.Username + "&password=" + t.Password + "&str=" + str;
            String response = "unsuccessfull operation";
            try {
                response = Utils.httpsPost(url, datastr);
            } catch (Exception ex) {
                System.out.println("Exception caught in EditTest: " + ex);
            }
            System.out.println("user update status: " + response);

            Stage temporaryStage = new Stage();
            Scene temporaryScene = EditTest.setScene(test, t);
            temporaryStage.setScene(temporaryScene);
            temporaryStage.show();

            Stage s = (Stage) addQuestionButton.getScene().getWindow();
            s.close();
                
        });
        
        
        Button uploadButton = new Button("Upload Test");
        box.getChildren().add(uploadButton);
        
        uploadButton.setOnAction((ActionEvent event) -> {
            String url = "http://10.22.13.87/SmartTestDB.php";
            
            String str = Utils.toStr(test);
            String datastr = "op=addToDeployedTests&str="+str+"&pincode="+test.testID;
            
            try {
                String response = Utils.httpsPost(url, datastr);
                System.out.println("Response: "+response);
                response = response.substring(0, response.length()-1);
                if(response.equalsIgnoreCase("success!")){
                    t.UndeployedTests.remove(test);
                    t.DeployedTests.add(test);
                    System.out.println("Size: "+t.DeployedTests.size());
                }
                str = Utils.toStr(t);
                datastr = "op=updateUser&uname="+t.Username+"&password="+t.Password+"&str="+str;
                response = Utils.httpsPost(url, datastr);
                System.out.println("user update status: "+response);
                
                Stage st = (Stage)uploadButton.getScene().getWindow();
                st.close();
            } catch (Exception ex) {
                System.out.println("Exception caught: "+ ex);
            }
        });
        
        for(Question question: test.questions){
            //add a separator
            Separator separator = new Separator();
            box.getChildren().add(separator);

            Separator separator1 = new Separator();
            box.getChildren().add(separator1);

            Label questionLabel = new Label("Question: "+ question.question);
            box.getChildren().add(questionLabel);
            
            Label correctOptionLabel = new Label("Correct Option: option# " + question.correctOption);
            box.getChildren().add(correctOptionLabel);
            
            Label pointsLabel = new Label("Points for this question: " + question.points);
            box.getChildren().add(pointsLabel);
            
            Label loLabel = new Label("Learning Outcomes for question: ");
            box.getChildren().add(loLabel);
            
            for(LearningOutcome lo : question.learningOutcomes){
                Text loText = new Text("Category: " + lo.category + ", Subcategory: " + lo.name);
                box.getChildren().add(loText);
            }
            
            //add a separator
            Separator separator2 = new Separator();
            box.getChildren().add(separator2);
            int count;
            count = 1;
            for(String op : question.options){
                Text opText = new Text("Option"+count+" : " + op);
                box.getChildren().add(opText);
                count++;
            }
            //add a separator
            Separator separator3 = new Separator();
            box.getChildren().add(separator3);
            
            Button deleteQuestionButton = new Button("Delete Question");
            box.getChildren().add(deleteQuestionButton);
            
            
            deleteQuestionButton.setOnAction((ActionEvent event) -> {
                test.removeQuestion(question);
                
                String str = Utils.toStr(t);
                String url = "http://10.22.13.87/SmartTestDB.php";
                String datastr = "op=updateUser&uname="+t.Username+"&password="+t.Password+"&str="+str;
                String response = "unsuccessfull operation";
                try {
                    response = Utils.httpsPost(url, datastr);
                } catch (Exception ex) {
                    System.out.println("Exception caught in EditTest: "+ex);
                }
                System.out.println("user update status: "+response);
                
                Stage tempStage = new Stage();
                Scene tempScene = EditTest.setScene(test,t);
                tempStage.setScene(tempScene);
                tempStage.show();
                
                Stage s = (Stage)deleteQuestionButton.getScene().getWindow();
                s.close();
            });
        }
        
        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
}
