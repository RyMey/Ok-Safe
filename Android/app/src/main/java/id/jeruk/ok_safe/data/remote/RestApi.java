package id.jeruk.ok_safe.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.jeruk.ok_safe.OkSafeApp;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.model.Reward;
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

    public Observable<Comment> sendComment(String message) {
        List<Comment> comments = new ArrayList<>();
        return getComment()
                .flatMap(Observable::from)
                .doOnNext(comments::add)
                .map(Comment::getId)
                .reduce(0, Math::max)
                .map(i -> new Comment(i + 1, Calendar.getInstance().getTime(),message))
                .doOnNext(comment -> {
                    comments.add(comment);
                    sharedPreferences.edit()
                            .putString("comments", gson.toJson(comments))
                            .apply();
                });
    }
    public Observable<List<Comment>> getComment() {
        String json = sharedPreferences.getString("comment", "");
        List<Comment> comments = gson.fromJson(json, new TypeToken<List<Comment>>() {
        }.getType());
        if (comments == null) {
            comments = new ArrayList<>();
        }
        return Observable.just(comments);
    }

    public Observable<Report> postReport(String location, String title, String desc, List<File> photos) {
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
                .map(i -> new Report(i + 1, location, title, desc, photoUrls))
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

    public Observable<List<Reward>> getRewards() {
        List<Reward> rewards = new ArrayList<>();
        User user = LocalDataManager.getInstance(OkSafeApp.getInstance()).getUser();
        for (int i = 1; i <= 15; i++) {
            rewards.add(new Reward("Reward " + i, new Date(), ""));
        }
        return Observable.just(rewards);
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
