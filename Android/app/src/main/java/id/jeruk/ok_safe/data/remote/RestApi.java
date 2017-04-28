package id.jeruk.ok_safe.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.jeruk.ok_safe.data.model.Report;
import rx.Observable;

public class RestApi {
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    private static RestApi instance;

    private RestApi(Context context) {
        sharedPreferences = context.getSharedPreferences("ok-safe-fake-data", Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static RestApi getInstance(Context context) {
        if (instance == null) {
            instance = new RestApi(context);
        }
        return instance;
    }

    public Observable<Report> postReport(String location, String desc, List<File> photos) {
        List<String> photoUrls = new ArrayList<>();
        for (File photo : photos) {
            photoUrls.add(photo.getAbsolutePath());
        }

        List<Report> reports = new ArrayList<>();

        return getReports()
                .flatMap(Observable::from)
                .doOnNext(reports::add)
                .map(Report::getId)
                .reduce(0, Math::max)
                .map(i -> new Report(i + 1, location, desc, photoUrls))
                .doOnNext(report -> {
                    reports.add(report);
                    sharedPreferences.edit()
                            .putString("reports", gson.toJson(reports))
                            .apply();
                });
    }

    public Observable<List<Report>> getReports() {
        String json = sharedPreferences.getString("reports", "");
        List<Report> reports = gson.fromJson(json, new TypeToken<List<Report>>() {}.getType());
        if (reports == null) {
            reports = new ArrayList<>();
        }
        return Observable.just(reports);
    }


    public Observable<Object> logout() {
        sharedPreferences.edit().clear().apply();
        return Observable.just("");
    }
}
