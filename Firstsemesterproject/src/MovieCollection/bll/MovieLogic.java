package MovieCollection.bll;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.Dal.MovieDao;
import MovieCollection.be.Movie;

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

    public boolean checkDates(ArrayList<Movie> movies)
    {
        boolean alertUser = false;
        for(Movie movie: movies)
        {
            if(movie.getLastview().before(twoYearPriorDate(new Date()))){
                alertUser = true;
            }
        }
        return alertUser;
    }

    public Date twoYearPriorDate(Date date)
    {
        Date currentDate = new Date();
        Date newDate = new Date(currentDate.getYear()+1900-2, currentDate.getMonth(), currentDate.getDate());
        return newDate;
    }

}
