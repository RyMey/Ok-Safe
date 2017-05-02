package id.jeruk.ok_safe.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import id.jeruk.ok_safe.ui.adapter.OnItemClickListener;
import id.jeruk.ok_safe.ui.adapter.OnLongItemClickListener;

public abstract class BaseItemViewHolder<Data> extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnLongClickListener {
    private OnItemClickListener itemClickListener;
    private OnLongItemClickListener longItemClickListener;

    public BaseItemViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClickListener = itemClickListener;
        this.longItemClickListener = longItemClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bind(Data data);

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longItemClickListener != null) {
            longItemClickListener.onLongItemClick(v, getAdapterPosition());
            return true;
        }
        return false;
    }
}