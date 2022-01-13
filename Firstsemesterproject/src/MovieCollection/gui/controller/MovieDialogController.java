package MovieCollection.gui.controller;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Category;
import MovieCollection.gui.model.CategoriesModel;
import MovieCollection.gui.view.ErrorAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieDialogController implements Initializable{
    public TextField txtFieldTitle;
    public TextField txtFieldPersonalRating;
    public TextField txtFieldIMDB;
    public TextField txtFieldFile;

    public ArrayList<Category> categoryList;
    public Button btnAddCategoryDialog;
    public Button btnRemoveCategoryDialog;
    public ChoiceBox<Category> choiceBoxCategory;
    public ListView<Category> lstViewCategories;
    public ObservableList<Category> obsCatList;

    private CategoriesModel cbcModel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoryList = new ArrayList<>();
        this.obsCatList = FXCollections.observableArrayList();
        try {
            this.cbcModel = new CategoriesModel();
            choiceBoxCategory.getItems().addAll(cbcModel.getAllCategories());
            lstViewCategories.setItems(obsCatList);

        } catch (DataException e) {
            createAlertDialog(e);
            initialize(location, resources);
        }

    }

    private void createAlertDialog(Exception e) {
        ErrorAlert dialog = new ErrorAlert(Alert.AlertType.CONFIRMATION, e.getMessage(), ButtonType.OK);
        dialog.showAndWait();
    }

    public String getTitle() {
        return this.txtFieldTitle.getText();
    }

    public Category getCategory()
    {
        return this.choiceBoxCategory.getValue();
    }

    public double getPersonalRating(){return Double.parseDouble(this.txtFieldPersonalRating.getText());}

    public double getIMDB(){return Double.parseDouble(this.txtFieldIMDB.getText());}

    public String getFilePath(){return this.txtFieldFile.getText();}

    public void setTitle(String title){this.txtFieldTitle.setText(title);}

    public void setPersonalRating(double rating){this.txtFieldPersonalRating.setText(String.valueOf(rating));}

    public void setIMDB(double rating){this.txtFieldIMDB.setText(String.valueOf(rating));}

    public void setFilePath(String path){this.txtFieldFile.setText(path);}

    public void setCategories(ArrayList<Category> categories)
    {
        categoryList.addAll(categories);
    }

    public void setObsCatList(ArrayList<Category> categories) {obsCatList.addAll(categories);}

    public ArrayList<Category> getCategoryList(){return this.categoryList;}

    public void handleChoose(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file source");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4 File", "*.mp4"),
                new FileChooser.ExtensionFilter("Mpeg4 File", "*.mpeg4")
        );
        Node source =(Node) actionEvent.getSource();
        File file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if(file !=null){
            String filePath =file.getPath();
            txtFieldFile.setText(filePath);
        }
    }

    public void handleAddCategoryClick(ActionEvent actionEvent) {
        boolean shouldBeAdded = true;
        for(Category cat: categoryList)
        {
            if(cat.getCategoryName().toLowerCase().strip().equals(getCategory().getCategoryName().toLowerCase().strip()))
            {
                shouldBeAdded = false;
            }
        }
        if (shouldBeAdded)
        {
            categoryList.add(getCategory());
            obsCatList.add(getCategory());
        }
    }


    public void handleRemoveCategoryClick(ActionEvent actionEvent) {
        categoryList.removeIf(category -> category.getId() == getCategory().getId());
        obsCatList.removeIf(category -> category.getId() == getCategory().getId());
    }

}
