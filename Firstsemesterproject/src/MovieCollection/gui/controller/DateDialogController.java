package MovieCollection.gui.controller;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Movie;
import MovieCollection.gui.model.CheckDateModel;
import MovieCollection.gui.view.ErrorAlert;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DateDialogController implements Initializable {
    public TableView<Movie> tblViewOldMovies;
    public Button btnDeleteOldMovies;

    private CheckDateModel checkDateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                this.checkDateModel = new CheckDateModel();
                tblViewOldMovies.setItems(checkDateModel.getMovies());
            } catch (DataException e) {
                createAlertDialog(e);
                initialize(location, resources);
            }

    }

    public void handleDeleteOldMovies(ActionEvent actionEvent) {
    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }
}
