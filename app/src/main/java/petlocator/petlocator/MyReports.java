package petlocator.petlocator;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;

public class MyReports extends Default_Activity {

    public DrawerLayout drawerLayout;
    private Toolbar actionBar;
    public CustomListAdapter reportAdapter;
    private static final String LOG_TAG = "MyReports";

    public String[] reportitemname = {
            "LOST",
            "LOST",
            "SPOTTED",
            "LOST",
            "SPOTTED",
            "SPOTTED",
            "SPOTTED",
            "SPOTTED",
            "LOST",
            "LOST",
            "SPOTTED",
            "LOST",
            "SPOTTED",
            "SPOTTED",
            "SPOTTED",
            "SPOTTED"
    };
    ArrayList<String> reportitemnamelist = new ArrayList<String>(Arrays.asList(reportitemname));

    public Integer[] reportimgid= {
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.dog
    };
    ArrayList<Integer> reportimgidlist = new ArrayList<Integer>(Arrays.asList(reportimgid));

    public static String[] reportitemdescription = {
            "Fido - Greyhound Terrier - 9/10/2015",
            "Jeeves - Alaskan Wallabee - 6/4/2015",
            "Lingus - Golden Labradoodle - 5/3/2015",
            "Lil Doogie - Short-hair Cat - 5/2/2014",
            "Fido - Greyhound Terrier - 9/10/2015",
            "Jeeves - Alaskan Wallabee - 6/4/2015",
            "Lingus - Golden Labradoodle - 5/3/2015",
            "Lil Doogie - Short-hair Cat - 5/2/2014",
            "Fido - Greyhound Terrier - 9/10/2015",
            "Jeeves - Alaskan Wallabee - 6/4/2015",
            "Lingus - Golden Labradoodle - 5/3/2015",
            "Lil Doogie - Short-hair Cat - 5/2/2014",
            "Fido - Greyhound Terrier - 9/10/2015",
            "Jeeves - Alaskan Wallabee - 6/4/2015",
            "Lingus - Golden Labradoodle - 5/3/2015",
            "Lil Doogie - Short-hair Cat - 5/2/2014"
    };
    static ArrayList<String> reportitemdescriptionlist = new ArrayList<String>(Arrays.asList(reportitemdescription));


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("My Reports");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        reportAdapter = new CustomListAdapter(this, reportitemnamelist, reportimgidlist, reportitemdescriptionlist);
        ListView list = (ListView) findViewById(R.id.list_my_reports);
       list.setAdapter(reportAdapter);

        assert list != null;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                String selectedItem = reportitemname[+position];
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "Starting generic lost report activity");
            }
        });


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_reports;
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
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyReports Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://petlocator.petlocator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyReports Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://petlocator.petlocator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
