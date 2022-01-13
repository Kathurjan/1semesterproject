package MovieCollection.gui.model;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Movie;
import MovieCollection.be.Category;
import MovieCollection.bll.CategoryLogic;
import MovieCollection.bll.MovieLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean addMovie(Movie movie) throws DataException {
        Movie checkedMovie = movieLogic.addMovie(movie);
        if(checkedMovie!=null)
        {
            this.movieList.add(checkedMovie);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void deleteMovie(Movie movie) throws DataException {
        this.movieList.remove(movie);
        movieLogic.deleteMovie(movie);
    }

    public boolean editMovie(Movie selectedMovie, Movie editedMovie) throws DataException {
        editedMovie.setId(selectedMovie.getId());
        if(movieLogic.editMovie(editedMovie))
        {
            this.movieList.set(movieList.indexOf(selectedMovie), editedMovie);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void deleteAll(){
        this.movieList.remove(0, this.movieList.size());
    }


    public void refresh() throws DataException {
        deleteAll();
        this.movieList.addAll(movieLogic.getAllMovies());
    }

    public void openMovie(Movie movie)
    {
        Runtime runtime = Runtime.getRuntime();
        try {
            String[] command = {"cmd.exe", "/k", "Start", movie.getFileLink()};
            Process p =  runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
