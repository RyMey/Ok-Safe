package id.jeruk.ok_safe.data.model;

import java.util.Date;

/**
 * Created by RyMey on 4/24/17.
 */

public class Comment {
    private String id;
    private Date date;
    private String desc;

    public Comment(String id, Date date, String desc) {
        this.id = id;
        this.date = date;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
