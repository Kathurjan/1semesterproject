package MovieCollection.bll;

import MovieCollection.Dal.CategoryDao;
import MovieCollection.be.Category;

import java.util.ArrayList;

public class CategoryLogic {

    private CategoryDao categoryDao;

    public CategoryLogic()
    {
        this.categoryDao = new CategoryDao();
    }

    public ArrayList<Category> getAllCategories()
    {
        return categoryDao.getAllCategories();
    }

    public Category add(Category category) {
        return categoryDao.add(category);
    }

    public void delete(Category category) {
        categoryDao.delete(category);
    }
}
