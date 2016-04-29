package petlocator.petlocator;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;

public class NearbyPets extends Default_Activity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Toolbar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button filterButton;

    public static int tabStatus = 1;

    /* These are the dummy entries in the pet list */
    public static CustomListAdapter adapter;

    public static String[] lostitemname = {
            "Boots",
            "Labby",
            "Jeeves",
            "Splingdo",
            "Flubgus",
            "Shmort",
            "Lil Doogie",
            "Sarp",
            "Boots",
            "Labby",
            "Jeeves",
            "Splingdo",
            "Flubgus",
            "Shmort",
            "Lil Doogie",
            "Sarp"
    };
    static ArrayList<String> lostitemnamelist = new ArrayList<String>(Arrays.asList(lostitemname));
    public static String[] lostitemdescription = {
            "Calico short-hair",
            "Golden labradoodle",
            "Silver fox",
            "Black mamba",
            "Purple goose",
            "White poodle",
            "Black sprinkle",
            "Pink moose",
            "Calico short-hair",
            "Golden labradoodle",
            "Silver fox",
            "Black mamba",
            "Purple goose",
            "White poodle",
            "Black sprinkle",
            "Pink moose"
    };
    static ArrayList<String> lostitemdescriptionlist = new ArrayList<String>(Arrays.asList(lostitemdescription));

    public static String[] spotteditemname = {
            "Shoes",
            "Poodle",
            "Jives",
            "Flingdo",
            "Glubfus",
            "Shmoop",
            "Lil CatCat",
            "Prust"
    };
    static ArrayList<String> spotteditemnamelist = new ArrayList<String>(Arrays.asList(spotteditemname));

    public static Integer[] lostimgid = {
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog
    };
    static ArrayList<Integer> lostimgidlist = new ArrayList<Integer>(Arrays.asList(lostimgid));

    public static Integer[] spottedimgid = {
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
    };
    static ArrayList<Integer> spottedimgidlist = new ArrayList<Integer>(Arrays.asList(spottedimgid));

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("Nearby Pets");

        /*
        Assigning view variables to their respective view in xml
        by findViewByID method
         */

        tabLayout = (TabLayout) findViewById(R.id.lost_spotted_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        /*
        Creating Adapter and setting that adapter to the viewPager
        setSupportActionBar method takes the toolbar and sets it as
        the default action bar thus making the toolbar work like a normal
        action bar.
         */

        tabLayout.setupWithViewPager(mViewPager);

        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indicator below the tabs
         */

        //tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.drawable.tab_selector));
        //tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.lost_spotted_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /* initialize the filter button */
        filterButton = (Button) findViewById(R.id.filter_nearby_search);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayFilterPopup();
            }
        });

        /* initializes the seekbar which determines radius of search */
        SeekBar distance_seekbar = (SeekBar) findViewById(R.id.distance_seekbar);
        final TextView distance_text = (TextView) findViewById(R.id.distance_text);

        distance_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int distance = 3;

            @Override
            public void onProgressChanged(SeekBar seekBar, int distanceValue, boolean fromUser) {
                distance = distanceValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distance_text.setText("Radius: " + distance + "mi. /" + seekBar.getMax() + "mi.");
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        /* Handles displaying custom entries on an image+text ListView */

        adapter = new CustomListAdapter(this, lostitemnamelist, lostimgidlist, lostitemdescriptionlist);
        ListView list = (ListView) findViewById(R.id.nearby_pet_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.v("List entry clicked", "");
                if (tabStatus == 0) {
                    String selectedItem = lostitemname[+position];
                    Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                    Intent view_lost_report = new Intent(NearbyPets.this, ViewLostReport.class);
                    NearbyPets.this.startActivity(view_lost_report);
                    Log.v("Lost", "Starting lost report activity");
                } else if (tabStatus == 1) {
                    String selectedItem = spotteditemname[+position];
                    Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                    Intent view_found_report = new Intent(NearbyPets.this, ViewFoundReport.class);
                    NearbyPets.this.startActivity(view_found_report);
                    Log.v("Found", "Starting found report activity");
                }


            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_nearby_pets;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NearbyPets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://petlocator.petlocator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NearbyPets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://petlocator.petlocator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /* Handles the popup for the Filter button */
    public void displayFilterPopup() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.nearby_pets_filter, null);
        final CheckBox cbDog = (CheckBox) alertLayout.findViewById(R.id.cb_Dog);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Filter");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Filter settings confirmed", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class LostFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public LostFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static LostFragment newInstance(int sectionNumber) {
            LostFragment fragment = new LostFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        /* This really doesn't work right now! */
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.nearby_pets_tabs, container, false);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 0:
                    Log.v("Tab switch", "Switching to Lost Pet Tab");
                    NearbyPets.tabStatus = 0;
                    Log.v("Tab Status", Integer.toString(NearbyPets.tabStatus));
                    //NearbyPets.adapter.clear();
                    //adapter.updateData(NearbyPets.lostitemnamelist, NearbyPets.lostimgidlist);

                    break;
                case 1:
                    Log.v("Tab switch", "switching tabs to the Found tab");
                    NearbyPets.tabStatus = 1;
                    Log.v("Tab Status", Integer.toString(NearbyPets.tabStatus));
                    //NearbyPets.adapter.clear();
                    //adapter.updateData(NearbyPets.spotteditemnamelist, NearbyPets.spottedimgidlist);
                    break;
                default:
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return LostFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LOST PETS";
                case 1:
                    return "SPOTTED PETS";
            }
            return null;
        }
    }
}
