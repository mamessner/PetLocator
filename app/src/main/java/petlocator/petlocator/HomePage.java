package petlocator.petlocator;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

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

    // Information for lost pets
    public static String[] lostNames = {"Boots", "Labby", "Jeeves", "Splingdo", "Flubgus",
            "Shmort", "Lil Doogie", "Sarp", "Boots", "Labby", "Jeeves", "Splingdo", "Flubgus",
            "Shmort", "Lil Doogie", "Sarp"};
    public static ArrayList<String> lostNamesList = new ArrayList<String>(Arrays.asList(lostNames));
    public static String[] lostBreeds = {"Calico short-hair", "Golden labradoodle", "Silver fox",
            "Black mamba", "Purple goose", "White poodle", "Black sprinkle", "Pink moose",
            "Calico short-hair", "Golden labradoodle", "Silver fox", "Black mamba",
            "Purple goose", "White poodle", "Black sprinkle", "Pink moose"};
    public static ArrayList<String> lostBreedsList = new ArrayList<String>(Arrays.asList(lostBreeds));
    public static Integer[] lostImages = {R.drawable.cat, R.drawable.dog, R.drawable.cat,
            R.drawable.dog, R.drawable.cat, R.drawable.dog, R.drawable.cat, R.drawable.dog,
            R.drawable.cat, R.drawable.dog, R.drawable.cat, R.drawable.dog, R.drawable.cat,
            R.drawable.dog, R.drawable.cat, R.drawable.dog};
    public static ArrayList<Integer> lostImagesList = new ArrayList<Integer>(Arrays.asList(lostImages));

    // Information for found pets
    public static String[] foundNames = {"Shoes", "Poodle", "Jives", "Flingdo", "Glubfus",
            "Shmoop", "Lil CatCat", "Prust"};
    public static ArrayList<String> foundNamesList = new ArrayList<String>(Arrays.asList(foundNames));
    public static String[] foundBreeds = {"Domestic shorthair", "Poodle", "Black lab",
            "Calico", "Border collie", "Border collie", "Gray tabby", "Whippet"};
    public static ArrayList<String> foundBreedsList = new ArrayList<String>(Arrays.asList(foundBreeds));
    public static Integer[] foundImages = {R.drawable.cat, R.drawable.dog, R.drawable.dog,
            R.drawable.cat, R.drawable.dog, R.drawable.dog, R.drawable.cat, R.drawable.dog};
    public static ArrayList<Integer> foundImagesList = new ArrayList<Integer>(Arrays.asList(foundImages));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            ListView list = (ListView) rootView.findViewById(R.id.nearby_pet_list);
            CustomListAdapter adapter;
            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1: // Lost pets
                    adapter = new CustomListAdapter(getActivity(), lostNamesList, lostImagesList, lostBreedsList);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = lostNames[+position];
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Clicked " + selectedItem, Toast.LENGTH_SHORT).show();
                            Intent viewLostIntent = new Intent(getActivity(), ViewLostReport.class);
                            startActivity(viewLostIntent);
                        }
                    });
                    break;
                case 2: // Found pets
                    adapter = new CustomListAdapter(getActivity(), foundNamesList, foundImagesList, foundBreedsList);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = lostNames[+position];
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Clicked " + selectedItem, Toast.LENGTH_SHORT).show();
                            Intent viewFoundIntent = new Intent(getActivity(), ViewFoundReport.class);
                            startActivity(viewFoundIntent);
                        }
                    });
                    break;
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
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.269, -76.7103), 10);
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

//    protected class LostPetClickListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String selectedItem = lostNames[+position];
//            Toast.makeText(getApplicationContext(), "Clicked " + selectedItem, Toast.LENGTH_SHORT).show();
//            Intent viewLostIntent = new Intent(HomePage.this, ViewLostReport.class);
//            startActivity(viewLostIntent);
//        }
//    }
}
