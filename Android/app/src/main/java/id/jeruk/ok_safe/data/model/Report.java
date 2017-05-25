package id.jeruk.ok_safe.data.model;

import java.util.List;

/**
 * Created by RyMey on 4/24/17.
 */

public class Report {
    private int id;
    private String location;
    private String desc;
    private String title;
    private List<String> photoUrls;

    public Report(int id,String location, String desc, List<String> photoUrls) {
        this.id = id;
        this.location = location;
        this.desc = desc;
        this.photoUrls = photoUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return id == report.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", desc='" + desc + '\'' +
                ", photoUrls=" + photoUrls +
                '}';
    }
}
