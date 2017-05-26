package id.jeruk.ok_safe;

import android.app.Application;

public class OkSafeApp extends Application {
    private static OkSafeApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static OkSafeApp getInstance() {
        return instance;
    }
}
