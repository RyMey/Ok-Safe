package id.jeruk.ok_safe.data.model;

import java.util.Date;

/**
 * Created by RyMey on 4/24/17.
 */

public class Comment {
    private int id;
    private Date date;
    private String desc;

    public Comment(int id, Date date, String desc) {
        this.id = id;
        this.date = date;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
