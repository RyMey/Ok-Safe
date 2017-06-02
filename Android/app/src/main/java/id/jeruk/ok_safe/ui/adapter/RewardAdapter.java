package id.jeruk.ok_safe.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Reward;
import id.jeruk.ok_safe.ui.adapter.viewholder.RewardViewHolder;

public class RewardAdapter extends BaseRecyclerAdapter<Reward, RewardViewHolder> {
    public RewardAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_reward;
    }

    @Override
    public RewardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RewardViewHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
