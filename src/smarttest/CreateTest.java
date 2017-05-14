/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreateTest {
    public static Scene setScene(Teacher t){
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        Test newTest = new Test();
        
        draw(newTest,box, t);

        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
    
    
    public static void draw(Test newTest, VBox box, Teacher t){
        if(newTest.questions.size() > 0){
            Label existingQuestionsLabel = new Label("Existing Questions");
            box.getChildren().add(existingQuestionsLabel);
            
            for(Question q : newTest.questions){
                
                Label questionLabel = new Label(q.question);
                box.getChildren().add(questionLabel);
                questionLabel.setId("questionLabel");
            }
        }

        //add two separators
        Separator separator1 = new Separator();
        box.getChildren().add(separator1);
        Separator separator2 = new Separator();
        box.getChildren().add(separator2);
        
        
        Label addQuestionLabel = new Label("Add Questions");
        box.getChildren().add(addQuestionLabel);
        
        
        TextField numberOfOptions = new TextField("Enter number of options you want. e.g 2 or 4");
        box.getChildren().add(numberOfOptions);
        
        //add a new question
        Button addQuestion = new Button("Add Question");
        box.getChildren().add(addQuestion);
        
        //add two separators
        Separator separator3 = new Separator();
        box.getChildren().add(separator3);
        Separator separator4 = new Separator();
        box.getChildren().add(separator4);
        
        //setting up action for addQuestion button
        //add a new question every time button is clicked
        addQuestion.setOnAction((ActionEvent event) -> {
            int num = parseInt(numberOfOptions.getText());
            Question question = new Question();

            Stage tempStage = new Stage();
            Scene tempScene = CreateQuestion.setScene(question,num);
            tempStage.setScene(tempScene);
            tempStage.showAndWait();
            
            newTest.addQuestion(question);
            box.getChildren().clear();
            draw(newTest,box,t);
        });
        
        //upload the test
        Button uploadTest = new Button("Upload Test");
        box.getChildren().add(uploadTest);
        
        uploadTest.setOnAction((ActionEvent event) -> {
            String url = "http://10.22.13.87/SmartTestDB.php";
            
            String str = Utils.toStr(newTest);
            String datastr = "op=addToDeployedTests&str="+str+"&pincode="+newTest.testID;
            
            try {
                String response = Utils.httpsPost(url, datastr);
                System.out.println("Response: "+response);
                response = response.substring(0, response.length()-1);
                if(response.equalsIgnoreCase("success!")){
                    t.DeployedTests.add(newTest);
                    System.out.println("Size: "+t.DeployedTests.size());
                }
                str = Utils.toStr(t);
                datastr = "op=updateUser&uname="+t.Username+"&password="+t.Password+"&str="+str;
                response = Utils.httpsPost(url, datastr);
                System.out.println("user update status: "+response);
                
                Stage st = (Stage)uploadTest.getScene().getWindow();
                st.close();
            } catch (Exception ex) {
                System.out.println("Exception caught: "+ ex);
            }
            
        });
        
        //save the test
        Button saveTest = new Button("Save Test");
        box.getChildren().add(saveTest);
        
        saveTest.setOnAction((ActionEvent event) -> {
            try {
                t.UndeployedTests.add(newTest);
                
                String url = "http://10.22.13.87/SmartTestDB.php";
                String str = Utils.toStr(t);
                String datastr = "op=updateUser&uname="+t.Username+"&password="+t.Password+"&str="+str;
                String response = Utils.httpsPost(url, datastr);
                System.out.println("user update status: "+response);
                
                Stage st = (Stage)saveTest.getScene().getWindow();
                st.close();
            } catch (Exception ex) {
                System.out.println("Exception caught in saveTestButton: "+ ex);
            }
        });
        
    }
}