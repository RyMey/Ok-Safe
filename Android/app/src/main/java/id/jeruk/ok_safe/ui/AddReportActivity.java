package id.jeruk.ok_safe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.util.Util;

public class AddReportActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.address) TextView tvAddress;

    private String imgUrl[];

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
            if (which == 0) Util.openCamera(AddReportActivity.this);
            else if (which == 1) Util.openGallery(AddReportActivity.this);
            dialog.dismiss();
        }).show();
    }

    @OnClick(R.id.iv_photo2)
    public void inputPhoto2() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, (dialog, which) -> {
            if (which == 0) Util.openCamera(AddReportActivity.this);
            else if (which == 1) Util.openGallery(AddReportActivity.this);
            dialog.dismiss();
        }).show();
    }

    @OnClick(R.id.iv_photo3)
    public void inputPhoto3() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, (dialog, which) -> {
            if (which == 0) Util.openCamera(AddReportActivity.this);
            else if (which == 1) Util.openGallery(AddReportActivity.this);
            dialog.dismiss();
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PLACE_PICKER_REQUEST) {
            tvAddress.setText(PlacePicker.getPlace(this, data).getAddress());
        }
    }
}
