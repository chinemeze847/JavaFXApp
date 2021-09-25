/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxPackage;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 *
 * @author Eze
 */
public class TestGame extends Application {
    Food food;
    double xPos;
    double yPos;
    @Override
    public void start(Stage primaryStage) 
    {
        GamePane pane = new GamePane();
        BorderPane root = new BorderPane(pane);

        Scene scene = new Scene(root, 300, 300);
        
        primaryStage.setTitle("Snake Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public class GamePane extends Pane
    {
        
        Timeline animation;
        Color color ;
        Snake snake;
        
        Random gen ;
        
        public GamePane()
        {
          draw();
//          animation = new Timeline(new KeyFrame(Duration.millis(100),e -> moveSnake()));
//          animation.setCycleCount(Timeline.INDEFINITE);
//          animation.play();
        }
        
        public void draw()
        {
            color = Color.RED;
            xPos = Math.random()*200;
            yPos = Math.random()*200;
            snake = new Snake(50,50 ,10,10, color);
            food = new Food(xPos,yPos,5);
            getChildren().addAll(food,snake);
        }
        protected void moveSnake()
        {
//            draw();
//            snake.setX(snake.getX() + 1);
        }
        
        public void add()
        {
            getChildren().add(new Snake(30,30,20,20,color));
        }
        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    class  Food extends Circle
    {
        Food(double x, double y, int r)
        {
          super(x,y,r);
          setFill(Color.BLUE);
        }
  
    }
    
}
