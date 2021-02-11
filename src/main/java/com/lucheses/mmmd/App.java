package com.lucheses.mmmd;

import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author lucheses
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage = null;

    private static double xOffset;
    private static double yOffset;

    @Override
    public void start(Stage stage) throws IOException {
        Sessao.terminar(); // Só para certificar que a sessao está em condições
        
        scene = new Scene(loadFXML("fxml/LoginUI"));

        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.setScene(scene);
        

        App.stage = stage;

        stage.show();
    }
    
    public static void tornarArrastavel(AnchorPane parent) {
        parent.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
            App.stage.setX(event.getScreenX() - xOffset);
            App.stage.setY(event.getScreenY() - yOffset);
            App.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
            App.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((event) -> {
            App.stage.setOpacity(1.0f);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void novaJanela(String fxml) throws IOException {
        stage = new Stage();
        scene = new Scene(App.loadFXML(fxml));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
