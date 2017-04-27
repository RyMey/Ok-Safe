package id.jeruk.ok_safe.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import id.jeruk.ok_safe.util.Util;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.et_nama) EditText etNama;
    @BindView(R.id.et_id_user) EditText etIdUser;
    @BindView(R.id.bt_simpan) Button btSimpan;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_description) TextView tvDesc;
    @BindView(R.id.tv_phoneNumber) TextView tvPhoneNumber;

    private boolean isUbahProfile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Util.hideKeyboard(this);

        isUbahProfile = getIntent().getExtras().getBoolean("isUbahProfile");
        tvPhoneNumber.setText(String.format("%s%s", getString(R.string.desc_nomor_telepon_profile), LocalDataManager.getInstance(this).getPhoneNumber()));

        if (isUbahProfile) {
            ivBack.setVisibility(View.VISIBLE);
            tvDesc.setVisibility(View.GONE);
        } else {
            ivBack.setVisibility(View.GONE);
            tvDesc.setVisibility(View.VISIBLE);
        }
    }

    @OnTextChanged(R.id.et_nama)
    public void changeNama() {
        nyalakanButtonSimpan();
    }

    @OnTextChanged(R.id.et_id_user)
    public void changeIdUser() {
        nyalakanButtonSimpan();
    }

    @OnClick(R.id.iv_input_photo)
    public void inputPhoto() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) Util.openCamera(ProfileActivity.this);
                else if (which == 1) Util.openGallery(ProfileActivity.this);
                dialog.dismiss();
            }
        }).show();

    }

    @OnClick(R.id.bt_simpan)
    public void simpan() {
        LocalDataManager.getInstance(this).saveStatus(LocalDataManager.Status.IN);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void nyalakanButtonSimpan() {
        if (!etNama.getText().toString().equals("") && !etIdUser.getText().toString().equals("")) {
            btSimpan.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            btSimpan.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryText));
            btSimpan.setEnabled(true);
        } else {
            btSimpan.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDivider));
            btSimpan.setTextColor(ContextCompat.getColor(this, R.color.colorSecondaryText));
            btSimpan.setEnabled(false);
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }
}
