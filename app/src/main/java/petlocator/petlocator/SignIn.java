package petlocator.petlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("Sign In");
        setSupportActionBar(actionBar);

        // Set up buttons
        final Button signInButton = (Button) findViewById(R.id.sign_in_button);
        final Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        assert signInButton != null;
        assert signUpButton != null;
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked Sign In", Toast.LENGTH_SHORT).show();
                Log.v("Sign In", "Sign in complete");
                Intent signInIntent = new Intent(SignIn.this, HomePage.class);
                SignIn.this.startActivity(signInIntent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked Sign Up", Toast.LENGTH_SHORT).show();
                Log.v("Sign In", "Going to sign up");
                Intent signUpIntent = new Intent(SignIn.this, Sign_Up.class);
                startActivity(signUpIntent);
            }
        });
    }
    protected int getLayout() {
        return R.layout.activity_sign_in;
    }
}

