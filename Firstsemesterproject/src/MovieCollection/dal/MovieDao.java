package MovieCollection.Dal;

import MovieCollection.be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class MovieDao {

    private static final DatabaseConnector db = new DatabaseConnector();


    //method used for returning movies from the database
    public List<Movie> getAllMovie() {
        ObservableList movielist = FXCollections.observableArrayList();

        try (Connection con = db.getConnection()) {
            String sqlstatement = "SELECT * From dbo.Movie";
            Statement statement = con.createStatement();
            if (statement.execute(sqlstatement)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String category = resultSet.getString("Category");
                    String name = resultSet.getString("Moviename");
                    double imdbRating = resultSet.getDouble("imdbRating");
                    double privatRating = resultSet.getDouble("Personalrating");
                    String fileLink = resultSet.getString("fileLink");
                    int id = resultSet.getInt("ID");

                    Movie movie = new Movie(name, imdbRating, privatRating, fileLink, id, category);
                    movielist.add(movie);
                }
            }


        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        return movielist;
    }

    /*public Movie addMovie(String name, double imdbRating, double privateRating, String fileLink, String... category) {
        String sqlStatement = "INSERT INTO dbo.Movie(Moviename,imdbRating,Personalrating,fileLink,Category)VALUES(?,?,?,?,?)";
        try (Connection con = db.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, imdbRating);
            preparedStatement.setDouble(3, privateRating);
            preparedStatement.setString(4, fileLink);
            preparedStatement.setString(5, String.valueOf(category));

            preparedStatement.addBatch();
            preparedStatement.executeUpdate();

        } catch (SQLException Ex) {
            System.out.println(Ex);
            return null;

        }
        Movie movie = new Movie(name, imdbRating, privateRating, fileLink, 1, category);
        return movie;

    }*/

    public Movie addmovie(Movie movie) {
        String name = movie.getName();
        double imdbrating = movie.getImdbRating();
        double privaterating = movie.getPrivateRating();
        String filelink = movie.getFileLink();
        String[] Category = movie.getCategory();
        int id = movie.getId();

        String query = "INSERT INTO dbo.Movie(Moviename,imdbRating,Personalrating,fileLink,Category)VALUES(?,?,?,?,?)";

        try (Connection con = db.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, imdbrating);
            preparedStatement.setDouble(3, privaterating);
            preparedStatement.setString(4, filelink);
            preparedStatement.setString(5, String.valueOf(Category));

            preparedStatement.addBatch();
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        Movie movietoadd = new Movie(name, imdbrating, privaterating, filelink, id, Category);
        return movietoadd;
    }

    public Movie editMovie(Movie selectedMovie, String name, double imdbRating, double privateRating, String fileLink, String... category) {

        try (Connection con = db.getConnection()) {
            String sqlStatement = "UPDATE dbo.movie set Moviename = ?, set Category = ?, set imdbRating = ?, set Personalrating = ?, set fileLink = ? WHERE = ID? ";
            PreparedStatement preparedStatement = con.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, imdbRating);
            preparedStatement.setDouble(3, privateRating);
            preparedStatement.setString(4, fileLink);
            preparedStatement.setString(5, selectedMovie.getCategoryString());
            preparedStatement.setInt(6, selectedMovie.getId());

            preparedStatement.executeUpdate();
            return new Movie(name, imdbRating, privateRating, fileLink, selectedMovie.getId(), selectedMovie.getCategoryString());

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }

    }

    public void deleteMovie(Movie selectedMovie) {
        try (Connection con = db.getConnection()) {
            String sqlStatement = "DELETE FROM dbo.Movie WHERE ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, selectedMovie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

}
