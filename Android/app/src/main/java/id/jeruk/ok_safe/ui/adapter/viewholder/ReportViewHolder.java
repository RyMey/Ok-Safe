package id.jeruk.ok_safe.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;

import butterknife.BindView;
import id.jeruk.ok_safe.OkSafeApp;
import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.local.LocalDataManager;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.ui.adapter.OnItemClickListener;
import id.jeruk.ok_safe.ui.adapter.OnLongItemClickListener;

public class ReportViewHolder extends BaseItemViewHolder<Report> {
    @BindView(R.id.iv_photo) ImageView ivAvatar;
    @BindView(R.id.tv_judul) TextView tvTitle;
    @BindView(R.id.lokasi) TextView tvLocation;
    @BindView(R.id.tv_time) TextView tvTime;
    @BindView(R.id.tv_laporan) TextView tvDesc;
    @BindView(R.id.cl_images) CarouselView carouselView;

    public ReportViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Report report) {
        Glide.with(OkSafeApp.getInstance())
                .load(LocalDataManager.getInstance(itemView.getContext()).getUser().getImgUrl())
                .error(R.drawable.ic_person)
                .placeholder(R.drawable.ic_person)
                .into(ivAvatar);

        tvTitle.setText("Judul");
        tvLocation.setText(report.getLocation());
        tvTime.setText("Kemarin");
        tvDesc.setText(report.getDesc());

        carouselView.setPageCount(report.getPhotoUrls().size());

        carouselView.setImageListener((position, imageView) -> Glide.with(OkSafeApp.getInstance())
                .load(report.getPhotoUrls().get(position))
                .into(imageView));
    }
}
