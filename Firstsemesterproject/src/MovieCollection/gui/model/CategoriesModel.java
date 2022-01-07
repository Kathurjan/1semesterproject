package MovieCollection.gui.model;

import MovieCollection.be.Category;
import MovieCollection.bll.CategoryLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CategoriesModel {

    private CategoryLogic categoryLogic;
    private ObservableList<Category> categories;

    public CategoriesModel()
    {
        this.categoryLogic = new CategoryLogic();
        this.categories = FXCollections.observableArrayList();

        categories.addAll(categoryLogic.getAllCategories());
    }

    public ObservableList<Category> getAllCategories()
    {
        return categories;
    }

    public void addNewCategory(Category category)
    {
        categories.add(categoryLogic.add(category));
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        categoryLogic.delete(category);
        categories.remove(category);
    }

}
