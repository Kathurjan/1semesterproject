package MovieCollection.Dal;

import MovieCollection.be.Category;

import java.util.ArrayList;

public interface InterfaceCategoryDao {
    ArrayList<Category> getAllCategories();

    Category add(Category category);

    void delete(Category category);

    void deleteCatMovie(int ID);
}
