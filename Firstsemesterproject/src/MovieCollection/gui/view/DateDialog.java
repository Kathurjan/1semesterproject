package MovieCollection.gui.view;


import MovieCollection.gui.controller.DateDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class DateDialog extends Dialog<ButtonType> {

    private DateDialogController controller;

    public DateDialog()
    {
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DateDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Delete Old Movies");
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

