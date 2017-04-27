package id.jeruk.ok_safe.presenter;

import android.content.Context;

import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.User;

/**
 * Created by RyMey on 4/26/17.
 */

public class VerificationPresenter extends BasePresenter<VerificationPresenter.View> {
    private final Context context;

    public VerificationPresenter(Context context,View view) {
        super(view);
        this.context = context;
    }

    public void checkVerification(String code){
        switch (code) {
            case "1234":
                view.showLoading();
                User user = new User("Rya Meyvriska", "G64164008", "http://res.cloudinary.com/zelory/image/upload/v1464394688/bztiizxw6dwrycw9aahz.jpg", LocalDataManager.getInstance(context).getPhoneNumber());
                LocalDataManager.getInstance(context).saveUser(user);
                LocalDataManager.getInstance(context).saveStatus(LocalDataManager.Status.IN);
                view.onVerified();
                view.dismissLoading();
                break;
            case "4321":
                view.showLoading();
                LocalDataManager.getInstance(context).saveStatus(LocalDataManager.Status.NEW);
                view.onVerified();
                view.dismissLoading();
                break;
            default:
                view.showError("Kode yang Anda masukkan salah");
                break;
        }
    }

    public interface View extends BasePresenter.View {
        void onVerified();
    }
}
