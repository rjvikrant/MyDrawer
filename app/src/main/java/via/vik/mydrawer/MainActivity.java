package via.vik.mydrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import via.vik.mydrawer.fragments.AboutUsFragment;
import via.vik.mydrawer.fragments.HomeFragment;
import via.vik.mydrawer.fragments.PartnerPreferences.PartnerPreferencesFragment;
import via.vik.mydrawer.fragments.ProfileFragment;
import via.vik.mydrawer.fragments.ShortListedProfileFragments;
import via.vik.mydrawer.fragments.settings.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_ABOUT ="about" ;
    NavigationView navigationView;
    DrawerLayout drawer;
    public static int navItemIndex = 0;
    FloatingActionButton fab;
    private Handler mHandler;
    public MainActivity mainActivity;

    private static final String TAG_HOME = "home";
    private static final String TAG_GALLERY = "gallery";
    private static final String TAG_SHORTLISTED = "shortlisted";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_PREFERENCES = "preferences";
    public static String CURRENT_TAG = TAG_HOME;
    FragmentCommunicator fragmentCommunicator;
    public Toolbar toolbar;
    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }


    private String[] activityTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainActivity=this;
         navigationView = (NavigationView) findViewById(R.id.nav_view);
        mHandler=new Handler();
        View hView =  navigationView.getHeaderView(0);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCommunicator.passData("Hello");
              //  fab.setImageResource(R.drawable.ic_chat);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            SharedPreferences sh=this.getSharedPreferences(LoginActivity.MyPref,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sh.edit();
            editor.putBoolean(LoginActivity.IsLogin,false);
            editor.putString(LoginActivity.Username,"");
            editor.putString(LoginActivity.Password,"");
            editor.putString(LoginActivity.UserId,"");
            editor.commit();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_GALLERY;

        }else if (id == R.id.nav_profile){
            navItemIndex = 2;
            CURRENT_TAG = TAG_PROFILE;

        }else if (id == R.id.nav_preferences){
            navItemIndex = 3;
            CURRENT_TAG = TAG_PREFERENCES;

        } else if (id == R.id.nav_slideshow) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_SHORTLISTED;

        }else if (id == R.id.nav_manage){

        navItemIndex=5;
        CURRENT_TAG=TAG_ABOUT;

        }else if (id == R.id.nav_settings){

            navItemIndex=6;
            CURRENT_TAG=TAG_SETTINGS;

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        drawer.closeDrawer(GravityCompat.START);
        loadHomeFragment();
        return true;
    }

     void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment(mainActivity);
              return homeFragment;
            case 1:
                // Gallery
                ShortListedProfileFragments moviesFragment = new ShortListedProfileFragments();
                return moviesFragment;


            case 2:
                ProfileFragment profileFragment = new ProfileFragment(mainActivity);
                return  profileFragment;

            case 3:
                // settings
                PartnerPreferencesFragment partnerPreferencesFragment = new PartnerPreferencesFragment(mainActivity);
                return partnerPreferencesFragment;

            case 4:
                // Shortlisted profiles
                ShortListedProfileFragments moviesFragment1 = new ShortListedProfileFragments();
                return moviesFragment1;

            case 6:
                SettingFragment settingFragment=new SettingFragment();
                return settingFragment;


            case 5:
                // settings
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                return aboutUsFragment;



            default:
                return new HomeFragment();
        }
    }

    private void toggleFab() {
        if (navItemIndex == 0 )
            fab.show();

        else if (navItemIndex ==1){
            fab.setImageResource(R.drawable.ic_search);
            fab.show();

        }
        else if (navItemIndex ==2){

            fab.show();
            fab.setImageResource(R.drawable.ic_edit);
        }

        else if ( navItemIndex == 3 ) {
            fab.show();
            fab.setImageResource(R.drawable.ic_edit);
        }


        else if (navItemIndex ==4){

            fab.show();
            fab.setImageResource(R.drawable.ic_done);
        }
        else if (navItemIndex ==5){

            fab.show();
            fab.setImageResource(R.drawable.ic_call);
        }
        else
            fab.hide();
    }

    public void passVal(FragmentCommunicator fragmentCommunicator) {
        this.fragmentCommunicator = fragmentCommunicator;

    }

    public interface FragmentCommunicator {

        public void passData(String name);
    }
}
