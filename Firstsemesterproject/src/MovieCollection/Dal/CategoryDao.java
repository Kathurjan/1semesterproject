package MovieCollection.Dal;

import MovieCollection.be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDao {

    private DatabaseConnector databaseConnector;

    public CategoryDao()
    {
        this.databaseConnector = new DatabaseConnector();
    }

    public ArrayList<Category> getAllCategories()
    {
        ArrayList<Category> allCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Categories";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);



    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
