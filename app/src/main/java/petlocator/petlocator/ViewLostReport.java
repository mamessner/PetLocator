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
import android.view.Menu;
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

    private Toolbar actionBar;
    private MapView mapView;
    private GoogleMap map;
    private ListView list;
    private static final String LOG_TAG = "ViewLostReport";
    private String[] fieldHeaders = {"Name", "Color", "Breed", "Notes"};
    private String[] fieldDescriptions = {"Jeeves", "Black", "Black lab",
            "Jeeves is a sweet golden with a distinctive white spot in the center of the backs of " +
            "both ears. He loves people and will probably approach you if you find him!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("View Lost Report");

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
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.269, -76.7103), 10);
        map.animateCamera(cameraUpdate);

        // Set up the ListView
        // Set up the report information
        PetReportListAdapter adapter = new PetReportListAdapter(this, fieldHeaders, fieldDescriptions);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //Get the edit and delete buttons
        final Button edit_Button = (Button) findViewById(R.id.edit_button);
        final Button delete_Button = (Button) findViewById(R.id.button2);
        assert edit_Button != null;
        assert delete_Button != null;

        //Set the edit button to reroute to the make report form on click
        edit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked Edit", Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "Moving to Lost Report edit");
                Log.v(LOG_TAG, "Data from current report would normally be transferred.");
                Intent reportLostPetIntent = new Intent(ViewLostReport.this, ReportLostPet.class);
                ViewLostReport.this.startActivity(reportLostPetIntent);
            }
        });

        //Set the delete button to spawn an alert box on click
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(LOG_TAG, "Opening alert");
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
                                Log.v(LOG_TAG, "Close approved");
                                Toast.makeText(getApplicationContext(), "Close approved", Toast.LENGTH_SHORT).show();
                                Intent userProfileIntent = new Intent(ViewLostReport.this, UserProfile.class);
                                ViewLostReport.this.startActivity(userProfileIntent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, cancel the dialog and do nothing
                                Log.v(LOG_TAG, "Close rejected");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Override this in each subclass and replace with appropriate layout.
     * @return the activity's layout
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_view_lost_report;
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
