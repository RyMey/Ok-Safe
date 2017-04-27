package id.jeruk.ok_safe.presenter;

import android.content.Context;

import java.io.File;

import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.User;

/**
 * Created by RyMey on 4/26/17.
 */

public class ProfilePresenter extends BasePresenter<ProfilePresenter.View>{
    private final Context context;
    public ProfilePresenter(Context context,View view) {
        super(view);
        this.context = context;
    }

    public void saveProfile(String name, String id){
        view.showLoading();
        User user = LocalDataManager.getInstance(context).getUser();
        if(user==null)
            user = new User(name,id,null,LocalDataManager.getInstance(context).getPhoneNumber());
        else{
            user.setName(name);
            user.setId(id);
        }
        LocalDataManager.getInstance(context).saveUser(user);
        LocalDataManager.getInstance(context).saveStatus(LocalDataManager.Status.IN);
        view.onSavedProfile();
        view.dismissLoading();
    }

    public void uploadAvatar(String avatar){
        view.showLoading();
        User user;
        if(LocalDataManager.getInstance(context).getUser()!= null) {
            user = LocalDataManager.getInstance(context).getUser();
        }else{
            user = new User(null,null,null,LocalDataManager.getInstance(context).getPhoneNumber());
        }
        user.setImgUrl("http://res.cloudinary.com/zelory/image/upload/c_scale,h_813/v1464394688/bztiizxw6dwrycw9aahz.jpg");
        LocalDataManager.getInstance(context).saveUser(user);
        view.onAvatarUploaded();
        view.dismissLoading();
    }

    public interface View extends BasePresenter.View {
        void onSavedProfile();
        void onAvatarUploaded();
    }
}
