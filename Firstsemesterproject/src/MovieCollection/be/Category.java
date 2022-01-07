package MovieCollection.be;

public class Category {


    private String categoryName;
    private int id;

    public Category(String categoryName){
        this.categoryName = categoryName;
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

    public void setId(int id){this.id = id;}

    @Override
    public String toString(){
        return categoryName;
    }

}
