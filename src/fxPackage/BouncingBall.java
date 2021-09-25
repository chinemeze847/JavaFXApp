
package fxPackage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BouncingBall extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        
        BorderPane pane =new BorderPane();
        
        Pane circlePane = new Pane();
        try{
        FileInputStream stream = new FileInputStream("C:\\Users\\Eze\\Desktop\\image\\anime.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        pane.getChildren().add(imageView);
        }catch(FileNotFoundException e)
        {
            System.out.println("File does not exist");
        }
        
        
        circlePane.setOnMousePressed(e ->
        {
            double xPos = e.getX();
            double yPos = e.getY();
            Color color = new Color(Math.random(),Math.random(),Math.random(),0.5);
            Circle circle = new Circle(xPos,yPos,30,color);
            KeyValue start = new KeyValue(circle.radiusProperty(),10);
            KeyValue end = new KeyValue(circle.radiusProperty(),circlePane.getHeight()/2.0);
            KeyFrame frame = new KeyFrame(Duration.seconds(10),start,end);
            Timeline timeline = new Timeline(frame);
            timeline.play();
        
            circlePane.getChildren().add(circle);
        });
        
        pane.setCenter(circlePane);
        
        
        Scene scene = new Scene(pane, 400, 300);
        
        primaryStage.setTitle("Testing something!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
