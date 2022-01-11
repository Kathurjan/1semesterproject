package MovieCollection.gui.model;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Movie;
import MovieCollection.be.OldMovieList;
import MovieCollection.bll.MovieLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckDateModel {

    MovieLogic movieLogic;
    ObservableList<Movie> movies;

    public CheckDateModel() throws DataException
    {
        this.movies = FXCollections.observableArrayList();
        this.movieLogic = new MovieLogic();
        movies.addAll(getOldMoviesList().getMovies());
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public OldMovieList getOldMoviesList() throws DataException
    {
       return movieLogic.getAllOldMovies();
    }
}
