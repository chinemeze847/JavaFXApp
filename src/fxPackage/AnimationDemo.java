/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxPackage;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Eze
 */
public class AnimationDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        
        Circle circle = new Circle(0,0,30,Color.BLUE);
        root.getChildren().add(circle);
        KeyValue start = new KeyValue(circle.radiusProperty(),10);
        KeyValue end = new KeyValue(circle.radiusProperty(),root.getHeight()/2.0);
        KeyFrame frame = new KeyFrame(Duration.seconds(10),start,end);
        Timeline timeline = new Timeline(frame);
        timeline.play();
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Circle Animation Demo!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
