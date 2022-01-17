package MovieCollection.Dal;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.be.Movie;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieDao implements InterfaceMovieDao {

    private DatabaseConnector databaseConnector;

    public MovieDao() {
        this.databaseConnector = new DatabaseConnector();
    }

    @Override
    public Movie addMovie(Movie movie) throws DataException {
        String movieName = movie.getName();
        double imdbRating = movie.getImdbRating();
        double personalRating = movie.getPrivateRating();
        String fileLink = movie.getFileLink();

        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "INSERT INTO Movie(Moviename, imdbRating, Personalrating, fileLink) OUTPUT INSERTED.* VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, movieName);
            statement.setDouble(2, imdbRating);
            statement.setDouble(3, personalRating);
            statement.setString(4, fileLink);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            movie.setId(resultSet.getInt("ID"));

            addCatMovieRelation(movie);
            System.out.println(movie.getCategory());
            return movie;

        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public void addCatMovieRelation(Movie movie) throws DataException {
        try (Connection connection = databaseConnector.getConnection()) {
            ArrayList<Category> categories = movie.getCategory();
            int movieID = movie.getId();
            for (Category cat : categories) {
                int categoryID = cat.getId();
                System.out.println(categoryID);
                System.out.println(movieID);

                String sql = "INSERT INTO CatMovie(CategoryId, Movieid) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, categoryID);
                statement.setInt(2, movieID);

                statement.execute();
            }
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public ArrayList<Movie> getAllMovies() throws DataException {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "SELECT * FROM Movie";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String movieName = resultSet.getString("Moviename");
                double imdbRating = resultSet.getDouble("imdbRating");
                double personalRating = resultSet.getDouble("Personalrating");
                String fileLink = resultSet.getString("fileLink");
                Date lastView = resultSet.getDate("lastview");

                ArrayList<Category> categories = new ArrayList<>();

                Movie movie = new Movie(ID, movieName, imdbRating, personalRating, fileLink, categories);
                if(lastView!=null) {
                    movie.setLastview(lastView.toLocalDate());
                }
                movie.setCategory(getCategoryRelations(movie));
                allMovies.add(movie);
            }
            return allMovies;
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }


    @Override
    public ArrayList<Category> getCategoryRelations(Movie movie) throws DataException {
        int movieID = movie.getId();
        ArrayList<Category> categories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "SELECT * FROM CatMovie WHERE Movieid=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, movieID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int catID = rs.getInt("CategoryId");
                categories.add(findCategory(catID));
            }
            return categories;

        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public Category findCategory(int ID) throws DataException {
        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "SELECT * FROM Category WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, ID);

            ResultSet rs = statement.executeQuery();
            rs.next();

            String name = rs.getString("CategoryName");

            return new Category(name, ID);

        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws DataException {
        int ID = movie.getId();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Movie WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, ID);
            statement.execute();
            deleteCatMovieRelation(ID);
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public void deleteCatMovieRelation(int ID) throws DataException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM CatMovie WHERE Movieid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, ID);
            statement.execute();
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public void editMovie(Movie movie) throws DataException {
        int selectedID = movie.getId();
        String movieName = movie.getName();
        double imdbRating = movie.getImdbRating();
        double personalRating = movie.getPrivateRating();
        String fileLink = movie.getFileLink();

        try (Connection connection = databaseConnector.getConnection()) {

            String query = "UPDATE Movie SET Moviename = ?, imdbRating = ?, Personalrating = ?, fileLink = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, movieName);
            statement.setDouble(2, imdbRating);
            statement.setDouble(3, personalRating);
            statement.setString(4, fileLink);
            statement.setInt(5, selectedID);

            statement.execute();
            deleteCatMovieRelation(selectedID);
            addCatMovieRelation(movie);
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }
    public void editLastViewMovie(Movie movie) throws DataException {
        int selectedID = movie.getId();
        LocalDate movieDate = movie.getLastview();

        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "UPDATE Movie SET lastview = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, Date.valueOf(movieDate));

            statement.execute();
            deleteCatMovieRelation(selectedID);
            addCatMovieRelation(movie);
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

}