package MovieCollection.gui.controller;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.gui.model.CategoriesModel;
import MovieCollection.gui.view.ErrorAlert;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryDialogDeleteController implements Initializable {
    public ListView<Category> lstViewCategories;
    public Button btnDeleteCategory;

    private CategoriesModel categoriesModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.categoriesModel = new CategoriesModel();
            lstViewCategories.setItems(categoriesModel.getAllCategories());
        } catch (DataException e) {
            createAlertDialog(e);
            initialize(location, resources);
        }

    }

    public void handleDeleteCategory(ActionEvent actionEvent) {
        if (lstViewCategories.getSelectionModel().getSelectedItem()!= null) {
            try {
                categoriesModel.deleteCategory(lstViewCategories.getSelectionModel().getSelectedItem());
                lstViewCategories.setItems(categoriesModel.getAllCategories());
            } catch (DataException e) {
                createAlertDialog(e);
                handleDeleteCategory(actionEvent);
            }
        }
    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }

}
