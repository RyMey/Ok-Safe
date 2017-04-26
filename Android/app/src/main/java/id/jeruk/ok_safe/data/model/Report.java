package id.jeruk.ok_safe.data.model;

/**
 * Created by RyMey on 4/24/17.
 */

public class Report {
    private String id;
    private String location;
    private String desc;
    private String photoUrl[];

    public Report(String id,String location, String desc, String[] photoUrl) {
        this.id = id;
        this.location = location;
        this.desc = desc;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String[] photoUrl) {
        this.photoUrl = photoUrl;
    }
}
