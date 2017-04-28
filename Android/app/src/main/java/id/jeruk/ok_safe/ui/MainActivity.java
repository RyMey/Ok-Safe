package id.jeruk.ok_safe.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.data.model.User;
import id.jeruk.ok_safe.data.remote.RestApi;
import id.jeruk.ok_safe.presenter.MainPresenter;
import id.jeruk.ok_safe.ui.adapter.ReportsAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {

    @BindView(R.id.rv_main) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.iv_ph_main) ImageView placeHolder;

    private TextView navNama;
    private TextView navStatus;
    private TextView navId;
    private ImageView navPhoto;

    private View hView;

    private MainPresenter mainPresenter;
    private ReportsAdapter reportsAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Laporan Anda");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        reportsAdapter = new ReportsAdapter(this);
        mRecyclerView.setAdapter(reportsAdapter);
        reportsAdapter.setOnItemClickListener((view, position) ->
                onItemClick(reportsAdapter.getData().get(position)));

        hView = navigationView.getHeaderView(0);

        navNama = (TextView) hView.findViewById(R.id.tv_nav_nama);
        navStatus = (TextView) hView.findViewById(R.id.tv_nav_valid_status);
        navId = (TextView) hView.findViewById(R.id.tv_nav_id);
        navPhoto = (ImageView) hView.findViewById(R.id.iv_nav_photo);

        User user = LocalDataManager.getInstance(this).getUser();

        navNama.setText(user.getName());
        navId.setText(user.getId());
        navStatus.setText(user.getStatus());
        if (user.getImgUrl() != null) {
            Glide.with(this).load(user.getImgUrl()).centerCrop().into(navPhoto);
        } else {
            navPhoto.setImageResource(R.drawable.ic_person);
        }

        progressDialog = new ProgressDialog(this);

        mainPresenter = new MainPresenter(this, this);
        mainPresenter.loadReports();
    }

    private void onItemClick(Report report) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("isUbahProfile", true);
            startActivity(intent);
        } else if (id == R.id.nav_reward) {
            startActivity(new Intent(this, RewardActivity.class));
        } else if (id == R.id.nav_map) {
            startActivity(new Intent(this, MapActivity.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(this, HelpActivity.class));
        } else if (id == R.id.nav_tentang) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_developer) {
            startActivity(new Intent(this, AboutDeveloperActivity.class));
        } else if (id == R.id.nav_logout) {
            LocalDataManager.getInstance(this).clearData();
            RestApi.getInstance(this).logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    public void tambahLaporan() {
        startActivity(new Intent(this, AddReportActivity.class));
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(mRecyclerView.getRootView(), errorMessage, Snackbar.LENGTH_LONG).show();
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
    public void showReports(List<Report> reports) {
        reportsAdapter.addOrUpdate(reports);
        if (reportsAdapter.getItemCount() > 0) {
            placeHolder.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            placeHolder.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
