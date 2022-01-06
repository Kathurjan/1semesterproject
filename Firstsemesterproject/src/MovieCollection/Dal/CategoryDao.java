package MovieCollection.Dal;

import MovieCollection.be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryDao {

    private static final DatabaseConnector db = new DatabaseConnector();

    public List<Category> getAllCategory() {
        ObservableList categorylist = FXCollections.observableArrayList();
        try (Connection con = db.getConnection()) {
            String sqlStatement = "SELECT * FROM dbo.Category";
            Statement statement = con.createStatement();

            if (statement.execute(sqlStatement)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String CategoryName = resultSet.getString("CategoryName");
                    int ID = resultSet.getInt("ID");

                    Category category = new Category(CategoryName, ID);
                    categorylist.add(category);
                }
            }


        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        return categorylist;
    }

}
