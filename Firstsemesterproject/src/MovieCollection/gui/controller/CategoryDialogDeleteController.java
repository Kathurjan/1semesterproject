package MovieCollection.gui.controller;

import MovieCollection.be.Category;
import MovieCollection.gui.model.CategoriesModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryDialogDeleteController implements Initializable {
    public ListView<Category> lstViewCategories;
    public Button btnDeleteCategory;

    private CategoriesModel categoriesModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoriesModel = new CategoriesModel();
        lstViewCategories.setItems(categoriesModel.getAllCategories());

    }

    public void handleDeleteCategory(ActionEvent actionEvent) {
        categoriesModel.deleteCategory(lstViewCategories.getSelectionModel().getSelectedItem());
        lstViewCategories.setItems(categoriesModel.getAllCategories());
    }

}
