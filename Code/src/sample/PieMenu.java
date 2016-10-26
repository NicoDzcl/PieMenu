package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
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

    public class MyPos{
        public double x,y;

        public MyPos(double x, double y){
            this.x = x;
            this.y = y;
        }
    }


    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    Pane canvas;
    States state;  // État actuel du "systeme"
    MyPos pmCenter;  // Position du centre du pie menu

    public PieMenu(){
        state = States.Init;
    }

    public void OpenPM(MyPos pos){
        Circle circle = new Circle(20, Color.BLUE);
        circle.setCursor(Cursor.DEFAULT);
        circle.relocate((pos.x - 10), (pos.y - 10));
        canvas.getChildren().add(circle);
    }

    public void OnRightClic(MouseEvent e){
        switch (state) {
            case Init:
                break;
            case Open:
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

    public void OnLeftClic(MouseEvent e){
        switch (state) {
            case Init:
                pmCenter = new MyPos(e.getX(),e.getY());
                OpenPM(pmCenter);
                state=States.Open;
                break;
            case Open:
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

    public void OnMouseMove() {
        switch (state) {
            case Init:
                // rien
                break;
            case Open:
                System.out.println("Coucou");
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
}