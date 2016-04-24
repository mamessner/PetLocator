package petlocator.petlocator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String[] menuOptions;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private Button lostButton;
    private Button foundButton;
    private ListView lostResults;
    TableRow fidoRow;
    TableRow bootsRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        Drawable menuButton = ResourcesCompat.getDrawable(getResources(),
                R.drawable.menu_button, null);
        actionBar.setNavigationIcon(menuButton);
        actionBar.setTitle("Lost Pets");
        setSupportActionBar(actionBar);

        /* The entire section below involving menus and action bars can (and should) be used
           in all activities. */
        menuOptions = getResources().getStringArray(R.array.menu_options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, menuOptions));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBar,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called once the drawer is closed. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getTitle());
            }

            /** Called when the drawer is opened. */
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                getSupportActionBar().setTitle(getTitle());
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        // Set up buttons
        lostButton = (Button) findViewById(R.id.lost_button);
        foundButton = (Button) findViewById(R.id.found_button);
        lostButton.setPressed(true);
        foundButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                foundButton.setPressed(true);
                lostButton.setPressed(false);
                Intent foundPetsIntent = new Intent(MainActivity.this, FoundPetsActivity.class);
                startActivity(foundPetsIntent);
                return true;
            }
        });

        // Set up results list
        fidoRow = (TableRow) findViewById(R.id.row_fido);
        bootsRow = (TableRow) findViewById(R.id.row_boots);
        fidoRow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent fidoIntent = new Intent(MainActivity.this, ViewLostReport.class);
                // TODO: log something
                startActivity(fidoIntent);
                return true;
            }
        });
        bootsRow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO: log something
                Intent fidoIntent = new Intent(MainActivity.this, ViewLostReport.class);
                startActivity(fidoIntent);
                return true;
            }
        });


        // Set up map
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            // TODO: do something
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
