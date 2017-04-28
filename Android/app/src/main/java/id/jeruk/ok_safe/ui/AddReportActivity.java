package id.jeruk.ok_safe.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.presenter.AddReportPresenter;
import id.jeruk.ok_safe.util.FileUtil;
import id.jeruk.ok_safe.util.Util;

public class AddReportActivity extends AppCompatActivity implements AddReportPresenter.View {
    private static final int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.address) TextView tvAddress;
    @BindView(R.id.et_report) EditText etReport;
    @BindView(R.id.iv_photo1) ImageView ivPhoto1;
    @BindView(R.id.iv_photo2) ImageView ivPhoto2;
    @BindView(R.id.iv_photo3) ImageView ivPhoto3;

    private AddReportPresenter addReportPresenter;
    private List<File> photos;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        ButterKnife.bind(this);
        addReportPresenter = new AddReportPresenter(this, this);
        photos = new ArrayList<>(3);
        progressDialog = new ProgressDialog(this);
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
        } else if (resultCode == RESULT_OK && requestCode % 10 == 1) {
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
        photos.set(index - 1, file);
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

    @OnClick(R.id.button_send)
    public void sendReport() {
        String location = tvAddress.getText().toString().trim();
        String desc = etReport.getText().toString().trim();
        if (location.equals(getString(R.string.press_pin_lokasi))) {
            showError("Mohon isi lokasi laporan!");
        } else if (TextUtils.isEmpty(desc)) {
            etReport.setError("Mohon isi detail laporan!");
            etReport.requestFocus();
        } else {
            addReportPresenter.postReport(location, desc, photos);
        }
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(etReport.getRootView(), errorMessage, Snackbar.LENGTH_LONG).show();
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
    public void onReported(Report report) {
        Log.d("AddReportActivity", "Reported: " + report);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addReportPresenter.detachView();
    }
}
