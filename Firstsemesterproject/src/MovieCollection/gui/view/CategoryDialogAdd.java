package MovieCollection.gui.view;

import MovieCollection.be.Category;
import MovieCollection.gui.controller.CategoryDialogAddController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class CategoryDialogAdd extends Dialog<Category> {

    private CategoryDialogAddController controller;

    public CategoryDialogAdd()
    {
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryDialogAddView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add Category");
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
