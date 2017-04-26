package id.jeruk.ok_safe.data.model;

/**
 * Created by RyMey on 4/24/17.
 */

public class User {
    private String name;
    private String id;
    private String imgUrl;
    private String phoneNumber;

    public User(String name, String id, String imgUrl, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.imgUrl = imgUrl;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
