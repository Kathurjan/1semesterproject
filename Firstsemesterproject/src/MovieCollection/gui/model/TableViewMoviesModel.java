package MovieCollection.gui.model;

import MovieCollection.be.Movie;
import MovieCollection.be.Category;
import MovieCollection.bll.CategoryLogic;
import MovieCollection.bll.MovieLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TableViewMoviesModel {

    ObservableList<Movie> movieList;
    MovieLogic movieLogic;

    public TableViewMoviesModel()
    {
        movieList = FXCollections.observableArrayList();
        this.movieLogic = new MovieLogic();
        movieList.addAll(getAllMovies());
    }

    public void addMovie(Movie movie)
    {
        this.movieList.add(movie);
        movieLogic.addMovie(movie);
    }

    public void deleteMovie(Movie movie)
    {
        this.movieList.remove(movie);
    }

    public void editMovie(Movie selectedMovie, Movie editedMovie) {
        this.movieList.set(movieList.indexOf(selectedMovie), editedMovie);
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Movie> getAllMovies()
    {
        return movieLogic.getAllMovies();
    }
}
