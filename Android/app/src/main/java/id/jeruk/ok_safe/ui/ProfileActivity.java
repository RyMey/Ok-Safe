package id.jeruk.ok_safe.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.util.Util;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.et_nama) EditText etNama;
    @BindView(R.id.et_id_user) EditText etIdUser;
    @BindView(R.id.bt_simpan) Button btSimpan;
    @BindView(R.id.iv_back) ImageView ivBack;

    private boolean isUbahProfile = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Util.hideKeyboard(this);

        isUbahProfile = getIntent().getExtras().getBoolean("isUbahProfile");

        if(isUbahProfile){
            ivBack.setVisibility(View.VISIBLE);
        }else {
            ivBack.setVisibility(View.GONE);
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
                if (which == 0) openCamera();
                else if (which == 1) openGallery();
                dialog.dismiss();
            }
        }).show();

    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
    }

    @OnClick(R.id.bt_simpan)
    public void simpan(){
        startActivity(new Intent(this,MainActivity.class));

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
    public void back(){
        onBackPressed();
    }
}
