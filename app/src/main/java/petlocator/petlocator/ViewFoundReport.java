package petlocator.petlocator;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("View Found Report");
        final String TAG = "VIEW FOUND REPORT";

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
                Toast.makeText(getApplicationContext(), "Clicked edit", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Moving to report edit");
                Intent reportFoundPetIntent = new Intent(ViewFoundReport.this, ReportFoundPet.class);
                ViewFoundReport.this.startActivity(reportFoundPetIntent);
            }
        });

        //Set the delete button to spawn an alert box on click
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "Opening alert");
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
                                Log.v(TAG, "Delete approved");
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
                                Log.v(TAG, "Delete cancelled");
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
