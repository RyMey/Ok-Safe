package id.jeruk.ok_safe.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.util.Util;

public class AddReportActivity extends AppCompatActivity {
    private String imgUrl[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void back(){
        onBackPressed();
    }

    @OnClick(R.id.iv_photo1)
    public void inputPhoto1() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) Util.openCamera(AddReportActivity.this);
                else if (which == 1) Util.openGallery(AddReportActivity.this);
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.iv_photo2)
    public void inputPhoto2() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) Util.openCamera(AddReportActivity.this);
                else if (which == 1) Util.openGallery(AddReportActivity.this);
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.iv_photo3)
    public void inputPhoto3() {
        String[] inputPhotoOptions = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(inputPhotoOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) Util.openCamera(AddReportActivity.this);
                else if (which == 1) Util.openGallery(AddReportActivity.this);
                dialog.dismiss();
            }
        }).show();
    }
}
