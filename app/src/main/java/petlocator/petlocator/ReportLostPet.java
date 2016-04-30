package petlocator.petlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class ReportLostPet extends Default_Activity {

    public DrawerLayout drawerLayout;
    private Toolbar actionBar;
    private MapView mapView;
    private GoogleMap map;
    private final String LOG_TAG = "ReportLostPet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("Report a Lost Pet");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAd   apter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animal_array, R.layout.timspinner);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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
        MapsInitializer.initialize(this.getApplicationContext());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.269, -76.7103), 10);
        map.animateCamera(cameraUpdate);

        // Set up buttons
        Button submitButton = (Button) findViewById(R.id.submit_button);
        Button browseButton = (Button) findViewById(R.id.browse_button);
        assert submitButton != null;
        assert browseButton != null;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked submit", Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "Going to a generic lost pet report");
                Intent viewLostIntent = new Intent(ReportLostPet.this, ViewLostReport.class);
                startActivity(viewLostIntent);
            }
        });
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked browse", Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "Browse feature not implemented yet");
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_report_lost_pet;
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
