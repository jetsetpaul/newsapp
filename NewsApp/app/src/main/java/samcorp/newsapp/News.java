package samcorp.newsapp;

/**
 * Created by samwyz on 7/18/16.
 */
public class News {

    String mHeadline;
    String mCategory;
    String mWebLink;

    public News(String mHeadline, String mCategory, String mWebLink) {
        this.mHeadline = mHeadline;
        this.mCategory = mCategory;
        this.mWebLink = mWebLink;
    }

    public String getmHeadline() {
        return mHeadline;
    }

    public void setmHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmWebLink() {
        return mWebLink;
    }

    public void setmWebLink(String mWebLink) {
        this.mWebLink = mWebLink;
    }

}
