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


    public Story(String title, String link, String blurb, String image) {
        this.title = title;
        this.link = link;
        this.blurb = blurb;
        this.image = image;
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

}

