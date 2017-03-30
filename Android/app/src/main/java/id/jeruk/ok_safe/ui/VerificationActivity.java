package id.jeruk.ok_safe.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.util.Util;

public class VerificationActivity extends AppCompatActivity {
    @BindView(R.id.tv_desc_nomor) TextView tvDescNomor;
    @BindView(R.id.iv_cancel) ImageView ivCancel;
    @BindView(R.id.et_kode_verifikasi) EditText etKodeVerifikasi;
    @BindView(R.id.bt_kirim_ulang) Button btKirimUlang;
    @BindView(R.id.tv_waktu_tunggu) TextView tvWaktuTunggu;

    private int seconds = 59;
    private boolean stopTimer = false;

    @OnClick(R.id.iv_cancel)
    public void cancelPhoneNumber() {
        etKodeVerifikasi.setText("");

        if (stopTimer) {
            btKirimUlang.setBackgroundColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorPrimary));
            btKirimUlang.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorWhite));
            btKirimUlang.setText("Kirim Ulang Kode");
            btKirimUlang.setEnabled(true);
            tvWaktuTunggu.setVisibility(View.INVISIBLE);
        } else {
            btKirimUlang.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDivider));
            btKirimUlang.setTextColor(ContextCompat.getColor(this, R.color.colorSecondaryText));
            btKirimUlang.setText("Kirim Ulang Kode");
            ivCancel.setVisibility(View.INVISIBLE);
            btKirimUlang.setEnabled(false);

            tvWaktuTunggu.setVisibility(View.VISIBLE);
            if (seconds < 10)
                tvWaktuTunggu.setText("00:0" + seconds);
            else
                tvWaktuTunggu.setText("00:" + seconds);
        }

        ivCancel.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.tv_ubah_nomor)
    public void ubahNomor() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnTextChanged(R.id.et_kode_verifikasi)
    public void setNomorTelepon(CharSequence str) {
        btKirimUlang.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        btKirimUlang.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryText));
        btKirimUlang.setText("Registrasi");
        tvWaktuTunggu.setVisibility(View.INVISIBLE);
        btKirimUlang.setEnabled(true);

        if(etKodeVerifikasi.getText().equals(""))
            ivCancel.setVisibility(View.INVISIBLE);
        else
            ivCancel.setVisibility(View.VISIBLE);
    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                seconds--;
                if (seconds < 10)
                    tvWaktuTunggu.setText("00:0" + seconds);
                else
                    tvWaktuTunggu.setText("00:" + seconds);
                if (seconds < 0) {
                    btKirimUlang.setBackgroundColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorPrimary));
                    btKirimUlang.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorWhite));
                    btKirimUlang.setText("Kirim Ulang Kode");
                    btKirimUlang.setEnabled(true);
                    stopTimer = true;
                    tvWaktuTunggu.setVisibility(View.INVISIBLE);
                }
                if (!stopTimer) {
                    handler.postDelayed(this, 1000);
                }
            }

        });
    }

    @OnClick(R.id.bt_kirim_ulang)
    public void registrasi() {
        if (btKirimUlang.getText().equals("Registrasi")) {
            Util.hideKeyboard(this);
            startActivity(new Intent(this, ProfileActivity.class));
        } else {
            btKirimUlang.setBackgroundColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorDivider));
            btKirimUlang.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorSecondaryText));
            btKirimUlang.setEnabled(false);
            seconds = 59;
            tvWaktuTunggu.setText("00:" + seconds);
            tvWaktuTunggu.setVisibility(View.VISIBLE);
            stopTimer = false;
            timer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        Util.hideKeyboard(this);
        tvDescNomor.setText(tvDescNomor.getText() + " " + getIntent().getStringExtra("nomorTelepon"));

        tvWaktuTunggu.setText("00:" + seconds);
        timer();
    }
}
