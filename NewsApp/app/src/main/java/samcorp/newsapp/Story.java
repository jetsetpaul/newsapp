package samcorp.newsapp;

/**
 * Created by pauljoiner on 7/16/16.
 */
public class Story {
    String title;
    String link;
    String blurb;
    int imageId;
    String source;


    public Story(String title, String link, int imageId, String blurb, String source) {
        this.title = title;
        this.link = link;
        this.imageId = imageId;
        this.blurb = blurb;
        this.source = source;
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

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public String getSource() {
        return source;
    }

    public int getImageId() {
        return imageId;
    }
}

