package id.jeruk.ok_safe.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
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
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.presenter.VerificationPresenter;
import id.jeruk.ok_safe.util.Util;

public class VerificationActivity extends AppCompatActivity implements VerificationPresenter.View{
    @BindView(R.id.tv_desc_nomor) TextView tvDescNomor;
    @BindView(R.id.iv_cancel) ImageView ivCancel;
    @BindView(R.id.et_kode_verifikasi) EditText etKodeVerifikasi;
    @BindView(R.id.bt_kirim_ulang) Button btKirimUlang;
    @BindView(R.id.tv_waktu_tunggu) TextView tvWaktuTunggu;

    private int seconds = 59;
    private boolean stopTimer = false;
    private String phoneNumber;
    private ProgressDialog progressDialog;
    private VerificationPresenter verificationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        Util.hideKeyboard(this);
        phoneNumber = LocalDataManager.getInstance(this).getPhoneNumber();
        tvDescNomor.setText(getString(R.string.desc_nomor_telepon) + " +62" + phoneNumber);

        verificationPresenter = new VerificationPresenter(this,this);
        progressDialog = new ProgressDialog(this);

        tvWaktuTunggu.setText("00:" + seconds);
        timer();
    }

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
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("nomorTelepon",phoneNumber);
        startActivity(intent);
    }

    @OnTextChanged(R.id.et_kode_verifikasi)
    public void setNomorTelepon(CharSequence str) {
        if(str.length()<=0){
            btKirimUlang.setBackgroundColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorPrimary));
            btKirimUlang.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorWhite));
            btKirimUlang.setText("Kirim Ulang Kode");
            tvWaktuTunggu.setVisibility(View.VISIBLE);
            btKirimUlang.setEnabled(false);
        }else {
            btKirimUlang.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            btKirimUlang.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryText));
            btKirimUlang.setText("Kirim");
            tvWaktuTunggu.setVisibility(View.INVISIBLE);
            btKirimUlang.setEnabled(true);
        }

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
        if (btKirimUlang.getText().equals("Kirim")) {
            Util.hideKeyboard(this);
            verificationPresenter.checkVerification(etKodeVerifikasi.getText().toString());
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
    public void onVerified() {
        stopTimer = true;
        Intent intent;
        if(LocalDataManager.getInstance(this).getStatus().equals(LocalDataManager.Status.NEW)){
            intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("isUbahProfile", false);
        }else{
            intent = new Intent(this,MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(btKirimUlang.getRootView(),errorMessage,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }
}
