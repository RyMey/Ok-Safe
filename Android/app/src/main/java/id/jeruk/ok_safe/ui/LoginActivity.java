package id.jeruk.ok_safe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_nomor_telepon) EditText etNomorTelepon;
    @BindView(R.id.bt_masuk) Button btMasuk;
    @BindView(R.id.iv_cancel) ImageView ivCancel;

    @OnClick(R.id.bt_masuk)
    public void masuk() {
        if (etNomorTelepon.getText().length() >= 10) {
            startActivity(new Intent(this, VerificationActivity.class));
        }
    }

    @OnClick(R.id.iv_cancel)
    public void cancelPhoneNumber(){
        etNomorTelepon.setText("");
    }

    @OnTextChanged(R.id.et_nomor_telepon)
    public void setNomorTelepon(CharSequence str) {
        if (str.length() >= 10) {
            btMasuk.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            btMasuk.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryText));
            ivCancel.setVisibility(View.VISIBLE);
            btMasuk.setEnabled(true);
        } else {
            btMasuk.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDivider));
            btMasuk.setTextColor(ContextCompat.getColor(this, R.color.colorSecondaryText));
            ivCancel.setVisibility(View.INVISIBLE);
            btMasuk.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


}
