package id.jeruk.ok_safe.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public class RestApi {
    private static RestApi instance;
    
    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private final OkHttpClient httpClient;
    private final Api api;

    private RestApi(Context context) {
        sharedPreferences = context.getSharedPreferences("ok-safe-fake-data", Context.MODE_PRIVATE);
        gson = new Gson();

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        api = new Retrofit.Builder()
                .baseUrl("http://alamatServer")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    public static RestApi getInstance(Context context) {
        if (instance == null) {
            instance = new RestApi(context);
        }
        return instance;
    }

    public Observable<User> login(String phoneNumber) {
        return api.login(phoneNumber)
                //TODO map json response to User pojo
                .map(jsonElement -> null);
    }

    public Observable<User> register(String phonNumber) {
        return api.register(phonNumber)
                //TODO map json response to User pojo
                .map(jsonElement -> null);
    }

    public Observable<List<Report>> reportHistories(String userId) {
        return api.reportHistories(userId)
                //TODO map json response to List of Report
                .map(jsonElement -> null);
    }

    public Observable<Report> createReport(String userId, String title, String description, String image,
                                           long latitude, long longitude) {
        return api.createReport(userId, title, description, image, latitude, longitude)
                //TODO map json response to Report pojo
                .map(jsonElement -> null);
    }

    public Observable<Comment> createComment(String reportId, String message) {
        return api.createComment(reportId, message)
                //TODO map json response to Comment pojo
                .map(jsonElement -> null);
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
        List<Report> reports = gson.fromJson(json, new TypeToken<List<Report>>() {
        }.getType());
        if (reports == null) {
            reports = new ArrayList<>();
        }
        return Observable.just(reports);
    }


    public Observable<Object> logout() {
        sharedPreferences.edit().clear().apply();
        return Observable.just("");
    }

    private interface Api {

        @FormUrlEncoded
        @POST("/api-oksafe/login")
        Observable<JsonElement> login(@Field("phone_number") String phoneNumber);

        @FormUrlEncoded
        @POST("/api-oksafe/register")
        Observable<JsonElement> register(@Field("phone_number") String phoneNumber);

        @FormUrlEncoded
        @GET("/api-oksafe/posts/")
        Observable<JsonElement> reportHistories(@Field("id_user") String userId);

        @FormUrlEncoded
        @POST("/api-oksafe/posts/create")
        Observable<JsonElement> createReport(@Field("id_user") String userId,
                                             @Field("judul") String title,
                                             @Field("isi") String description,
                                             @Field("gambar") String img,
                                             @Field("latitude") long latitude,
                                             @Field("longitude") long longitude);

        @FormUrlEncoded
        @POST("/api-oksafe/comment/create")
        Observable<JsonElement> createComment(@Field("id_post") String reportId,
                                              @Field("komen") String message);
    }
}
