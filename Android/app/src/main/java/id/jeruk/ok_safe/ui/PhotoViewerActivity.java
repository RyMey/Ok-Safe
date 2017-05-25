package id.jeruk.ok_safe.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.ui.adapter.PhotoPagerAdapter;
import id.jeruk.ok_safe.ui.fragment.PhotoFragment;

public class PhotoViewerActivity extends RxAppCompatActivity implements ViewPager.OnPageChangeListener, PhotoFragment.ClickListener {
    private static final String EXTRA_REPORT = "extra_comment";
    private static final String KEY_POSITION = "last_position";

    private Toolbar toolbar;
    private TextView tvTitle;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private Animation fadein, fadeout;

    private int position = 0;
    private Report report;

    public static Intent generateIntent(Context context, Report report) {
        Intent intent = new Intent(context, PhotoViewerActivity.class);
        intent.putExtra(EXTRA_REPORT, report);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_viewer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        setSupportActionBar(toolbar);
        viewPager.addOnPageChangeListener(this);

        resolveData(savedInstanceState);

        if (report == null) {
            return;
        }

        showLoading();
        initPhotos();
        dismissLoading();
    }

    private void resolveData(Bundle savedInstanceState) {
        report = getIntent().getParcelableExtra(EXTRA_REPORT);
        if (report == null && savedInstanceState != null) {
            report = savedInstanceState.getParcelable(EXTRA_REPORT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_REPORT, report);
        outState.putInt(KEY_POSITION, position);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void dismissLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void initPhotos() {
        List<PhotoFragment> fragments = new ArrayList<>();
        for (int i = 0; i < report.getPhotoUrls().size(); i++) {
            fragments.add(PhotoFragment.newInstance(report.getPhotoUrls().get(i)));
        }
        viewPager.setAdapter(new PhotoPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(position);
        tvTitle.setText("Foto ke " + (position + 1));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        tvTitle.setText("Foto ke " + (position + 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPhotoClick() {
        if (toolbar.getVisibility() == View.VISIBLE) {
            toolbar.startAnimation(fadeout);
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.startAnimation(fadein);
            toolbar.setVisibility(View.VISIBLE);
        }
    }
}
