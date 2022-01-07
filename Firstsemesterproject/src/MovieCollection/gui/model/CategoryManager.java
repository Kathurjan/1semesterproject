package MovieCollection.gui.model;

import MovieCollection.be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;

public class CategoryManager {

    //ObservableList<Category> categories;
    HashMap<String, Category> allCategories;


    public CategoryManager(){
        this.allCategories = new HashMap<String, Category>();

        Category action = new Category("Action");
        Category drama = new Category("Drama");
        Category thriller = new Category("Thriller");
        Category scifi = new Category("Sci-Fi");

        addCategoryToManager(action);
        addCategoryToManager(drama);
        addCategoryToManager(thriller);
        addCategoryToManager(scifi);

    }

    public void addCategoryToManager(Category category)
    {
        if (!allCategories.containsKey(category.getCategoryName().toLowerCase())) {
            allCategories.put(category.getCategoryName().toLowerCase(), category);
        }
        System.out.println(allCategories);
    }

    public void removeCategoryFromManager(Category category)
    {
        allCategories.remove(category.getCategoryName().toLowerCase());
    }

    public Category returnQueriedCategory(String string)
    {
        return allCategories.get(string);
    }

    public ObservableList<String> getObsListCategories() {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        obsList.addAll(allCategories.keySet());
        return obsList;
    }

    public ArrayList<Category> getArrListCategories()
    {
        ArrayList<Category> arrList = new ArrayList<>();
        ArrayList<String> categoryStrings = new ArrayList<>();

        categoryStrings.addAll(allCategories.keySet());

        for(int i=0; i< allCategories.size(); i++)
        {
            String key = categoryStrings.get(i);
            arrList.add(allCategories.get(key));
        }
        return arrList;
    }

}
