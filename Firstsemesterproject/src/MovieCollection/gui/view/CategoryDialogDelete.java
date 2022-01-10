package MovieCollection.gui.view;

import MovieCollection.be.Category;
import MovieCollection.gui.controller.CategoryDialogAddController;
import MovieCollection.gui.controller.CategoryDialogDeleteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class CategoryDialogDelete extends Dialog<ButtonType> {


    private CategoryDialogDeleteController controller;

    public CategoryDialogDelete()
    {
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryDialogDeleteView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Delete Category");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType ->{
                if(buttonType == ButtonType.CLOSE){
                    this.close();
                    return ButtonType.CLOSE;
                }
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
