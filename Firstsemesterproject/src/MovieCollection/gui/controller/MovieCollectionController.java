package MovieCollection.gui.controller;

import MovieCollection.be.Category;
import MovieCollection.be.Movie;
import MovieCollection.gui.model.CategoriesModel;
import MovieCollection.gui.model.TableViewMoviesModel;
import MovieCollection.gui.view.CategoryDialog;
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

        CategoryDialog dialog = new CategoryDialog();
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
        CategoryDialog dialog = new CategoryDialog();
        Optional<Category> result = dialog.showAndWait();
        result.ifPresent(response ->{
            try{
                this.categoriesModel.deleteCategory(response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public void handleFilterClick(ActionEvent actionEvent) {
        String query = filterTxtField.getText().toLowerCase(Locale.ROOT).strip();
        ObservableList<Movie> movies = movieTblView.getItems();
        ObservableList<Movie> queriedMovies = FXCollections.observableArrayList();

        for (Movie movie : movies)
        {
            if(movie.getName().contains(query))
            {
                queriedMovies.add(movie);
                break;
            }
            if(String.valueOf(movie.getImdbRating()).contains(query))
            {
                queriedMovies.add(movie);
                break;
            }
            if(String.valueOf(movie.getPrivateRating()).contains(query))
            {
                queriedMovies.add(movie);
                break;
            }
            for(Category category: movie.getCategory())
            {
                if(category.toString().contains(query))
                {
                    queriedMovies.add(movie);
                    break;
                }
            }
        }
        movieTblView.setItems(queriedMovies);
    }
}
