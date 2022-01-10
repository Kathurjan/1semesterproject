package MovieCollection.bll;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.Dal.MovieDao;
import MovieCollection.be.Movie;

import java.util.ArrayList;

public class MovieLogic {

    private MovieDao movieDao;

    public MovieLogic()
    {
        this.movieDao = new MovieDao();
    }

    public Movie addMovie(Movie movie) throws DataException {
        return movieDao.addMovie(movie);
    }

    public ArrayList<Movie> getAllMovies() throws DataException {
        return movieDao.getAllMovies();
    }

    public void deleteMovie(Movie movie) throws DataException { movieDao.deleteMovie(movie);
    }

    public void editMovie(Movie movie) throws DataException {
        movieDao.editMovie(movie);
    }
}
