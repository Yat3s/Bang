package com.aran.bang.module;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.aran.bang.R;
import com.aran.bang.base.BaseActivity;
import com.aran.bang.module.auth.AuthActivity;
import com.aran.bang.module.contact.ContactFragment;
import com.aran.bang.module.help.HelpFragment;
import com.aran.bang.module.inbox.InboxFragment;
import com.aran.bang.module.setting.SettingFragment;
import com.gigamole.library.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    NavigationTabBar navigationTab;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private List<Fragment> mFragments;

    @Override
    protected void onStart() {
        super.onStart();
        if (null == BmobUser.getCurrentUser(this, UserModel.class)) {
            startActivity(AuthActivity.class);
            finish();
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initNavigation();
        initViewpager();
        initTab();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_main;
    }

    private void initNavigation() {
        setSupportActionBar(toolbar);
        setTitle("");
    }

    private void initViewpager() {
        mFragments = new ArrayList<>();
        mFragments.add(HelpFragment.newInstance());
        mFragments.add(InboxFragment.newInstance());
        mFragments.add(ContactFragment.newInstance());
        mFragments.add(SettingFragment.newInstance());

        contentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        contentVp.setOffscreenPageLimit(4);
    }

    private void initTab() {
        final String[] titles = new String[]{"Feed", "Inbox", "Contact", "Setting"};
        final String[] colors = getResources().getStringArray(R.array.colors);
        final int[] icons = new int[]{R.mipmap.ic_first, R.mipmap.ic_second, R.mipmap.ic_third, R
                .mipmap.ic_fourth};
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        for (int idx = 0; idx < titles.length; idx++) {
            models.add(
                    new NavigationTabBar.Model.Builder(
                            getResources().getDrawable(icons[idx]),
                            Color.parseColor(colors[idx]))
                            .title(titles[idx])
                            .build()
            );
        }

        navigationTab.setModels(models);
        navigationTab.setViewPager(contentVp, 0);
    }

}
