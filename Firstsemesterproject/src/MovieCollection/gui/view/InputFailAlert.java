package MovieCollection.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class InputFailAlert extends Alert {


    public InputFailAlert(AlertType alertType) {
        super(alertType);
        init(null);
    }

    public InputFailAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        init(contentText);
    }

    private void init(String contentText)
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorAlertView.fxml"));
            DialogPane dp = loader.load();
            this.setTitle("Error Dialog");
            dp.setContentText(contentText);
            dp.setHeaderText("Input Fail");
            super.setDialogPane(dp);
        } catch (IOException e) {
        }
    }

}
