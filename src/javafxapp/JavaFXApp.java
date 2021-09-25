/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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


public class JavaFXApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //Instantiating the multiple ball pane class
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("fx-border-color: yellow;");
        
        //Components of the lower button
        Button btnAdd = new Button("+");
        Button btnSub = new Button("-");
        HBox box =new HBox(10);
        box.getChildren().addAll(btnAdd,btnSub);
        box.setAlignment(Pos.CENTER);
        
        //event listener on button
        btnAdd.setOnAction(e -> ballPane.add());
        btnAdd.setOnAction(e -> ballPane.subtract());
        
        //setting on mouse listener to ball pane
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());
        
        //scroll bar to control ball speed
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMax(20);
        scrollBar.setValue(10);
        ballPane.rateProperty().bind(scrollBar.valueProperty());
        
        //Instantiating a Border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(ballPane);
        borderPane.setTop(scrollBar);
        borderPane.setBottom(box);
        
        
        Scene scene = new Scene(borderPane, 300, 250);
        stage.setTitle("Multiple Bouncing Balls");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private class MultipleBallPane extends Pane
    {
        private  Timeline animation ;
        
        public MultipleBallPane()
        {
            animation = new Timeline(new KeyFrame(Duration.millis(1000),e -> this.moveBall()));
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
            getChildren().stream().map(node -> (Ball)node).map(ball -> {
                if(ball.getCenterX() < ball.getRadius() || ball.getCenterX() > getWidth()-ball.getRadius())
                    ball.dx *= -1;
                return ball;                
            }).map(ball -> {
                if(ball.getCenterY() < ball.getRadius() || ball.getCenterY() > getHeight()-ball.getRadius())
                    ball.dy*= -1;
                return ball;                
            }).map(ball -> {
                ball.setCenterX(ball.dx + ball.getCenterX());
                return ball;
            }).forEachOrdered(ball -> {
                ball.setCenterY(ball.dy + ball.getCenterY());
            });
            
            
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
        
 }

    
   
    

