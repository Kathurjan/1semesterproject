package MovieCollection.Dal;

import MovieCollection.be.Category;
import MovieCollection.be.Movie;

import java.util.ArrayList;

public interface InterfaceMovieDao {
    Movie addMovie(Movie movie);

    void addCatMovieRelation(Movie movie);

    void deleteMovie(Movie movie);

    void deleteCatMovieRelation(int ID);

    void editMovie(Movie movie);

    Category findCategory(int ID);

    ArrayList<Movie> getAllMovies();

    ArrayList<Category> getCategoryRelations(Movie movie);
}
