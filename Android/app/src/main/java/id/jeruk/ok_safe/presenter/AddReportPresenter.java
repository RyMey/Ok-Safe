package id.jeruk.ok_safe.presenter;

import android.content.Context;

import java.io.File;
import java.util.List;

import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.remote.RestApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddReportPresenter extends BasePresenter<AddReportPresenter.View> {
    private final Context context;

    public AddReportPresenter(Context context, View view) {
        super(view);
        this.context = context;
    }

    public void postReport(String location, String desc, List<File> photos) {
        view.showLoading();
        RestApi.getInstance(context)
                .postReport(location, desc, photos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(report -> {
                    view.onReported(report);
                    view.dismissLoading();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.dismissLoading();
                });
    }

    public interface View extends BasePresenter.View {
        void onReported(Report report);
    }
}
