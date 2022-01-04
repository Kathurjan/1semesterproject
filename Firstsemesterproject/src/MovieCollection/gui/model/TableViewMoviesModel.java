package MovieCollection.gui.model;

import MovieCollection.be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableViewMoviesModel {

    ObservableList<Movie> movieList;

    public TableViewMoviesModel()
    {
        movieList = FXCollections.observableArrayList();

        Movie testMovie = new Movie("bob", 4.2, 4.2, "whatever", 6, "action", "drama", "crime");
        movieList.add(testMovie);
    }

    public ObservableList getMovieList() {
        return movieList;
    }
}
