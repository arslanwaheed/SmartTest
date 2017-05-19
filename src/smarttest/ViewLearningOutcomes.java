/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author arslanwaheed
 */
public class ViewLearningOutcomes {

    public static Scene setScene() {
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();

        String url = "http://10.22.13.87/SmartTestDB.php";
        String datastr = "op=getLearningOutcomes";
        String response = null;
        try {
            response = Utils.httpsPost(url, datastr);
        } catch (Exception ex) {
            Logger.getLogger(CreateQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        LearningOutcomesArray learningOutcomes = new LearningOutcomesArray();

        if (response != null) {
            response = response.substring(0, response.length() - 1);
            learningOutcomes = (LearningOutcomesArray) Utils.toObj(response);
        }
        
        
        int size = learningOutcomes.loArray.size();
        String prevCategory = "";
        for(int i=0; i<size; i++){
            String category = learningOutcomes.loArray.get(i).category;
            String name = learningOutcomes.loArray.get(i).name;
            
            if(!prevCategory.equals(category)){
                prevCategory = category;
                Label categoryLabel = new Label(category);
                box.getChildren().add(categoryLabel);
                
            }
            Text tempText = new Text(name);
            box.getChildren().add(tempText);
        }

        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}
