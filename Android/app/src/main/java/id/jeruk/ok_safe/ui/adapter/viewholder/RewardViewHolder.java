package id.jeruk.ok_safe.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import id.jeruk.ok_safe.OkSafeApp;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Reward;
import id.jeruk.ok_safe.ui.adapter.OnItemClickListener;
import id.jeruk.ok_safe.ui.adapter.OnLongItemClickListener;

public class RewardViewHolder extends BaseItemViewHolder<Reward> {
    @BindView(R.id.iv_reward) ImageView imageView;
    @BindView(R.id.judul_reward) TextView titleView;
    @BindView(R.id.tgl_reward) TextView dateView;

    public RewardViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Reward reward) {
        Glide.with(OkSafeApp.getInstance()).load(reward.getImgUrl()).error(R.drawable.ic_reward_ph).into(
                imageView);
        titleView.setText(reward.getTitle());
        dateView.setText(new SimpleDateFormat("dd/MM/yyyy").format(reward.getDate()));
    }
}
