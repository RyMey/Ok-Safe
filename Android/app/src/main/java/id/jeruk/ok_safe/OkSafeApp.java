package id.jeruk.ok_safe;

import android.app.Application;

/**
 * Created on : April 28, 2017
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
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
