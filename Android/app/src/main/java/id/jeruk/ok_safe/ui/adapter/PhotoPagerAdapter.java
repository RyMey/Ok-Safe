package id.jeruk.ok_safe.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import id.jeruk.ok_safe.ui.fragment.PhotoFragment;

public class PhotoPagerAdapter extends FragmentStatePagerAdapter {
    private List<PhotoFragment> fragments;

    public PhotoPagerAdapter(FragmentManager fm, List<PhotoFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
