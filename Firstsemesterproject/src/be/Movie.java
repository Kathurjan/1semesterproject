package be;

import java.util.Date;

public class Movie {
    private String category;
    private String name;
    private double imdbRating;
    private double privateRating;
    private String fileLink;
    private int id;

    public Movie(String category, String name, double imdbRating, double privateRating, String fileLink, int id) {
        this.category = category;
        this.name = name;
        this.imdbRating = imdbRating;
        this.privateRating = privateRating;
        this.fileLink = fileLink;
        this.id = id;


    }
    public int getId(){
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
