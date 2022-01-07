package MovieCollection.be;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private ArrayList<Category> category;
    private String categoryString;
    private String name;
    private double imdbRating;
    private double privateRating;
    private String fileLink;
    private int id;
    private Date lastView;

    public Movie(String name, double imdbRating, double privateRating, String fileLink, ArrayList<Category> category) {
        this.category = category;
        this.name = name;
        this.imdbRating = imdbRating;
        this.privateRating = privateRating;
        this.fileLink = fileLink;
        this.id = id;
        this.categoryString = getCategoryString();
        this.lastView = lastView;

    }
    public Date getLastview() {
        return lastView;
    }

    public void setLastview(Date lastview) {
        this.lastView = lastview;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){this.id = id;}

    public ArrayList<Category> getCategory() {
        return category;
    }

    public String getCategoryString(){
        StringBuilder sb = new StringBuilder();
        for(Category s : category)
        {
            sb.append(", " + s.getCategoryName());
        }
        String string = sb.toString().replaceFirst(", ", "");
        return string;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getPrivateRating() {
        return privateRating;
    }

    public void setPrivateRating(double privateRating) {
        this.privateRating = privateRating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }


}
