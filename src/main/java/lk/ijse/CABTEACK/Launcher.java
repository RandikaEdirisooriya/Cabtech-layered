package lk.ijse.CABTEACK;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    double x,y=0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //FXMLLoader.load(this.getClass().getResource("/View/main_form.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form1.fxml"));

        // stage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(rootNode);
        rootNode.setOnMousePressed(event -> {
            x=event.getSceneX();
            y=event.getSceneY();
        });
        rootNode.setOnMouseDragged(event -> {
            stage.setX(event.getSceneX()-x);
            stage.setY(event.getSceneY()-y);
        });
        stage.setTitle("Login Form");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setResizable(false);
        //scene.getStylesheets().add(getClass().getResource("styls.css").toExternalForm());

        stage.show();
    }
}
