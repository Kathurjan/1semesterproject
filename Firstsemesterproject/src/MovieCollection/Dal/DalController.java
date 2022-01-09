package MovieCollection.Dal;


import MovieCollection.be.Movie;

import java.util.List;

public class DalController implements DalInterface {

    private MovieDao movieDao;

    @Override
    public List<Movie> getAllMovie() {
        return movieDao.getAllMovie();
    }

    @Override
    public Movie addMovie(String name, double imdbRating, double privateRating, String fileLink, String... category) {
        return movieDao.addMovie(name,imdbRating,privateRating,fileLink,category);
    }
    //might be an issue caused from not using parameters to create the object not sure though.
    @Override
    public Movie addmovie(Movie movie) {
        return movieDao.addmovie(movie);
    }

    @Override
    public Movie editMovie(Movie selectedMovie, String name, double imdbRating, double privateRating, String fileLink, String... category) {
       return movieDao.editMovie(selectedMovie,name,imdbRating,privateRating,fileLink,category);
    }

    @Override
    public void deleteMovie(Movie selectedMovie) {
        movieDao.deleteMovie(selectedMovie);
    }
}
