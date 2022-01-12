package MovieCollection.be;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private ArrayList<Category> category;
    private String name;
    private double imdbRating;
    private double privateRating;
    private String fileLink;
    private int id;
    private Date lastView;
    private String categoryString;

    public Movie(String name, double imdbRating, double privateRating, String fileLink, ArrayList<Category> category) {
        this.category = category;
        this.name = name;
        this.imdbRating = imdbRating;
        this.privateRating = privateRating;
        this.fileLink = fileLink;
        this.categoryString = getCategoryString();
    }

    public Movie(int id, String name, double imdbRating, double privateRating, String fileLink, ArrayList<Category> category) {
        this.category = category;
        this.name = name;
        this.imdbRating = imdbRating;
        this.privateRating = privateRating;
        this.fileLink = fileLink;
        this.id = id;
        this.categoryString = getCategoryString();
    }

    public Date getLastview() {
        return lastView;
    }

    public void setLastview(Date lastview) {
        this.lastView = lastview;
    }

    public void setLastViewToCurrentDate()
    {
        Date date = new Date();
        this.setLastview(date);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getCategoryString()
    {
        StringBuilder sb = new StringBuilder();
        for(Category cat: getCategory())
        {
            sb.append(", " + cat.toString());
        }
        return sb.toString().replaceFirst(", ", "");
    }

    public ArrayList<Category> getCategory() {
        return category;
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
