package id.jeruk.ok_safe.presenter;

import android.content.Context;

import java.util.List;

import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.remote.RestApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RyMey on 4/28/17.
 */

public class CommentPresenter extends BasePresenter<CommentPresenter.View> {
    private final Context context;

    public CommentPresenter(Context context, View view) {
        super(view);
        this.context = context;
    }

    public void sendComment(int reportId, String message) {
        RestApi.getInstance(context)
                .sendComment(reportId, message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(comment -> view.onCommentSend(comment), throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                });

    }

    public void loadComments(int reportId) {
        view.showLoading();
        RestApi.getInstance(context)
                .getComments(reportId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(comments -> {
                    view.showComments(comments);
                    view.dismissLoading();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.dismissLoading();
                });
    }

    public interface View extends BasePresenter.View {
        void showComments(List<Comment> comments);

        void onCommentSend(Comment comment);
    }
}
