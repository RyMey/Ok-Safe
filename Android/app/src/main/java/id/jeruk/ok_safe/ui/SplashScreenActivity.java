package id.jeruk.ok_safe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (LocalDataManager.getInstance(this).getStatus() == LocalDataManager.Status.IN) {
            new Handler().postDelayed(() -> startActivity(new Intent(this, MainActivity.class)), 1500);
        } else if (LocalDataManager.getInstance(this).getStatus() == LocalDataManager.Status.WAIT) {
            new Handler().postDelayed(() -> startActivity(new Intent(this, VerificationActivity.class)), 1500);
        } else if (LocalDataManager.getInstance(this).getStatus() == LocalDataManager.Status.NEW) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("isUbahProfile", false);
            new Handler().postDelayed(() -> startActivity(intent), 1500);
        } else {
            new Handler().postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)), 1500);
        }
    }
}
