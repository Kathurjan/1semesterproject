package MovieCollection.be;

public class Category {


    private String categoryName;
    private int id;

    public Category(String categoryName, int id){
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
