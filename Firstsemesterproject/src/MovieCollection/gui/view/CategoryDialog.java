package MovieCollection.gui.view;

import MovieCollection.be.Category;
import MovieCollection.gui.controller.CategoryDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class CategoryDialog extends Dialog<Category> {

    private CategoryDialogController controller;

    public CategoryDialog()
    {
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/delete Category");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType ->{
                if(buttonType == ButtonType.APPLY){
                    return new Category(controller.getName());
                }
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
