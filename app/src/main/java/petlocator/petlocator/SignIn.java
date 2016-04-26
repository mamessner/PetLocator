package petlocator.petlocator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SignIn extends Default_Activity {

    private String[] menuOptions;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
//        Drawable menuButton = ResourcesCompat.getDrawable(getResources(),
//                R.drawable.menu_button, null);
//        actionBar.setNavigationIcon(menuButton);
//        setSupportActionBar(actionBar);
         /* The entire section below involving menus and action bars can (and should) be used
           in all activities. */
        menuOptions = getResources().getStringArray(R.array.menu_options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.left_drawer);
        setUpNavigationView();
        setUpDrawerLayout();
        drawerLayout.addDrawerListener(drawerToggle);
        final Button sign_In_Button = (Button) findViewById(R.id.sign_in_button);
        assert sign_In_Button != null;
        sign_In_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked Sign In", Toast.LENGTH_SHORT).show();
                Log.v("Sign Up", "Sign in complete");
                Intent signInIntent = new Intent(SignIn.this, MainActivity.class);
                SignIn.this.startActivity(signInIntent);
            }
        });
    }
    protected int getLayout() {
        return R.layout.activity_sign_in;
    }
}

