package id.jeruk.ok_safe.presenter;

import java.util.List;

import id.jeruk.ok_safe.OkSafeApp;
import id.jeruk.ok_safe.data.model.Reward;
import id.jeruk.ok_safe.data.remote.RestApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RewardPresenter extends BasePresenter<RewardPresenter.View> {

    public RewardPresenter(View view) {
        super(view);
    }

    public void loadRewad() {
        view.showLoading();
        RestApi.getInstance(OkSafeApp.getInstance())
                .getRewards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(rewards -> {
                    view.showRewards(rewards);
                    view.dismissLoading();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.dismissLoading();
                });
    }

    public interface View extends BasePresenter.View {
        void showRewards(List<Reward> rewards);
    }
}
