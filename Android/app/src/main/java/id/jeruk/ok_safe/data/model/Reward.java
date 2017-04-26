package id.jeruk.ok_safe.data.model;

import java.util.Date;

/**
 * Created by RyMey on 4/24/17.
 */

public class Reward {
    private String title;
    private Date date;
    private String imgUrl;

    public Reward(String title, Date date, String imgUrl) {
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
