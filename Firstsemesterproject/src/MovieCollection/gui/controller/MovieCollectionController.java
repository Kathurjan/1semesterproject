package MovieCollection.gui.controller;

import MovieCollection.be.Movie;
import MovieCollection.gui.model.TableViewMoviesModel;
import MovieCollection.gui.view.MovieDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieCollectionController implements Initializable {
    public Button addMovieBtn;
    public Button deleteMovieBtn;
    public Button editMovieBtn;
    public Button addCategoryBtn;
    public Button deleteCategoryBtn;

    public TextField filterTxtField;

    public TableView<Movie> movieTblView;
    private TableViewMoviesModel tableViewMoviesModel;
    public TableColumn<Movie, String> tblColumnTitle;
    public TableColumn<Movie, String> tblColumnCategory;
    public TableColumn<Movie, String> tblColumnPersonalRating;
    public TableColumn<Movie, String> tblColumnIMDBRating;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableViewMoviesModel = new TableViewMoviesModel();
        movieTblView.setItems(tableViewMoviesModel.getMovieList());

        this.initTables();
    }

    private void initTables() {

        this.tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("categoryString"));
        this.tblColumnPersonalRating.setCellValueFactory(new PropertyValueFactory<>("privateRating"));
        this.tblColumnIMDBRating.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));

    }

    public void handleAddMovieClick(ActionEvent actionEvent) {

        MovieDialog dialog = new MovieDialog();
        Optional<Movie> result =dialog.showAndWait();
        result.ifPresent(response -> {
            try{
                this.tableViewMoviesModel.addMovie(response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    public void handleEditMovieClick(ActionEvent actionEvent) {

        /*Movie selectedMovie = movieTblView.getSelectionModel().getSelectedItem();
        if(selectedMovie != null){
            MovieDialog dialog = new MovieDialog();
            dialog.
        }

         */

    }

    public void handleDeleteMovieClick(ActionEvent actionEvent) {
    }

    public void handleAddCategoryClick(ActionEvent actionEvent) {
    }

    public void handleDeleteCategoryClick(ActionEvent actionEvent) {
    }
}
