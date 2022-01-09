package MovieCollection.Dal;

import MovieCollection.be.Category;
import MovieCollection.be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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

    public Category addCategory(int ID,String categoryName) {
        String query = "INSERT INTO dbo.Category(ID,CategoryName)VALUES(?,?)";
        try (Connection con = db.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);

           preparedStatement.setInt(1,ID);
           preparedStatement.setString(2,categoryName);

            preparedStatement.addBatch();
            preparedStatement.executeUpdate();

        } catch (SQLException Ex) {
            System.out.println(Ex);
            return null;

        }
       Category category = new Category(categoryName,ID);
        return category;

    }

    public Category addcatgory(Category category) {
        String categoryName = category.getCategoryName();
        int id = category.getId();

        String query = "INSERT INTO dbo.Category(ID,CategoryName)VALUES(?,?)";

        try (Connection con = db.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);

           preparedStatement.setInt(1,id);
           preparedStatement.setString(2,categoryName);

            preparedStatement.addBatch();
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        category = new Category(categoryName,id);
        return category;
    }

    public Category editCategory(Category selectedCategory, String categoryName) {

        try (Connection con = db.getConnection()) {
            String query = "UPDATE dbo.Category set CategoryName = ? WHERE ID = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

           preparedStatement.setInt(1,selectedCategory.getId());
           preparedStatement.setString(2,categoryName);

            preparedStatement.executeUpdate();
            return new Category(categoryName,selectedCategory.getId());

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }

    }

    public void deleteCategory(Category selectedCategory) {
        try (Connection connection = db.getConnection()) {
            String query = "DELETE FROM musicCategory WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, selectedCategory.getId());
            pstm.executeUpdate(); // Executing the statement
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
