package via.vik.mydrawer;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import via.vik.mydrawer.fragments.AboutMeInfoFragment;
import via.vik.mydrawer.fragments.BasicInfoFragment;
import via.vik.mydrawer.fragments.ChatFragment;
import via.vik.mydrawer.fragments.ContactInfoFragment;
import via.vik.mydrawer.fragments.EducationalInfoFragment;
import via.vik.mydrawer.fragments.FamilyInfoFragment;
import via.vik.mydrawer.fragments.HobbyInfoFragment;
import via.vik.mydrawer.fragments.HomeFragment;
import via.vik.mydrawer.fragments.HoroscopeInfoFragment;
import via.vik.mydrawer.fragments.LifestyleInfoFragment;
import via.vik.mydrawer.fragments.MatchCompatibilityFragment;
import via.vik.mydrawer.fragments.RequestFragment;
import via.vik.mydrawer.fragments.RequestsFragment;
import via.vik.mydrawer.fragments.ShortListedProfileFragments;

public class ProfileActivity extends AppCompatActivity {
    FloatingActionButton fab;

  android.support.v7.widget.Toolbar toolbar;

    TabLayout tabLayout;
     OnFragmentInteractionListener1 mListener;
    FragmentCommunicator fragmentCommunicator;

    String title[]={"About Him","Basic information","Educational & Occupational","Lifestyle & Appearance"," Family","Horoscope Details",
            "Hobby/Interest","Match Compatibility"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager_profile);
        setupViewPager(viewPager);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_profile);
        fab=(FloatingActionButton)findViewById(R.id.fab);
         tabLayout = (TabLayout) findViewById(R.id.tabs_profile);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCommunicator.passData("Hello");
            }
        });
    }


    private void setupTabIcons() {
        int[] tabIcons = {
                R.mipmap.ic_about_me,
                R.drawable.ic_chat,
                R.drawable.ic_education,
                R.drawable.ic_matches,
                R.drawable.ic_family,
                R.drawable.ic_horocope,
                R.drawable.ic_matches,
                R.drawable.ic_contact
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
        tabLayout.getTabAt(7).setIcon(tabIcons[7]);
    }


    private void setupViewPager(ViewPager viewPager) {
        ProfileActivity.ViewPagerAdapter adapter = new ProfileActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AboutMeInfoFragment(), "ONE");
        adapter.addFrag(new BasicInfoFragment(), "TWO");
        adapter.addFrag(new EducationalInfoFragment(), "THREE");
        adapter.addFrag(new LifestyleInfoFragment(), "FOUR");
        adapter.addFrag(new FamilyInfoFragment(), "FIVE");
        adapter.addFrag(new HoroscopeInfoFragment(), "SIX");
        adapter.addFrag(new HobbyInfoFragment(), "SEVEN");
        adapter.addFrag(new MatchCompatibilityFragment(), "EIGHT");
        viewPager.setAdapter(adapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(title[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void passVal(ProfileActivity.FragmentCommunicator fragmentCommunicator) {
        this.fragmentCommunicator = fragmentCommunicator;

    }

    public interface OnFragmentInteractionListener1 {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface FragmentCommunicator {

        public void passData(String name);
    }
}

