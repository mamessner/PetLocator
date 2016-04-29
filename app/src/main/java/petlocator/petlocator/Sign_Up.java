package petlocator.petlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle("Sign Up");
        setSupportActionBar(actionBar);

        // Set up button
        final Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Clicked Sign Up", Toast.LENGTH_SHORT).show();
                Log.v("Sign Up", "Sign up complete");
                Intent signUpIntent = new Intent(Sign_Up.this, SignIn.class);
                Sign_Up.this.startActivity(signUpIntent);
            }
        });

    }

}
