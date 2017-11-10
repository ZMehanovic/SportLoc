package hr.foi.air.sportloc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvLogin;
    private Button btnAccount;
    private Button btnFacebook;
    private Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initializeValues();
        createDesign();
    }

    public void initializeValues() {
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        btnAccount = (Button) findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(openLoginActivity);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);
    }

    public void createDesign() {
        Typeface tfRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        tvLogin.setTypeface(tfRobotoBold);
        btnAccount.setTypeface(tfRobotoBold);
        btnFacebook.setTypeface(tfRobotoBold);
        btnGoogle.setTypeface(tfRobotoBold);
    }

    View.OnClickListener openLoginActivity = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };
}
