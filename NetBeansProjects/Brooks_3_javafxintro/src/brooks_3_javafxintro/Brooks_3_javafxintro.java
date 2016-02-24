/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks_3_javafxintro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 *
 * @author Clarity
 */
public class Brooks_3_javafxintro extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();
 //       drawShapes(gc);
        for(int i=0; i < 1000; i+=40 ){
        drawSquares1(gc, Math.random()*i, Math.random()*i,Math.random()*i,Math.random()*i);
        }
        for(int i=0; i < 1000; i+=40 ){
        drawSquares2(gc, Math.random()*i, Math.random()*i,Math.random()*i,Math.random()*i);
        }
        for(int i=0; i < 1000; i+=40 ){
        drawSquares2(gc, Math.random()*i+100, Math.random()*i+100,Math.random()*i,Math.random()*i);
        }
        for(int i=0; i < 1000; i+=40 ){
        drawSquares3(gc, Math.random()*i, Math.random()*i,Math.random()*i,Math.random()*i);
        }
        //drawHouse(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawSquares(GraphicsContext gc, double x, double y, double w, double h) {
               gc.setLineWidth(4);
               gc.setStroke (Color.web("BLUE", .5));
               gc.setFill(Color.web("BLUE",.1));
               gc.fillRect(x, y, w, h);
                gc.strokeRect(x,y,w,h);
               
    }
    
        private void drawSquares1(GraphicsContext gc, double x, double y, double w, double h) {
               gc.setLineWidth(3);
               gc.setStroke (Color.web("LIMEGREEN", .5));
               gc.setFill(Color.web("LIMEGREEN",.1));
               gc.fillRect(x, y, w, h);
                gc.strokeRect(x,y,w,h);
    }
        
            private void drawSquares2(GraphicsContext gc, double x, double y, double w, double h) {
               gc.setLineWidth(4);
               gc.setStroke (Color.web("PURPLE", .5));
               gc.setFill(Color.web("PURPLE",.1));
               gc.fillRect(x, y, w, h);
               gc.strokeRect(x,y,w,h);
    }
            
                private void drawSquares3(GraphicsContext gc, double x, double y, double w, double h) {
               gc.setLineWidth(3);
               gc.setStroke (Color.web("DARKBLUE", .5));
               gc.setFill(Color.web("DARKBLUE",.1));
               gc.fillRect(x, y, w, h);
                gc.strokeRect(x,y,w,h);
    }
    
                
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                       new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                         new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                          new double[]{210, 210, 240, 240}, 4);
    }

    /**
     * @param args the command line arguments
     */
 /*   public static void main(String[] args) {
        launch(args);
    }
    
}
*/