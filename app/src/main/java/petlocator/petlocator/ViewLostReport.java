package petlocator.petlocator;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class ViewLostReport extends Default_Activity {

    private String[] menuOptions;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar actionBar;
    private ImageView imageView;
    private MapView mapView;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lost_report);

        final String TAG = "VIEW LOST REPORT";
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

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) findViewById(R.id.map);
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
        MapsInitializer.initialize(ViewLostReport.this);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);

        //Get the edit and delete buttons
        final Button edit_Button = (Button) findViewById(R.id.button);
        final Button delete_Button = (Button) findViewById(R.id.button2);
        assert edit_Button != null;
        assert delete_Button != null;

        //Set the edit button to reroute to the make report form on click
        edit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked Edit", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Moving to Lost Report edit");
                Intent reportLostPetIntent = new Intent(ViewLostReport.this, ReportLostPet.class);
                ViewLostReport.this.startActivity(reportLostPetIntent);
            }
        });

        //Set the delete button to spawn an alert box on click
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "Opening alert");
                Toast.makeText(getApplicationContext(), "Clicked close", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewLostReport.this);


                //set title
                alertDialogBuilder.setTitle("Delete this report?");

                //set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to delete")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, reroute to user profile
                                Log.v(TAG, "Close approved");
                                Toast.makeText(getApplicationContext(), "Close approved", Toast.LENGTH_SHORT).show();
                                Intent userProfileIntent = new Intent(ViewLostReport.this, UserProfile.class);
                                ViewLostReport.this.startActivity(userProfileIntent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, cancel the dialog and do nothing
                                Log.v(TAG, "Close rejected");
                                Toast.makeText(getApplicationContext(), "Close rejected", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                //create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                //show it
                alertDialog.show();
            }

        });

    }


    /**
     * Override this in each subclass and replace with appropriate layout.
     * @return the activity's layout
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_view_lost_report;
    }
}
