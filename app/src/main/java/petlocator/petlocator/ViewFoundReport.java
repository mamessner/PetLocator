package petlocator.petlocator;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class ViewFoundReport extends Default_Activity{

    private MapView mapView;
    private GoogleMap map;
    private ListView list;
    private String[] fieldHeaders = {"Name", "Color", "Breed", "Notes"};
    private String[] fieldDescriptions = {"Unknown", "Golden", "Golden Retriever",
            "He didn't have a collar but he came right up to me."};
    private static final String LOG_TAG = "ViewFoundReport";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("View Found Report");

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
        MapsInitializer.initialize(ViewFoundReport.this);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.269, -76.7103), 10);
        map.animateCamera(cameraUpdate);

        // Set up the report information
        PetReportListAdapter adapter = new PetReportListAdapter(this, fieldHeaders, fieldDescriptions);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //Get the edit and delete buttons
        final Button editButton = (Button) findViewById(R.id.edit_button);
        final Button delete_Button = (Button) findViewById(R.id.button2);
        assert editButton != null;
        assert delete_Button != null;

        //Set the edit button to reroute to the make report form on click
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked edit", Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "Moving to report found pet page.");
                Log.v(LOG_TAG, "Data from current report would normally be automatically entered.");
                Intent reportFoundPetIntent = new Intent(ViewFoundReport.this, ReportFoundPet.class);
                ViewFoundReport.this.startActivity(reportFoundPetIntent);
            }
        });

        //Set the delete button to spawn an alert box on click
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(LOG_TAG, "Opening alert");
                Toast.makeText(getApplicationContext(), "Clicked delete", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFoundReport.this);


                //set title
                alertDialogBuilder.setTitle("Delete this report?");

                //set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to delete.")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Approved deletion", Toast.LENGTH_SHORT).show();
                                Log.v(LOG_TAG, "Delete approved");
                                //if this button is clicked, reroute to user profile
                                Intent userProfileIntent = new Intent(ViewFoundReport.this, UserProfile.class);
                                ViewFoundReport.this.startActivity(userProfileIntent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, cancel the dialog and do nothing
                                Toast.makeText(getApplicationContext(), "Cancelled deletion", Toast.LENGTH_SHORT).show();
                                Log.v(LOG_TAG, "Delete cancelled");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        return R.layout.activity_view_found_report;
    }

}
