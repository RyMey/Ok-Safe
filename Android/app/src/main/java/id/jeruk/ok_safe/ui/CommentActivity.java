package id.jeruk.ok_safe.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.presenter.CommentPresenter;
import id.jeruk.ok_safe.ui.adapter.CommentAdapter;

public class CommentActivity extends AppCompatActivity implements CommentPresenter.View {
    private static final String EXTRA_REPORT = "extra_report";

    @BindView(R.id.button_send) ImageView ivButtonSend;
    @BindView(R.id.et_komentar) EditText etKomentar;
    @BindView(R.id.rv_komentar) RecyclerView mRecyclerView;
    @BindView(R.id.holder) ImageView placeHolder;

    private CommentPresenter commentPresenter;
    private Report report;
    private CommentAdapter commentAdapter;
    private ProgressDialog progressDialog;

    public static Intent generateIntent(Context context, Report report) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(EXTRA_REPORT, report);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        report = getIntent().getParcelableExtra(EXTRA_REPORT);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        commentAdapter = new CommentAdapter(this);
        mRecyclerView.setAdapter(commentAdapter);

        progressDialog = new ProgressDialog(this);

        commentPresenter = new CommentPresenter(this, this);
        commentPresenter.loadComments(report.getId());
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @OnTextChanged(R.id.et_komentar)
    public void setComment(CharSequence str) {
        if (str.length() > 0) {
            ivButtonSend.setColorFilter(getResources().getColor(R.color.colorPrimary));
        } else {
            ivButtonSend.setColorFilter(getResources().getColor(R.color.colorDivider));
        }
    }

    @OnClick(R.id.button_send)
    public void sendComment() {
        String message = etKomentar.getText().toString().trim();
        if (!message.isEmpty()) {
            commentPresenter.sendComment(report.getId(), message);
            etKomentar.setText("");
        }
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
    public void showComments(List<Comment> comments) {
        commentAdapter.addOrUpdate(comments);
        if (commentAdapter.getItemCount() > 0) {
            placeHolder.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            placeHolder.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCommentSend(Comment comment) {
        commentAdapter.addOrUpdate(comment);
        if (commentAdapter.getItemCount() > 0) {
            placeHolder.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            placeHolder.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
