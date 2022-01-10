package MovieCollection.gui.controller;

import MovieCollection.be.Category;
import MovieCollection.be.Movie;
import MovieCollection.gui.model.CategoriesModel;
import MovieCollection.gui.model.TableViewMoviesModel;
import MovieCollection.gui.view.CategoryDialogAdd;
import MovieCollection.gui.view.CategoryDialogDelete;
import MovieCollection.gui.view.MovieDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Locale;
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
    public Button btnFilterSearch;
    private TableViewMoviesModel tableViewMoviesModel;
    public TableColumn<Movie, String> tblColumnTitle;
    public TableColumn<Movie, String> tblColumnCategory;
    public TableColumn<Movie, String> tblColumnPersonalRating;
    public TableColumn<Movie, String> tblColumnIMDBRating;

    private CategoriesModel categoriesModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableViewMoviesModel = new TableViewMoviesModel();
        this.categoriesModel = new CategoriesModel();
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

        Movie selectedMovie = movieTblView.getSelectionModel().getSelectedItem();
        if(selectedMovie != null){
            MovieDialog dialog = new MovieDialog();
            dialog.setFields(selectedMovie);
            Optional<Movie> result = dialog.showAndWait();
            result.ifPresent(response -> {
                response.setId(selectedMovie.getId());
                try{
                    this.tableViewMoviesModel.editMovie(selectedMovie, response);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }


    }

    public void handleDeleteMovieClick(ActionEvent actionEvent) {

      Movie selectedMovie = movieTblView.getSelectionModel().getSelectedItem();

      if(selectedMovie!=null)
      {
          try{
              this.tableViewMoviesModel.deleteMovie(selectedMovie);
          } catch (Exception exception) {
              exception.printStackTrace();
          }
      }

    }

    public void handleAddCategoryClick(ActionEvent actionEvent) {

        CategoryDialogAdd dialog = new CategoryDialogAdd();
        Optional<Category> result =dialog.showAndWait();
        result.ifPresent(response -> {
            try{
                this.categoriesModel.addNewCategory(response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    public void handleDeleteCategoryClick(ActionEvent actionEvent) {
        CategoryDialogDelete dialog = new CategoryDialogDelete();
        dialog.show();
    }

    public void handleFilterClick(ActionEvent actionEvent) {

    }
}
