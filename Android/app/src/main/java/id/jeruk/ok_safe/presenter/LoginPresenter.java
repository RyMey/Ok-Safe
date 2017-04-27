package id.jeruk.ok_safe.presenter;

import android.content.Context;

import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.User;

/**
 * Created by RyMey on 4/26/17.
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    private final Context context;

    public LoginPresenter(Context context, View view) {
        super(view);
        this.context = context;
    }

    public void login(String phoneNumber) {
        view.showLoading();
        LocalDataManager.getInstance(context).savePhoneNumber(phoneNumber);
        LocalDataManager.getInstance(context).saveStatus(LocalDataManager.Status.WAIT);
        view.onLoginSuccess();
        view.dismissLoading();
    }

    public interface View extends BasePresenter.View {
        void onLoginSuccess();
    }
}
