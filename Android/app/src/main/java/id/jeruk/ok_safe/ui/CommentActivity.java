package id.jeruk.ok_safe.ui;

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

public class CommentActivity extends AppCompatActivity {
    @BindView(R.id.button_send) ImageView ivButtonSend;
    @BindView(R.id.et_komentar) EditText etKomentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
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
        }
    }

    @OnClick(R.id.button_send)
    public void sendComment(){

    }
}
