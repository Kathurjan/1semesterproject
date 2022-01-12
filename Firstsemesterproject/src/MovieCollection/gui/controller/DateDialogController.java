package MovieCollection.gui.controller;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Movie;
import MovieCollection.gui.model.CheckDateModel;
import MovieCollection.gui.view.ErrorAlert;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DateDialogController implements Initializable {
    public TableView<Movie> tblViewOldMovies;
    public Button btnDeleteOldMovies;
    public TableColumn<Movie, String> tblColumnTitle;
    public TableColumn<Movie, String> tblColumnCategory;
    public TableColumn<Movie, String> tblColumnPersonalRating;
    public TableColumn<Movie, String> tblColumnIMDBRating;

    private CheckDateModel checkDateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                this.checkDateModel = new CheckDateModel();
                tblViewOldMovies.setItems(checkDateModel.getMovies());
                initTables();
            } catch (DataException e) {
                createAlertDialog(e);
                initialize(location, resources);
            }

    }

    private void initTables() {
        this.tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("categoryString"));
        this.tblColumnPersonalRating.setCellValueFactory(new PropertyValueFactory<>("privateRating"));
        this.tblColumnIMDBRating.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
    }

    public void handleDeleteOldMovies(ActionEvent actionEvent) {
        try {
            checkDateModel.deleteMovie(tblViewOldMovies.getSelectionModel().getSelectedItem());
        } catch (DataException e) {
            createAlertDialog(e);
            handleDeleteOldMovies(actionEvent);
        }
    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }
}
