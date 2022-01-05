package MovieCollection.gui.model;

import MovieCollection.be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryManager {

    ObservableList<Category> categories;

    public CategoryManager(){
        categories = FXCollections.observableArrayList();
        categories.add(new Category("action"));
        categories.add(new Category("drama"));
        categories.add(new Category("comedy"));
    }

    public ObservableList<Category> getCategories(){return categories;}
}
