package id.jeruk.ok_safe.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trello.rxlifecycle.components.support.RxFragment;

import id.jeruk.ok_safe.R;
import id.jeruk.ok_safe.ui.view.TouchImageView;

public class PhotoFragment extends RxFragment {
    private static final String EXTRA_IMAGE_URL = "extra_image_url";

    private TouchImageView imageView;
    private String imageUrl;
    private ClickListener listener;

    public static PhotoFragment newInstance(String imageUrl) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, container, false);
        imageView = (TouchImageView) view.findViewById(R.id.image_view);
        imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPhotoClick();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resolveImageFile(savedInstanceState);
        if (imageUrl == null) {
            return;
        }
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity instanceof ClickListener) {
            listener = (ClickListener) activity;
        }
    }

    private void resolveImageFile(Bundle savedInstanceState) {
        imageUrl = getArguments().getString(EXTRA_IMAGE_URL);
        if (imageUrl == null && savedInstanceState != null) {
            imageUrl = savedInstanceState.getString(EXTRA_IMAGE_URL);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_IMAGE_URL, imageUrl);
    }

    public interface ClickListener {
        void onPhotoClick();
    }
}
