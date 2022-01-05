package MovieCollection.gui.model;

import MovieCollection.be.Movie;
import MovieCollection.be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableViewMoviesModel {

    ObservableList<Movie> movieList;

    public TableViewMoviesModel()
    {
        movieList = FXCollections.observableArrayList();

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Action"));
        categories.add(new Category("Drama"));
        Movie testMovie = new Movie("bob", 4.2, 4.2, "whatever", categories);
        movieList.add(testMovie);
    }

    public void addMovie(Movie movie)
    {
        this.movieList.add(movie);
    }

    public ObservableList getMovieList() {
        return movieList;
    }
}
