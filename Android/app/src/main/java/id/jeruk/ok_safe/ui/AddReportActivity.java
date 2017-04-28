package id.jeruk.ok_safe.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.util.FileUtil;
import id.jeruk.ok_safe.util.Util;

public class AddReportActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.address) TextView tvAddress;
    @BindView(R.id.iv_photo1) ImageView ivPhoto1;
    @BindView(R.id.iv_photo2) ImageView ivPhoto2;
    @BindView(R.id.iv_photo3) ImageView ivPhoto3;

    private File[] files = new File[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.pick_location)
    public void pickLocation() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @OnClick(R.id.iv_photo1)
    public void inputPhoto1() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, (dialog, which) -> {
            if (which == 0) Util.openCamera(this, 11);
            else if (which == 1) Util.openGallery(this, 12);
            dialog.dismiss();
        }).show();
    }

    @OnClick(R.id.iv_photo2)
    public void inputPhoto2() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, (dialog, which) -> {
            if (which == 0) Util.openCamera(this, 21);
            else if (which == 1) Util.openGallery(this, 22);
            dialog.dismiss();
        }).show();
    }

    @OnClick(R.id.iv_photo3)
    public void inputPhoto3() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, (dialog, which) -> {
            if (which == 0) Util.openCamera(this, 31);
            else if (which == 1) Util.openGallery(this, 32);
            dialog.dismiss();
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PLACE_PICKER_REQUEST) {
            tvAddress.setText(PlacePicker.getPlace(this, data).getAddress());
        }
        if (resultCode == RESULT_OK && requestCode % 10 == 1) {
            try {
                addImageFile(FileUtil.from(this, Uri.parse(LocalDataManager.getInstance(this)
                        .getLastImagePath())), requestCode / 10);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode % 10 == 2) {
            try {
                addImageFile(FileUtil.from(this, data.getData()), requestCode / 10);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addImageFile(File file, int index) {
        switch (index) {
            case 1:
                Glide.with(this).load(file).into(ivPhoto1);
                break;
            case 2:
                Glide.with(this).load(file).into(ivPhoto2);
                break;
            case 3:
                Glide.with(this).load(file).into(ivPhoto3);
                break;
        }
    }
}
