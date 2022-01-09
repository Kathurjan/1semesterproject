package MovieCollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public OpenMovie movie = new OpenMovie();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/MovieCollectionView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Collection");
        primaryStage.show();
        movie.testIt();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

