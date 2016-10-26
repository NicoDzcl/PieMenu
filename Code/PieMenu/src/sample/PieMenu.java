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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


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
        Circle circleOut = new Circle(sizeOut, Color.BLUE);
        circleOut.setCursor(Cursor.DEFAULT);
        circleOut.relocate((pos.x - sizeOut), (pos.y - sizeOut));
        Circle circleIn = new Circle(sizeIn, Color.RED);
        circleIn.setCursor(Cursor.DEFAULT);
        circleIn.relocate((pos.x - sizeIn), (pos.y - sizeIn));
        canvas.getChildren().add(circleOut);
        canvas.getChildren().add(circleIn);
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
                double x = e.getX();
                double y = e.getY();
                getMousePos(new MyPos(x, y));
                break;
            case Suivant:
                break;
            case Précédent:
                break;
            case Supprimer:
                break;
            case Modifier:
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