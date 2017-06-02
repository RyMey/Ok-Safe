package id.jeruk.ok_safe.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import id.jeruk.ok_safe.OkSafeApp;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.ui.adapter.OnItemClickListener;
import id.jeruk.ok_safe.ui.adapter.OnLongItemClickListener;

public class CommentViewHolder extends BaseItemViewHolder<Comment> {
    @BindView(R.id.iv_photo) ImageView imageView;
    @BindView(R.id.tv_name) TextView nameView;
    @BindView(R.id.tv_date) TextView dateView;
    @BindView(R.id.tv_komentar) TextView messageView;

    public CommentViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Comment comment) {
        Glide.with(OkSafeApp.getInstance())
                .load(comment.getSender().getImgUrl())
                .error(R.drawable.ic_person)
                .placeholder(R.drawable.ic_person)
                .into(imageView);

        nameView.setText(comment.getSender().getName());
        dateView.setText(new SimpleDateFormat("dd MMM yyyy, HH:mm").format(comment.getDate()));
        messageView.setText(comment.getDesc());
    }
}
