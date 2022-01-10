package MovieCollection.Dal;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.be.Movie;

import java.util.ArrayList;

public interface InterfaceMovieDao {
    Movie addMovie(Movie movie) throws DataException;

    void addCatMovieRelation(Movie movie) throws DataException;

    void deleteMovie(Movie movie) throws DataException;

    void deleteCatMovieRelation(int ID) throws DataException;

    void editMovie(Movie movie) throws DataException;

    Category findCategory(int ID) throws DataException;

    ArrayList<Movie> getAllMovies() throws DataException;

    ArrayList<Category> getCategoryRelations(Movie movie) throws DataException;
}
