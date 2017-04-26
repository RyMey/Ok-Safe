package id.jeruk.ok_safe.data.model;

/**
 * Created by RyMey on 4/24/17.
 */

public class Point {
    private long latitude;
    private long longitude;

    public Point(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
