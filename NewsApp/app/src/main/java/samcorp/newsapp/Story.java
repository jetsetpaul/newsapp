package samcorp.newsapp;

import java.io.Serializable;

/**
 * Created by pauljoiner on 7/16/16.
 */
public class Story implements Serializable{
    String title;
    String link;
    String blurb;
    String image;
    String category;


    public Story(String title, String link, String category, String image, String blurb) {
        this.title = title;
        this.link = link;
        this.category = category;
        this.image = image;
        this.blurb = blurb;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getBlurb() {
        return blurb;
    }

    public String getImage() {
        return image;
    }

    public String getCategory(){ return category; }

    public void setCategory(String category){
        this.category = category;
    }

}

