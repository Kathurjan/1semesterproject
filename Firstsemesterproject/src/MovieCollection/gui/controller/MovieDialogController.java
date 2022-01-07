package MovieCollection.gui.controller;

import MovieCollection.be.Category;
import MovieCollection.gui.model.CategoriesModel;
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
    public TextField txtFieldPersonalRating;
    public TextField txtFieldIMDB;
    public TextField txtFieldFile;

    public ArrayList<Category> categoryList;
    public Button btnAddCategoryDialog;
    public Button btnRemoveCategoryDialog;
    public ChoiceBox<Category> choiceBoxCategory;

    private CategoriesModel cbcModel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoryList = new ArrayList<>();
        this.cbcModel = new CategoriesModel();

        choiceBoxCategory.getItems().addAll(cbcModel.getAllCategories());
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
            if(cat.getCategoryName().equals(getCategory().getCategoryName()))
            {
                shouldBeAdded = false;
            }
        }
        if (shouldBeAdded)
        {
            categoryList.add(getCategory());
        }
    }


    public void handleRemoveCategoryClick(ActionEvent actionEvent) {
        categoryList.removeIf(category -> category.getCategoryName().equals(getCategory().getCategoryName()));
    }
}
