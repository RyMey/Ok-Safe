package id.jeruk.ok_safe.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.User;
import id.jeruk.ok_safe.presenter.ProfilePresenter;
import id.jeruk.ok_safe.util.FileUtil;
import id.jeruk.ok_safe.util.Util;

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.View {
    @BindView(R.id.et_nama) EditText etNama;
    @BindView(R.id.et_id_user) EditText etIdUser;
    @BindView(R.id.bt_simpan) Button btSimpan;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_description) TextView tvDesc;
    @BindView(R.id.tv_phoneNumber) TextView tvPhoneNumber;
    @BindView(R.id.iv_photo) ImageView ivPhoto;

    private boolean isUbahProfile = false;
    private ProfilePresenter profilePresenter;
    private ProgressDialog progressDialog;
    private User user;

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

        profilePresenter = new ProfilePresenter(this, this);
        progressDialog = new ProgressDialog(this);
        user = LocalDataManager.getInstance(this).getUser();

        if (user != null) {
            etNama.setText(user.getName());
            etIdUser.setText(user.getId());
            if (user.getImgUrl() != null) {
                Glide.with(this).load(user.getImgUrl()).centerCrop().into(ivPhoto);
            } else {
                ivPhoto.setImageResource(R.drawable.ic_person);
            }
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
                if (which == 0) Util.openCamera(ProfileActivity.this, 11);
                else if (which == 1) Util.openGallery(ProfileActivity.this, 12);
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.bt_simpan)
    public void simpan() {
        profilePresenter.saveProfile(etNama.getText().toString(), etIdUser.getText().toString());
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(etNama.getRootView(), errorMessage, Snackbar.LENGTH_LONG).show();
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

    @Override
    public void onSavedProfile() {
        Snackbar.make(etNama.getRootView(), "Profil berhasil di ubah", Snackbar.LENGTH_LONG).show();

        if (ivBack.getVisibility() != View.VISIBLE) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onAvatarUploaded() {
        Snackbar.make(etNama.getRootView(), "Photo berhasil diubah", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode % 10 == 1) {
            try {
                Glide.with(this).load(FileUtil.from(this, Uri.parse(LocalDataManager.getInstance(this)
                        .getLastImagePath()))).into(ivPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode % 10 == 2) {
            try {
                Glide.with(this).load(FileUtil.from(this, data.getData()))
                        .into(ivPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}