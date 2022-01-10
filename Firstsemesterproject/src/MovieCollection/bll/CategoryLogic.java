package MovieCollection.bll;

import MovieCollection.Dal.CategoryDao;
import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;

import java.util.ArrayList;

public class CategoryLogic {

    private CategoryDao categoryDao;

    public CategoryLogic()
    {
        this.categoryDao = new CategoryDao();
    }

    public ArrayList<Category> getAllCategories() throws DataException {
        return categoryDao.getAllCategories();
    }

    public Category add(Category category) throws DataException {
        return categoryDao.add(category);
    }

    public void delete(Category category) throws DataException {
        categoryDao.delete(category);
    }
}
