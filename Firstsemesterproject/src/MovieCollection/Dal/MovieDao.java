package MovieCollection.Dal;

import MovieCollection.be.Movie;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class MovieDao {
    private final static DatabaseConnector db = new DatabaseConnector();

    public List<Movie> getAllMovie(){
        List<Movie> movielist = FXCollections.observableArrayList();

        try(Connection conn = db.getConnection()){
            String sqlStatement ="SELECT * FROM dbo.Movie";
            Statement st = conn.createStatement();
            if (st.execute(sqlStatement)){
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    int id = rs.getInt("ID");
                    String name = rs.getString("Moviename");
                    double imdbRating = rs.getDouble("imdbRating");
                    double privateRating = rs.getDouble("Personalrating");
                    String category = rs.getString("Category");
                    String filepath = rs.getString("fileLink");
                    Date lastview = rs.getDate("lastview");

                    Movie movie = new Movie(category,name,imdbRating,privateRating,filepath,id,lastview);
                    movielist.add(movie);


                }
            }

            return movielist;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return null;

        }

    }



}
