/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author arslanwaheed
 */
public class EditTest {
    public static Scene setScene(int testId){
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        
        
        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
}
