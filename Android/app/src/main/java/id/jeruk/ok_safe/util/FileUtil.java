package id.jeruk.ok_safe.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;


/**
 * Created by RyMey on 4/13/17.
 */

public class FileUtil {

    public static Uri getPhotoFileUri(Context context, String fileName) {
        if (isExternalStorageAvailable()) {

        }
        return null;
    }

    private static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
