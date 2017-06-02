package id.jeruk.ok_safe.presenter;

import android.content.Context;

import id.jeruk.ok_safe.data.remote.RestApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RyMey on 4/28/17.
 */

public class CommentPresenter extends BasePresenter<CommentPresenter.View> {
    private final Context context;
    public CommentPresenter(Context context,View view) {
        super(view);
        this.context = context;
    }

    public void sendComment(String str){
        view.showLoading();
        RestApi.getInstance(context)
                .sendComment(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(comment -> {
                    view.onSuccessSend();
                    view.dismissLoading();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.dismissLoading();
                });
        
    }

    public interface View extends BasePresenter.View {
        void onSuccessSend();
        void onFailedSend();
    }
}
