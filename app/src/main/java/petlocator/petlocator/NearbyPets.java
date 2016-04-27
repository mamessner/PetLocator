package petlocator.petlocator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class NearbyPets extends Default_Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String[] menuOptions;
    public DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    /* These are the dummy entries in the pet list */
    String[] itemname ={
            "Boots",
            "Labby",
            "Jeeves",
            "Splingdo",
            "Flubgus",
            "Shmort",
            "Lil Doogie",
            "Sarp"
    };

    Integer[] imgid={
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
    };

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_pets);

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        Drawable menuButton = ResourcesCompat.getDrawable(getResources(),
                R.drawable.menu_button, null);
        actionBar.setNavigationIcon(menuButton);
        actionBar.setTitle("Nearby Pets");
        setSupportActionBar(actionBar);

        /*
        Assigning view variables to thier respective view in xml
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
        setSupportActionBar(actionBar);

        /*
        TabLayout.newTab() method creates a tab view, Now a Tab view is not the view
        which is below the tabs, its the tab itself.
         */

        final TabLayout.Tab lost = tabLayout.newTab();
        final TabLayout.Tab spotted = tabLayout.newTab();

        /*
        Setting Title text for our tabs respectively
         */

        lost.setText("Lost");
        spotted.setText("Spotted");

        /*
        Adding the tab view to our tablayout at appropriate positions
        As I want home at first position I am passing home and 0 as argument to
        the tablayout and like wise for other tabs as well
         */
        tabLayout.addTab(lost, 0);
        tabLayout.addTab(spotted, 1);

        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indicator below the tabs
         */

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.drawable.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        /* The entire section below involving menus and action bars can (and should) be used
           in all activities. */
        menuOptions = getResources().getStringArray(R.array.menu_options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.left_drawer);
        setUpNavigationView();
        setUpDrawerLayout();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.lost_spotted_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /* initializes the seekbar which determines radius of search */
        SeekBar distance_seekbar = (SeekBar) findViewById(R.id.distance_seekbar);
        final TextView distance_text = (TextView) findViewById(R.id.distance_text);

        distance_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int distance = 5;

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
                distance_text.setText("Covered: " + distance + "/" + seekBar.getMax());
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        /* Handles displaying custom entries on an image+text ListView */
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        ListView list = (ListView) findViewById(R.id.nearby_pet_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String selectedItem= itemname[+position];
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                Intent view_lost_report = new Intent(NearbyPets.this, ViewLostReport.class);
                NearbyPets.this.startActivity(view_lost_report);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
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
            return true;
        }
        if (id == R.id.action_inbox) {
            Intent inboxIntent = new Intent (this, Inbox.class);
            startActivity(inboxIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    /**
//     * Set up the listener for the left drawer.
//     */
//    private void setUpNavigationView() {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//
//            // This method will trigger on item Click of navigation menu
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//
//                //Checking if the item is in checked state or not, if not make it in checked state
//                if(menuItem.isChecked()) menuItem.setChecked(false);
//                else menuItem.setChecked(true);
//
//                //Closing drawer on item click
//                drawerLayout.closeDrawers();
//
//                //Check to see which item was being clicked and perform appropriate action
//                switch (menuItem.getItemId()){
//                    case R.id.home:
//                        Toast.makeText(getApplicationContext(), "Clicked home", Toast.LENGTH_SHORT).show();
//                        Intent homeIntent = new Intent(NearbyPets.this, HomePage.class);
//                        startActivity(homeIntent);
//                        return true;
//                    case R.id.profile:
//                        Toast.makeText(getApplicationContext(), "Clicked profile", Toast.LENGTH_SHORT).show();
//                        Intent profileIntent = new Intent(NearbyPets.this, UserProfile.class);
//                        startActivity(profileIntent);
//                        return true;
//                    case R.id.add_missing:
//                        Toast.makeText(getApplicationContext(), "Clicked add missing", Toast.LENGTH_SHORT).show();
//                        Intent reportLostIntent = new Intent(NearbyPets.this, ReportLostPet.class);
//                        startActivity(reportLostIntent);
//                        return true;
//                    case R.id.add_found:
//                        Toast.makeText(getApplicationContext(), "Clicked add found", Toast.LENGTH_SHORT).show();
//                        Intent reportFoundIntent = new Intent(NearbyPets.this, ReportFoundPet.class);
//                        startActivity(reportFoundIntent);
//                        return true;
//                    case R.id.nearby:
//                        // no need to do anything here
//                        Toast.makeText(getApplicationContext(), "Clicked nearby", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.sign_out:
//                        // TODO: do something
//                        Toast.makeText(getApplicationContext(), "Clicked sign out", Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        // TODO: do something
//                        return false;
//                }
//            }
//        });
//    }
//
//    /** Set up toggle for the drawer. */
//    private void setUpDrawerLayout() {
//        // Initializing Drawer Layout and ActionBarToggle
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
//                actionBar,R.string.drawer_open, R.string.drawer_close){
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
//
//                super.onDrawerOpened(drawerView);
//            }
//        };
//
//        //Setting the actionbarToggle to drawer layout
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//    }

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
            TableRow firstRow = (TableRow) rootView.findViewById(R.id.row1);
            TableRow secondRow = (TableRow) rootView.findViewById(R.id.row2);
            TextView row1Col1 = (TextView) rootView.findViewById(R.id.row1_col1);
            TextView row1Col2 = (TextView) rootView.findViewById(R.id.row1_col2);
            TextView row1Col3 = (TextView) rootView.findViewById(R.id.row1_col3);
            TextView row2Col1 = (TextView) rootView.findViewById(R.id.row2_col1);
            TextView row2Col2 = (TextView) rootView.findViewById(R.id.row2_col2);
            TextView row2Col3 = (TextView) rootView.findViewById(R.id.row2_col3);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    /*
                    row1Col1.setText("Fido");
                    row1Col2.setText("Greyhound");
                    row1Col3.setText("0.1 mi");
                    row2Col1.setText("Boots");
                    row2Col2.setText("Calico shorthair");
                    row2Col3.setText("0.3 mi");
                    firstRow.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Intent fidoIntent = new Intent(getActivity(), ViewLostReport.class);
                            // TODO: log something
                            startActivity(fidoIntent);
                            return true;
                        }
                    });
                    secondRow.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // TODO: log something
                            Intent fidoIntent = new Intent(getActivity(), ViewLostReport.class);
                            startActivity(fidoIntent);
                            return true;
                        }
                    }); */
                    break;
                case 2: /*
                    row1Col1.setText("Iko");
                    row1Col2.setText("Black lab");
                    row1Col3.setText("1.17 mi");
                    row2Col1.setText("Cinnamon");
                    row2Col2.setText("Torbie");
                    row2Col3.setText("1.75 mi");
                    firstRow.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Intent firstIntent = new Intent(getActivity(), ViewFoundReport.class);
                            // TODO: log something
                            startActivity(firstIntent);
                            return true;
                        }
                    });
                    secondRow.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // TODO: log something
                            Intent firstIntent = new Intent(getActivity(), ViewFoundReport.class);
                            startActivity(firstIntent);
                            return true;
                        }
                    }); */
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
            return LostFragment.newInstance(position + 1);
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

    /* The click listener for ListView in the navigation drawer. */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // TODO: do something
    }
}
