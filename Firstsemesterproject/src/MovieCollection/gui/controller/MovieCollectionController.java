package MovieCollection.gui.controller;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.be.Movie;
import MovieCollection.gui.model.CategoriesModel;
import MovieCollection.gui.model.TableViewMoviesModel;
import MovieCollection.gui.view.CategoryDialogAdd;
import MovieCollection.gui.view.CategoryDialogDelete;
import MovieCollection.gui.view.ErrorAlert;
import MovieCollection.gui.view.MovieDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
        try {
            this.tableViewMoviesModel = new TableViewMoviesModel();
            this.categoriesModel = new CategoriesModel();
            movieTblView.setItems(tableViewMoviesModel.getMovieList());
            this.initTables();
        } catch (DataException e) {
            createAlertDialog(e);
            initialize(location, resources);
        }
    }

    private void initTables() {

        this.tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("categoryString"));
        this.tblColumnPersonalRating.setCellValueFactory(new PropertyValueFactory<>("privateRating"));
        this.tblColumnIMDBRating.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));

    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }

    public void handleAddMovieClick(ActionEvent actionEvent) {

        MovieDialog dialog = new MovieDialog();
        Optional<Movie> result =dialog.showAndWait();
        result.ifPresent(response -> {
            try{
                this.tableViewMoviesModel.addMovie(response);
            } catch (DataException e) {
                createAlertDialog(e);
                handleAddMovieClick(actionEvent);
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
                } catch (DataException e) {
                    createAlertDialog(e);
                    handleEditMovieClick(actionEvent);
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
          } catch (DataException e) {
              createAlertDialog(e);
              handleDeleteMovieClick(actionEvent);
          }
      }

    }

    public void handleAddCategoryClick(ActionEvent actionEvent) {

        CategoryDialogAdd dialog = new CategoryDialogAdd();
        Optional<Category> result =dialog.showAndWait();
        result.ifPresent(response -> {
            try{
                this.categoriesModel.addNewCategory(response);
            } catch (DataException e) {
                createAlertDialog(e);
                handleAddCategoryClick(actionEvent);
            }
        });

    }

    public void handleDeleteCategoryClick(ActionEvent actionEvent) {
        CategoryDialogDelete dialog = new CategoryDialogDelete();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CLOSE) {
            try {
                this.tableViewMoviesModel.refresh();
                movieTblView.setItems(this.tableViewMoviesModel.getMovieList());
            } catch (DataException e) {
                createAlertDialog(e);
                handleDeleteCategoryClick(actionEvent);
            }
        }
    }

    public void handleFilterSearch(ActionEvent actionEvent) {
        ObservableList<Movie> filteredList = FXCollections.observableArrayList();
        List<Movie> result = tableViewMoviesModel.getMovieList().stream().filter(movie->
                movie.getName().toLowerCase().contains(filterTxtField.getText().toLowerCase().trim())).collect(Collectors.toList());
        filteredList.addAll(result);
        movieTblView.setItems(filteredList);
    }
}

