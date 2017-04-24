package id.jeruk.ok_safe.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.BuildConfig;
import id.jeruk.ok_safe.R;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.tv_version) TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        tvVersion.setText(String.format("%s %s", getString(R.string.version), BuildConfig.VERSION_NAME));
    }

    @OnClick(R.id.iv_back)
    public void back(){
        onBackPressed();
    }

    @OnClick(R.id.bt_feedback)
    public void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:rya_meyvriska@apps.ipb.ac.id"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback Ok-Safe");
        startActivity(Intent.createChooser(intent,"Kirim Melalui..."));
    }
}
