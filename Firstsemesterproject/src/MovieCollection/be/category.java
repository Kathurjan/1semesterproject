package MovieCollection.be;

public class category {


    private String categoryName;
    private int id;

    public category(String categoryName, int id){
        this.categoryName = categoryName;
        this.id = id;

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public int getId() {
        return id;
    }

}
