package MovieCollection.bll;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.Dal.MovieDao;
import MovieCollection.be.Movie;
import MovieCollection.be.OldMovieList;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

    public OldMovieList getAllOldMovies() throws DataException
    {
        ArrayList<Movie> movies = getAllMovies();
        OldMovieList oldMovieList = new OldMovieList();
        ArrayList<Movie> moviesToAdd = new ArrayList<>();

        for(Movie movie: movies)
        {
            if(movie.getLastview()!=null && movie.getLastview().isBefore(LocalDate.now().minusYears(2)) && movie.getPrivateRating()<6){
                moviesToAdd.add(movie);
                oldMovieList.setShouldBeReturned(true);
            }
        }
        oldMovieList.setMovies(moviesToAdd);
        return oldMovieList;
    }
    public void editLastViewMovie(Movie movie) throws DataException {
        movieDao.editLastViewMovie(movie);
    }
}
