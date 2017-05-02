package id.jeruk.ok_safe.presenter;

/**
 * Created by RyMey on 4/28/17.
 */

public class CommentPresenter extends BasePresenter<CommentPresenter.View> {

    public CommentPresenter(View view) {
        super(view);
    }

    public void sendComment(String str){
        
    }


    public interface View extends BasePresenter.View {
        void onSuccessSend();
        void onFailedSend();
    }
}
