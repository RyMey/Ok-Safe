package id.jeruk.ok_safe.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Report;
import id.jeruk.ok_safe.ui.adapter.viewholder.ReportViewHolder;

public class ReportsAdapter extends BaseRecyclerAdapter<Report, ReportViewHolder> {
    public ReportsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.card_main;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReportViewHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
