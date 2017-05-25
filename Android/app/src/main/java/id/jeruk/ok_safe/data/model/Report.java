package id.jeruk.ok_safe.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by RyMey on 4/24/17.
 */

public class Report implements Parcelable {
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

    protected Report(Parcel in) {
        id = in.readInt();
        location = in.readString();
        desc = in.readString();
        title = in.readString();
        photoUrls = in.createStringArrayList();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

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

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(location);
        dest.writeString(desc);
        dest.writeString(title);
        dest.writeStringList(photoUrls);
    }
}
