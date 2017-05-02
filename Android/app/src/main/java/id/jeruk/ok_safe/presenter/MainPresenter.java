package id.jeruk.ok_safe.presenter;

import android.content.Context;

import java.util.List;

import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.remote.RestApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RyMey on 4/27/17.
 */

public class MainPresenter extends BasePresenter<MainPresenter.View> {
    private final Context context;

    public MainPresenter(Context context, View view) {
        super(view);
        this.context = context;
    }

    public void loadReports() {
        view.showLoading();
        RestApi.getInstance(context)
                .getReports()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(reports -> {
                    view.showReports(reports);
                    view.dismissLoading();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.dismissLoading();
                });
    }

    public interface View extends BasePresenter.View {
        void showReports(List<Report> reports);
    }
}
