package hr.foi.air.sportloc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @BindView(R.id.btnAccount)
    Button btnAccount;

    @BindView(R.id.btnFacebook)
    Button btnFacebook;

    @BindView(R.id.btnGoogle)
    Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        createDesign();
    }

    public void createDesign() {
        Typeface tfRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        tvLogin.setTypeface(tfRobotoBold);
        btnAccount.setTypeface(tfRobotoBold);
        btnFacebook.setTypeface(tfRobotoBold);
        btnGoogle.setTypeface(tfRobotoBold);
    }

    @OnClick(R.id.btnAccount)
    public void openLoginActivity(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
