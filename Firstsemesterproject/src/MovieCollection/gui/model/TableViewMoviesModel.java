package MovieCollection.gui.model;

import MovieCollection.Dal.Exceptions.DataException;
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

    public TableViewMoviesModel() throws DataException {
        movieList = FXCollections.observableArrayList();
        this.movieLogic = new MovieLogic();
        refresh();
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }

    public void addMovie(Movie movie) throws DataException {
        this.movieList.add(movieLogic.addMovie(movie));
    }

    public void deleteMovie(Movie movie) throws DataException {
        this.movieList.remove(movie);
        movieLogic.deleteMovie(movie);
    }

    public void editMovie(Movie selectedMovie, Movie editedMovie) throws DataException {
        editedMovie.setId(selectedMovie.getId());
        this.movieList.set(movieList.indexOf(selectedMovie), editedMovie);
        movieLogic.editMovie(editedMovie);

    }

    public void deleteAll(){
        this.movieList.remove(0, this.movieList.size());
    }


    public void refresh() throws DataException {
        deleteAll();
        this.movieList.addAll(movieLogic.getAllMovies());
    }

    public boolean checkDates()
    {
       return movieLogic.checkDates(this.movieList);
    }
}
