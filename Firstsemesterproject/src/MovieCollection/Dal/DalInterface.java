package MovieCollection.Dal;

import MovieCollection.be.Movie;
import MovieCollection.be.Category;

import java.util.List;

public interface DalInterface {
    List<Movie> getAllMovie();
    Movie addMovie(String name, double imdbRating, double privateRating, String fileLink, String... category);
    Movie editMovie(Movie selectedMovie, String name, double imdbRating, double privateRating, String fileLink, String... category);
    void deleteMovie(Movie selectedMovie);



}
