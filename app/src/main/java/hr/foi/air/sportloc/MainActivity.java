package hr.foi.air.sportloc;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Typeface tfRoboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);
        Button btnAccount = (Button) findViewById(R.id.btnAccount);
        tvLogin.setTypeface(tfRoboto);
        btnAccount.setTypeface(tfRoboto);
    }
}
