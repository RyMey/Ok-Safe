package id.jeruk.ok_safe.ui.adapter;


import android.content.Context;
import android.view.ViewGroup;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.data.model.Comment;
import id.jeruk.ok_safe.ui.adapter.viewholder.CommentViewHolder;

public class CommentAdapter extends BaseRecyclerAdapter<Comment, CommentViewHolder> {
    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_comment;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
