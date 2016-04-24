package petlocator.petlocator;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * This is meant to be a listener for the left navigation drawer that can be shared across
 * activities. It requires access to the DrawerLayout from the parent view/activity.
 *
 * Ignore this class for now.
 */
public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener {

    private HomePage activity;
    private DrawerLayout drawerLayout;

    public NavigationListener() {

    }

    public NavigationListener(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
        System.out.println("in navigation listener drawerlayout constructor");
    }

    public NavigationListener(HomePage activity) {
        this.activity = activity;
        this.drawerLayout = this.activity.drawerLayout;
    }

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
                // TODO: do something
                Toast.makeText(activity.getApplicationContext(), "Clicked home", Toast.LENGTH_SHORT);
                return true;
            case R.id.profile:
                //TODO: do something
                return true;
            case R.id.add_missing:
                // TODO: do something
                return true;
            case R.id.add_found:
                // TODO: do something
                return true;
            case R.id.nearby:
                //TODO: do something
                return true;
            case R.id.sign_out:
                // TODO: do something
                return true;
            default:
                // TODO: do something
                return false;
        }
    }
}
