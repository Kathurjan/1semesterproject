package MovieCollection.be;
import java.util.Date;

public class Movie {
    private String[] category;
    private String categoryString;
    private String name;
    private double imdbRating;
    private double privateRating;
    private String fileLink;
    private int id;
    private Date lastView;

    public Movie(String name, double imdbRating, double privateRating, String fileLink, int id, String ... category) {
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

    public String[] getCategory() {
        return category;
    }

    public String getCategoryString(){
        StringBuilder sb = new StringBuilder();
        for(String s : category)
        {
            sb.append(", " + s);
        }
        String string = sb.toString().replaceFirst(", ", "");
        return string;
    }

    public void setCategory(String[] category) {
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

