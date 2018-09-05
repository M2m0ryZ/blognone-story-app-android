package com.benznestdeveloper.blognonestory.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MyUtils;
import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.fragment.AboutFragment;
import com.benznestdeveloper.blognonestory.fragment.MainFragment;
import com.benznestdeveloper.blognonestory.fragment.NodeFragment;
import com.benznestdeveloper.blognonestory.fragment.NodeListFragment;
import com.benznestdeveloper.blognonestory.fragment.SettingFragment;
import com.benznestdeveloper.blognonestory.fragment.TagListFragment;
import com.benznestdeveloper.blognonestory.service.blognone.web.MyBlognoneForum;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private int nodeId = -1;
    private static Activity activity;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);

        if (!MyUtils.isTablet()) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        initView();
        initFirebase();
        if (savedInstanceState == null) {
            navigateFragment(R.id.nav_home);
        }

        Intent intent = getIntent();
        if (intent != null) {
            nodeId = intent.getIntExtra(MyConstants.KEY_NODE_ID, -1);
            if (nodeId > 0) {
                Fragment fragment = NodeFragment.newInstance(nodeId);
                setContent(fragment, "");
                nodeId = -1;
            }

            tag = intent.getStringExtra(MyConstants.KEY_NODE_TAG);
            if (tag != null) {
                Fragment fragment = NodeListFragment.newInstance(tag);
                setContent(fragment, "");
                tag = null;
            }

        }

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            int topicId = bundle.getInt(MyConstant.KEY_TOPIC, -1);
//            if (topicId != -1) {
//                showTopicFragment(topicId);
//            }
//
//            String tag = bundle.getString(MyConstant.KEY_TOPIC_TAG, null);
//            if (tag != null) {
//                showTagTopicFragment(tag);
//            }
//        }
    }

    private void initFirebase() {
        String token = FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        Log.d("FIREBASE TOKEN = ", "" + token);
    }

    private void navigateFragment(int id) {
        if (id == R.id.nav_home) {
            setContent(MainFragment.newInstance(), MyConstants.TAG_NAV_HOME);
        } else if (id == R.id.nav_tag) {
            setContent(TagListFragment.newInstance(), MyConstants.TAG_NAV_TAG);
        } else if (id == R.id.nav_interview) {
            setContent(NodeListFragment.newInstance(NodeListFragment.MODE_INTERVIEW), MyConstants.TAG_NAV_INTERVIEW);
        } else if (id == R.id.nav_workplace) {
            setContent(NodeListFragment.newInstance(NodeListFragment.MODE_WORKPLACE), MyConstants.TAG_NAV_SETTING);
        } else if (id == R.id.nav_favorite_node) {
            setContent(NodeListFragment.newInstance(NodeListFragment.MODE_FAVORITE), MyConstants.TAG_NAV_FAVORITE);
        } else if (id == R.id.nav_cache_node) {
            setContent(NodeListFragment.newInstance(NodeListFragment.MODE_NODE_CACHE), MyConstants.TAG_NAV_CACHE);
        } else if (id == R.id.nav_history) {
            setContent(NodeListFragment.newInstance(NodeListFragment.MODE_NODE_HISTORY), MyConstants.TAG_NAV_HISTORY);
        } else if (id == R.id.nav_forum_blognone_th) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumBlognoneTh()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_forum_blognone_eng) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumBlognoneEng()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_forum_python) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumPython()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_forum_ruby) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumRuby()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_forum_programming_problem) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumProblemProgramming()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_forum_projects) {
            setContent(NodeListFragment.newInstance(MyBlognoneForum.getForumProject()),
                    MyConstants.TAG_NAV_FORUM);
        } else if (id == R.id.nav_about) {
            setContent(AboutFragment.newInstance(), MyConstants.TAG_NAV_ABOUT);
        } else if (id == R.id.nav_setting) {
            setContent(SettingFragment.newInstance(), MyConstants.TAG_NAV_SETTING);
        }

        MyUtils.vibrate();
    }

    public void setContent(Fragment fragment, String tag) {
        setContent(fragment, tag, true);
    }

    public void setContent(Fragment fragment, String tag, boolean isBackstack) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, fragment, tag);

        if (isBackstack) {
            t = t.addToBackStack("");
        }

        t.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(MyConstants.TAG_NAV_HOME);
                if (fragment != null) {
                    navigateFragment(R.id.nav_home);
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                navigateFragment(id);

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public static Activity getMainActivity() {
        return activity;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
