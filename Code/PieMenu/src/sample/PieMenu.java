package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.awt.geom.Arc2D;


public class PieMenu extends Application {

    public enum States{
        Init,
        Open,
        Suivant,
        Précédent,
        Supprimer,
        Modifier
    }

    public enum MousePos{
        /*
             |
          Q2 |  Q1
        -----O-----
          Q3 |  Q4
             |
         */
        Out,
        Q1,
        Q2,
        Q3,
        Q4
    }

    public class MyPos{
        public double x,y;

        public MyPos(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    Pane canvas;
    States state;       // État actuel du "systeme"
    MyPos pmCenter;     // Position du centre du pie menu
    Integer sizeOut;    // Taille de la couronne extérieure
    Integer sizeIn;     // Taille de la couronne intérieure

    public PieMenu(){
        state = States.Init;
        sizeIn = 20;
        sizeOut = 150;
    }



    public void OpenPM(MyPos pos){
        Rectangle rect1, rect2, rect3, rect4;
        Circle circleOut, circleIn;
        Shape arc1, arc2, arc3, arc4;

        rect1 = new Rectangle(pos.x, pos.y - sizeOut, sizeOut, sizeOut);
        rect2 = new Rectangle(pos.x - sizeOut, pos.y, sizeOut, sizeOut);
        rect3 = new Rectangle(pos.x - sizeOut, pos.y - sizeOut, sizeOut, sizeOut);
        rect4 = new Rectangle(pos.x, pos.y, sizeOut, sizeOut);

        circleOut = new Circle(sizeOut);
        circleOut.relocate((pos.x - sizeOut), (pos.y - sizeOut));
        circleIn = new Circle(sizeIn);
        circleIn.relocate((pos.x - sizeIn), (pos.y - sizeIn));

        Shape donnut = Shape.subtract(circleOut, circleIn);

        arc1 = Shape.intersect(donnut,rect1);
        arc2 = Shape.intersect(donnut,rect2);
        arc3 = Shape.intersect(donnut,rect3);
        arc4 = Shape.intersect(donnut,rect4);

        arc1.setFill(Color.LIGHTGRAY);
        arc1.setStroke(Color.DARKGRAY);
        arc1.setStrokeWidth(4);
        arc2.setFill(Color.LIGHTGRAY);
        arc2.setStroke(Color.DARKGRAY);
        arc2.setStrokeWidth(4);
        arc3.setFill(Color.LIGHTGRAY);
        arc3.setStroke(Color.DARKGRAY);
        arc3.setStrokeWidth(4);
        arc4.setFill(Color.LIGHTGRAY);
        arc4.setStroke(Color.DARKGRAY);
        arc4.setStrokeWidth(4);

        canvas.getChildren().add(arc1);
        canvas.getChildren().add(arc2);
        canvas.getChildren().add(arc3);
        canvas.getChildren().add(arc4);

    }

    public void OpenPM(MyPos pos, Integer quartier){
        Rectangle rect1, rect2, rect3, rect4;
        Circle circleOut, circleIn;
        Shape arc1, arc2, arc3, arc4;

        rect1 = new Rectangle(pos.x, pos.y - sizeOut, sizeOut, sizeOut);
        rect2 = new Rectangle(pos.x - sizeOut, pos.y, sizeOut, sizeOut);
        rect3 = new Rectangle(pos.x - sizeOut, pos.y - sizeOut, sizeOut, sizeOut);
        rect4 = new Rectangle(pos.x, pos.y, sizeOut, sizeOut);

        circleOut = new Circle(sizeOut);
        circleOut.relocate((pos.x - sizeOut), (pos.y - sizeOut));
        circleIn = new Circle(sizeIn);
        circleIn.relocate((pos.x - sizeIn), (pos.y - sizeIn));

        Shape donnut = Shape.subtract(circleOut, circleIn);

        arc1 = Shape.intersect(donnut,rect1);
        arc2 = Shape.intersect(donnut,rect3);
        arc3 = Shape.intersect(donnut,rect2);
        arc4 = Shape.intersect(donnut,rect4);

        arc1.setFill(Color.LIGHTGRAY);
        arc1.setStroke(Color.DARKGRAY);
        arc1.setStrokeWidth(4);
        arc2.setFill(Color.LIGHTGRAY);
        arc2.setStroke(Color.DARKGRAY);
        arc2.setStrokeWidth(4);
        arc3.setFill(Color.LIGHTGRAY);
        arc3.setStroke(Color.DARKGRAY);
        arc3.setStrokeWidth(4);
        arc4.setFill(Color.LIGHTGRAY);
        arc4.setStroke(Color.DARKGRAY);
        arc4.setStrokeWidth(4);

        if(quartier == 1){
            arc1.setFill(Color.GRAY);
        }
        if(quartier == 2){
            arc2.setFill(Color.GRAY);
        }
        if(quartier == 3){
            arc3.setFill(Color.GRAY);
        }
        if(quartier == 4){
            arc4.setFill(Color.GRAY);
        }



        canvas.getChildren().add(arc1);
        canvas.getChildren().add(arc2);
        canvas.getChildren().add(arc3);
        canvas.getChildren().add(arc4);

    }

    public void OnRightClic(MouseEvent e){
        switch (state) {
            case Init:
                // Rien
                break;
            case Open:
                // Fermer PM puis Ouvrir PM
                break;
            case Suivant:
                // Rien
                break;
            case Précédent:
                // Rien
                break;
            case Supprimer:
                // Rien
                break;
            case Modifier:
                // Rien
                break;
        }
    }

    public void OnLeftClic(MouseEvent e){
        switch (state) {
            case Init:
                // Ouvrir PM
                pmCenter = new MyPos(e.getX(),e.getY());
                OpenPM(pmCenter);
                state=States.Open;
                break;
            case Open:
                // Fermer PM
                break;
            case Suivant:
                // Action Suivant
                break;
            case Précédent:
                // Action Précédent
                break;
            case Supprimer:
                // Action
                break;
            case Modifier:
                // Action Modifier
                break;
        }
    }

    public void OnMouseMove(MouseEvent e) {
        switch (state) {
            case Init:
                // rien
                break;
            case Open:
                ChangeStateFctPos(e);
                ChangeGraphic();
                break;
            case Suivant:
                ChangeStateFctPos(e);
                ChangeGraphic();
                break;
            case Précédent:
                ChangeStateFctPos(e);
                ChangeGraphic();
                break;
            case Supprimer:
                ChangeStateFctPos(e);
                ChangeGraphic();
                break;
            case Modifier:
                ChangeStateFctPos(e);
                ChangeGraphic();
                break;
        }
    }

    public void ChangeGraphic(){
        switch (state){
            case Init:
                //close PM
                break;
            case Open:
                OpenPM(pmCenter);
                break;
            case Suivant:
                OpenPM(pmCenter,2);
                break;
            case Précédent:
                OpenPM(pmCenter,1);
                break;
            case Supprimer:
                OpenPM(pmCenter,3);
                break;
            case Modifier:
                OpenPM(pmCenter,4);
                break;
        }
    }

    public MousePos getMousePos(MyPos pos){
        // retourne la position actuelle du curseur en fonction des coordonées polaire du pointeur

        // calcul de la distance
        double distance = Math.hypot(pos.x - pmCenter.x, pos.y - pmCenter.y);

        // calcul de l'angle
        float angle = (float) Math.toDegrees(Math.atan2(pos.x - pmCenter.x, pos.y - pmCenter.y)) - 90;
        if(angle < 0){                  //        90°
                                        //      Q2 | Q1
            angle += 360;               //  180°---O--- 0°
            angle = angle % 360;        //      Q3 | Q4
        }                               //        270°

        if(distance > sizeIn & distance < sizeOut){
            if(0 < angle & angle < 90){
                System.out.println("Q1");
                return MousePos.Q1;}
            else if(90 < angle & angle < 180){
                System.out.println("Q2");
                return MousePos.Q2;}
            else if(180 < angle & angle < 270){
                System.out.println("Q3");
                return MousePos.Q3;}
            else if(270 < angle & angle < 360){
                System.out.println("Q4");
                return MousePos.Q4;}
        }
        System.out.println("Out");
        return MousePos.Out;
    }

    public void ChangeStateFctPos(MouseEvent e) {
        // fonction qui change l'état de la state machine en fonction de la position du curseur
        MyPos pos =new MyPos(e.getX(), e.getY());
        MousePos mp = getMousePos(pos);
        switch (mp) {
            case Out:
                state = States.Open;
                break;
            case Q1:
                state = States.Précédent;
                break;
            case Q2:
                state = States.Suivant;
                break;
            case Q3:
                state = States.Supprimer;
                break;
            case Q4:
                state = States.Modifier;
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;");
        canvas.setPrefSize(400, 400);
        canvas.setOnMousePressed(MousePressedEventHandler);
        canvas.setOnMouseMoved(MouseMouseEventEventHandler);


        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    EventHandler<MouseEvent> MousePressedEventHandler =
            e -> {
                System.out.println(e.getButton());
                if(e.getButton() == MouseButton.PRIMARY){
                    OnLeftClic(e);
                }
                else if (e.getButton() == MouseButton.SECONDARY){
                    OnRightClic(e);
                }

            };

    EventHandler<MouseEvent> MouseMouseEventEventHandler =
            e -> {
                OnMouseMove(e);
            };
}