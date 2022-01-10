package MovieCollection.bll;

import MovieCollection.Dal.MovieDao;
import MovieCollection.be.Movie;

import java.util.ArrayList;

public class MovieLogic {

    private MovieDao movieDao;

    public MovieLogic()
    {
        this.movieDao = new MovieDao();
    }

    public Movie addMovie(Movie movie)
    {
        return movieDao.addMovie(movie);
    }

    public ArrayList<Movie> getAllMovies()
    {
        return movieDao.getAllMovies();
    }

}
