package petlocator.petlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Sign_Up extends Default_Activity {

    final Button sign_Up_Button = (Button) findViewById(R.id.sign_up_button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        sign_Up_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent signUpIntent = new Intent(Sign_Up.this, SignIn.class);
                Sign_Up.this.startActivity(signUpIntent);
            }
        });

    }

}
