package MovieCollection.Dal;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import java.sql.*;
import java.util.ArrayList;

public class CategoryDao implements InterfaceCategoryDao {

    private DatabaseConnector databaseConnector;

    public CategoryDao() {
        this.databaseConnector = new DatabaseConnector();
    }

    @Override
    public ArrayList<Category> getAllCategories() throws DataException {
        ArrayList<Category> allCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Category";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("CategoryName");

                Category category = new Category(name, ID);
                allCategories.add(category);
            }
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
        return allCategories;
    }

    @Override
    public Category add(Category category) throws DataException {
        String catName = category.getCategoryName();

        try (Connection connection = databaseConnector.getConnection())
        {
            String sql ="INSERT INTO Category(CategoryName) OUTPUT inserted.* VALUES (?)";
            PreparedStatement statement =connection.prepareStatement(sql);

            statement.setString(1, catName);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            category.setId(resultSet.getInt("ID"));
            return category;

        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }
    @Override
    public void delete(Category category) throws DataException {

        try (Connection connection = databaseConnector.getConnection())
        {
            String sql ="DELETE FROM Category WHERE ID=?";
            PreparedStatement statement =connection.prepareStatement(sql);

            statement.setInt(1, category.getId());
            statement.execute();
            deleteCatMovie(category.getId());
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

    @Override
    public void deleteCatMovie(int ID) throws DataException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM CatMovie WHERE CategoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, ID);
            statement.execute();
        } catch (SQLException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }

}
