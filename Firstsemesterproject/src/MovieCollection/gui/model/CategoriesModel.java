package MovieCollection.gui.model;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.bll.CategoryLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CategoriesModel {

    private CategoryLogic categoryLogic;
    private ObservableList<Category> categories;

    public CategoriesModel() throws DataException {
        this.categoryLogic = new CategoryLogic();
        this.categories = FXCollections.observableArrayList();
        categories.addAll(categoryLogic.getAllCategories());
    }

    public ObservableList<Category> getAllCategories()
    {
        return categories;
    }

    public void addNewCategory(Category category) throws DataException {
        categories.add(categoryLogic.add(category));
        categories.add(category);
    }

    public void deleteCategory(Category category) throws DataException {
        categoryLogic.delete(category);
        categories.remove(category);
    }

}
