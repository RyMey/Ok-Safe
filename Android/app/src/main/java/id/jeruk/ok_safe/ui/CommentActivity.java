package id.jeruk.ok_safe.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.presenter.CommentPresenter;

public class CommentActivity extends AppCompatActivity implements CommentPresenter.View {
    private static final String EXTRA_REPORT = "extra_report";

    @BindView(R.id.button_send) ImageView ivButtonSend;
    @BindView(R.id.et_komentar) EditText etKomentar;
    private CommentPresenter commentPresenter;

    public static Intent generateIntent(Context context, Report report){
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(EXTRA_REPORT, report);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        commentPresenter = new CommentPresenter(this, this);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @OnTextChanged(R.id.et_komentar)
    public void setComment(CharSequence str) {
        if (str.length() > 0) {
            ivButtonSend.setColorFilter(getResources().getColor(R.color.colorPrimary));
        } else {
            ivButtonSend.setColorFilter(getResources().getColor(R.color.colorDivider));
            commentPresenter.sendComment(str.toString());
        }
    }

    @OnClick(R.id.button_send)
    public void sendComment(){

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onSuccessSend() {

    }

    @Override
    public void onFailedSend() {

    }
}
