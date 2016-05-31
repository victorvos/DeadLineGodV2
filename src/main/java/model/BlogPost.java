package model;

/**
 * Created by Eigenaar on 13-5-2016.
 */
public class BlogPost {
    private String creationTime = null;
    private String subject = null;
    private String text = null;


    public BlogPost(String sub, String txt) {
        subject = sub;
        text = txt;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }
}
