package id.jeruk.ok_safe.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Reward;
import id.jeruk.ok_safe.presenter.RewardPresenter;
import id.jeruk.ok_safe.ui.adapter.RewardAdapter;

public class RewardActivity extends AppCompatActivity implements RewardPresenter.View {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private RewardAdapter rewardAdapter;
    private RewardPresenter rewardPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);

        rewardAdapter = new RewardAdapter(this);
        recyclerView.setAdapter(rewardAdapter);
        rewardAdapter.setOnItemClickListener((view, position) ->
                onItemClick(rewardAdapter.getData().get(position)));

        progressDialog = new ProgressDialog(this);

        rewardPresenter = new RewardPresenter(this);
        rewardPresenter.loadRewad();
    }

    //TODO buka halaman detailnya
    private void onItemClick(Reward reward) {

    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @Override
    public void showRewards(List<Reward> rewards) {
        rewardAdapter.addOrUpdate(rewards);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(recyclerView.getRootView(), errorMessage, Snackbar.LENGTH_LONG).show();
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
}
