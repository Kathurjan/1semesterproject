package MovieCollection.gui.controller;

import MovieCollection.be.Category;
import MovieCollection.gui.model.CategoryManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieDialogController implements Initializable{
    public TextField txtFieldTitle;
    public TextField txtFieldCategory;
    public TextField txtFieldPersonalRating;
    public TextField txtFieldIMDB;
    public TextField txtFieldFile;
    public Button btnAddCategory;

    public ArrayList<Category> categoryList;
    private CategoryManager categoryManager;
    public ChoiceBox<Category> choiceBoxCategory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoryList = new ArrayList<Category>();
        CategoryManager categoryManager = new CategoryManager();
        choiceBoxCategory.getItems().addAll(categoryManager.getCategories());

    }

    public String getTitle() {
        return this.txtFieldTitle.getText();
    }

    public Category getCategory(){return this.choiceBoxCategory.getValue();}

    public double getPersonalRating(){return Double.parseDouble(this.txtFieldPersonalRating.getText());}

    public double getIMDB(){return Double.parseDouble(this.txtFieldIMDB.getText());}

    public String getFilePath(){return this.txtFieldFile.getText();}

    public void setTitle(String title){this.txtFieldTitle.setText(title);}

    public void setCategory(Category category){}

    public void setPersonalRating(int rating){this.txtFieldPersonalRating.setText(String.valueOf(rating));}

    public void setIMDB(int rating){this.txtFieldIMDB.setText(String.valueOf(rating));}

    public void setFilePath(String path){this.txtFieldFile.setText(path);}

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
        categoryList.add(getCategory());
    }


}
