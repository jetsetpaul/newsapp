package samcorp.newsapp;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class NewsCategory {
    private String name;
    private int imageId;

    public NewsCategory(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
