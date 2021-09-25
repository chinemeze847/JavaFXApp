
package fxPackage;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SnakeGame extends Application {
    Random gen = new Random();
    
    
    @Override
    public void start(Stage primaryStage) 
    {
        
        GamePanel gamePane = new GamePanel();
        double xPos = gen.nextInt(200);
        double yPos = gen.nextInt(200);
        
        //BorderPane
        Food  food = new Food(xPos,yPos,5);
        BorderPane borderPane = new BorderPane(gamePane);
        borderPane.getChildren().add(food);
        
        Scene scene = new Scene(borderPane, 500, 500);
        
        primaryStage.setTitle("Snake Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public class GamePanel extends Pane
    {
        
        Timeline animation;
        Color color ;
        Snake snake;
        
        Random gen ;
        
        public GamePanel()
        {
            color = Color.RED;
            snake = new Snake(50,50 ,10,10, color);
            getChildren().add(snake);
   
//          animation = new Timeline(new KeyFrame(Duration.millis(100),e -> moveSnake()));
//          animation.setCycleCount(Timeline.INDEFINITE);
//          animation.play();
        }
        
        protected void moveSnake()
        {
            
        }
        
        public void add()
        {
            getChildren().add(new Snake(30,30,20,20,color));
        }
        
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    class Snake extends Rectangle
    {
        int dx, dy;
        
        Snake(int x, int y, int s1,int s2, Color color )
        {
            super(x ,y ,s1,s2);
            setFill(color);
        }
    }
    
    class  Food extends Circle
    {
        Food(double x, double y, int radius)
        {
          super(x,y,radius);
          setFill(Color.BLUE);
        }
    }
}
