package MovieCollection.Dal;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;

import java.util.ArrayList;

public interface InterfaceCategoryDao {
    ArrayList<Category> getAllCategories() throws DataException;

    Category add(Category category) throws DataException;

    void delete(Category category) throws DataException;

    void deleteCatMovie(int ID) throws DataException;
}
