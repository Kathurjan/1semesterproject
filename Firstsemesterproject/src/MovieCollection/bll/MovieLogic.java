package MovieCollection.bll;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.Dal.MovieDao;
import MovieCollection.be.Movie;
import MovieCollection.be.OldMovieList;
import java.time.LocalDate;
import java.util.ArrayList;


public class MovieLogic {

    private MovieDao movieDao;

    public MovieLogic()
    {
        this.movieDao = new MovieDao();
    }

    public Movie addMovie(Movie movie) throws DataException {
        if(checkRatings(movie))
        {
            return movieDao.addMovie(movie);
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Movie> getAllMovies() throws DataException {
        return movieDao.getAllMovies();
    }

    public void deleteMovie(Movie movie) throws DataException { movieDao.deleteMovie(movie);
    }

    public boolean editMovie(Movie movie) throws DataException {
        if(checkRatings(movie)) {
            movieDao.editMovie(movie);
            return true;
        }
        else
        {
            return false;
        }
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

    private boolean checkRatings(Movie movie)
    {
        if(movie.getImdbRating() > 10 || movie.getPrivateRating() > 10) {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void editLastViewMovie(Movie movie) throws DataException {
        movieDao.editLastViewMovie(movie);
    }

}
