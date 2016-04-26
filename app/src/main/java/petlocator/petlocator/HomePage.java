package petlocator.petlocator;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

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
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class HomePage extends Default_Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private MapView mapView;
        private GoogleMap map;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
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
                    });
                    break;
                case 2:
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
                    });
                default:
                    break;
            }

            // Gets the MapView from the XML layout and creates it
            mapView = (MapView) rootView.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);

            // Gets to GoogleMap from the MapView and does initialization stuff
            map = mapView.getMap();
            map.getUiSettings().setMyLocationButtonEnabled(false);
            try {
                map.setMyLocationEnabled(true);
            } catch (SecurityException e) {
                // TODO: do something
            }

            // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
            MapsInitializer.initialize(this.getActivity());

            // Updates the location and zoom of the MapView
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
            map.animateCamera(cameraUpdate);
            return rootView;
        }

        @Override
        public void onResume() {
            mapView.onResume();
            super.onResume();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
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
            return PlaceholderFragment.newInstance(position + 1);
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
                    return "FOUND PETS";
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
