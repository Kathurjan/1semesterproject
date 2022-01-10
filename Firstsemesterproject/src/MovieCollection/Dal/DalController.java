package MovieCollection.Dal;


import MovieCollection.be.Movie;

import java.util.List;

public class DalController implements DalInterface {

    private MovieDao movieDao;

    @Override
    public List<Movie> getAllMovie() {
        return null;
    }

    @Override
    public Movie addMovie(String name, double imdbRating, double privateRating, String fileLink, String... category) {
        return null;
    }

    @Override
    public Movie editMovie(Movie selectedMovie, String name, double imdbRating, double privateRating, String fileLink, String... category) {
        return null;
    }

    @Override
    public void deleteMovie(Movie selectedMovie) {

    }
}
