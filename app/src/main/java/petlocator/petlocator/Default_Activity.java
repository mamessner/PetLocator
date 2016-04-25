package petlocator.petlocator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Default_Activity extends AppCompatActivity {

    String[] menuOptions;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        Drawable menuButton = ResourcesCompat.getDrawable(getResources(),
                R.drawable.menu_button, null);
        actionBar.setNavigationIcon(menuButton);
        actionBar.setTitle("Lost and Found Pets");
        setSupportActionBar(actionBar);

        /* The entire section below involving menus and action bars can (and should) be used
           in all activities. */
        menuOptions = getResources().getStringArray(R.array.menu_options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.left_drawer);
        setUpNavigationView();
        setUpDrawerLayout();
    }

    /**
     * Override this in each subclass and replace with appropriate layout.
     * @return the activity's layout
     */
    protected int getLayout() {
        return R.layout.activity_default_;
    }

    /**
     * Set up the listener for the left drawer.
     */
    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Clicked home", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(Default_Activity.this, HomePage.class);
                        return true;
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Clicked profile", Toast.LENGTH_SHORT).show();
                        Intent profileIntent = new Intent(Default_Activity.this, UserProfile.class);
                        startActivity(profileIntent);
                        return true;
                    case R.id.add_missing:
                        Toast.makeText(getApplicationContext(), "Clicked add missing", Toast.LENGTH_SHORT).show();
                        Intent reportLostIntent = new Intent(Default_Activity.this, ReportLostPet.class);
                        startActivity(reportLostIntent);
                        return true;
                    case R.id.add_found:
                        Toast.makeText(getApplicationContext(), "Clicked add found", Toast.LENGTH_SHORT).show();
                        Intent reportFoundIntent = new Intent(Default_Activity.this, ReportLostPet.class);
                        startActivity(reportFoundIntent);
                        return true;
                    case R.id.nearby:
                        Toast.makeText(getApplicationContext(), "Clicked nearby", Toast.LENGTH_SHORT).show();
                        Intent nearbyIntent = new Intent(Default_Activity.this, NearbyPets.class);
                        startActivity(nearbyIntent);
                        return true;
                    case R.id.sign_out:
                        // TODO: do something
                        Toast.makeText(getApplicationContext(), "Clicked sign out", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        // TODO: do something
                        return false;
                }
            }
        });
    }

    /** Set up toggle for the drawer. */
    private void setUpDrawerLayout() {
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                actionBar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
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
