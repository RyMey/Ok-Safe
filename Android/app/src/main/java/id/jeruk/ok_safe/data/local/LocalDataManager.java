package id.jeruk.ok_safe.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import id.jeruk.ok_safe.data.model.User;

/**
 * Created by RyMey on 4/24/17.
 */

public class LocalDataManager {

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    private static LocalDataManager instance;

    private LocalDataManager(Context context) {
        sharedPreferences = context.getSharedPreferences("ok-safe", Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static LocalDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDataManager(context);
        }
        return instance;
    }

    public void savePhoneNumber(String phoneNumber){
        sharedPreferences.edit().putString("phoneNumber",phoneNumber).apply();
    }

    public String getPhoneNumber(){
        return sharedPreferences.getString("phoneNumber",null);
    }

    public void saveUser(User user) {
        sharedPreferences.edit().putString("user", gson.toJson(user)).apply();
    }

    public User getUser() {
        return gson.fromJson(sharedPreferences.getString("user", null), User.class);
    }

    public void saveStatus(Status status) {
        sharedPreferences.edit().putString("status", status.name()).apply();
    }

    public Status getStatus() {
        return Status.valueOf(sharedPreferences.getString("status", Status.OUT.name()));
    }

    public void clearData(){
        sharedPreferences.edit().clear().apply();
    }

    public enum Status {
        OUT,
        WAIT,
        NEW,
        IN
    }
}
