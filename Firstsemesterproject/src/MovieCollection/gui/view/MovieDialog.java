package MovieCollection.gui.view;

import MovieCollection.be.Movie;
import MovieCollection.gui.controller.MovieDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class MovieDialog extends Dialog<Movie> {

    private MovieDialogController controller;

    public MovieDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/edit Movie");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Movie(controller.getTitle(), controller.getIMDB(), controller.getPersonalRating(), controller.getFilePath(), controller.getCategoryList());
                }
                return null;
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFields(Movie movie)
    {
        controller.setTitle(movie.getName());
    }

}
