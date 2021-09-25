
package fxPackage;

import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MultipleBouncingBalls extends Application {
    
    @Override
    public void start(Stage primaryStage) {
          //Instantiating the multiple ball pane class
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("fx-border-color: yellow; fx-backgroundcolor: blue;");
        
        //Components of the lower button
        Button btnAdd = new Button("+");
        Button btnSub = new Button("-");
        Button suspend = new Button("Suspend");
        Button resume = new Button("Resume");
        HBox box =new HBox(10);
        box.getChildren().addAll(suspend,resume,btnAdd,btnSub);
        box.setAlignment(Pos.CENTER);
        
        //event listener on button
        btnAdd.setOnAction(e -> ballPane.add());
        btnSub.setOnAction(e -> ballPane.subtract());
        
        //setting on mouse listener to ball pane
        suspend.setOnMousePressed(e -> ballPane.pause());
        resume.setOnMouseReleased(e -> ballPane.play());
        
        //scroll bar to control ball speed
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMax(40);
        scrollBar.setValue(10);
        ballPane.rateProperty().bind(scrollBar.valueProperty());
        
        //Instantiating a Border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(ballPane);
        borderPane.setTop(scrollBar);
        borderPane.setBottom(box);
        
        
        Scene scene = new Scene(borderPane, 300, 250);
        primaryStage.setTitle("Multiple Bouncing Balls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private  class MultipleBallPane extends Pane
    {
        private  Timeline animation ;
        
        public MultipleBallPane()
        {
            animation = new Timeline(new KeyFrame(Duration.millis(100),e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
        
        public void add()
        {
            Color color = new Color(Math.random(), Math.random()
                    , Math.random(),0.7);
            getChildren().add(new Ball(30,30,20,color));
        }
        
        public void subtract()
        {
            if(getChildren().size() > 0)
                getChildren().remove(getChildren().size()-1);
        }
        
        public void increasedSpeed()
        {
            animation.setRate(animation.getRate() + 0.1);
        }
        
        public void decreasedSpeed()
        {
            animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1: 0);
        }
        
        public DoubleProperty rateProperty()
        {
            return animation.rateProperty();
        }
        
        public void play()
        {
            animation.play();
        }
        
        public void pause()
        {
            animation.pause();
        }
        
        protected void moveBall()
        {
            for(Node node: this.getChildren()) 
            {
                Ball ball = (Ball)node;
    
                if(ball.getCenterX ()< 0 || ball.getCenterX() + ball.getRadius() > getWidth())
                {
                    ball.dx *= -1;
                }
                if (ball.getCenterY() < ball.getRadius() ||
                ball.getCenterY() - ball.getRadius() > getHeight()) 
                {
                    ball.dy *= -1;
                }
               
             // Adjust ball position
            ball.setCenterX(ball.dx + ball.getCenterX());
            ball.setCenterY(ball.dy + ball.getCenterY());
            }
             if(isCollide())
                {
                    System.out.println("it is true");
                }
        }

        class Ball extends Circle
        {
            private double dx =1, dy= 1;

            Ball(double x, double y, double radius, Color color)
            {
                super(x,y,radius);
                setFill(color);
            }
        }
        
        public boolean isCollide()
        {
            boolean isHit = false;
            List<Node> nodes  = this.getChildren();

            if(nodes.size() > 1)
            {
                
                    Ball ball1 = (Ball)nodes.remove(0);

                    for(int j=0; j<nodes.size(); j++)
                    {
                        Ball ball2 = (Ball)nodes.get(j);

                        double dist = getDist(ball1.getCenterX(),ball2.getCenterX(),
                                ball1.getCenterY(),ball2.getCenterY());
                        if(dist < ball1.getRadius() + ball2.getRadius())
                        {
                            Color color = new Color(Math.random(),Math.random(),Math.random(),0.5);
                            ball2.setFill(color);
                            this.getChildren().remove(ball2);
                            ball1.setRadius(ball1.getRadius() + ball2.getRadius());
                            isHit =true;
                        };     
                    }
                    nodes.add(ball1);  
            }

            return isHit;
        }
        
        public double getDist(double x1,double x2,double y1,double y2)
        {  
            double dist = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
            return dist;
        }
    }
}
        
     
