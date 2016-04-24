package petlocator.petlocator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewFoundReport extends AppCompatActivity {

    private String[] menuOptions;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_found_report);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        Drawable menuButton = ResourcesCompat.getDrawable(getResources(),
                R.drawable.menu_button, null);
        actionBar.setNavigationIcon(menuButton);
        actionBar.setTitle("View Found Report");
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
