package MovieCollection.gui.model;

import MovieCollection.be.Movie;
import MovieCollection.be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableViewMoviesModel {

    ObservableList<Movie> movieList;
    CategoryManager categoryManager;

    public TableViewMoviesModel()
    {
        movieList = FXCollections.observableArrayList();
        this.categoryManager = new CategoryManager();

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(categoryManager.returnQueriedCategory("action"));
        Movie testMovie = new Movie("bob", 4.2, 4.2, "whatever", categories);
        movieList.add(testMovie);
    }

    public void addMovie(Movie movie)
    {
        this.movieList.add(movie);
    }

    public void deleteMovie(Movie movie)
    {
        this.movieList.remove(movie);
    }

    public void editMovie(Movie selectedMovie, Movie editedMovie) {
        this.movieList.set(movieList.indexOf(selectedMovie), editedMovie);
    }

    public ObservableList getMovieList() {
        return movieList;
    }
}
