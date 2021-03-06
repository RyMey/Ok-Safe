package id.jeruk.ok_safe.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;

public class AboutDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void back(){
        onBackPressed();
    }
}
