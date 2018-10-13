package hr.foi.air.sportloc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Typeface tfRoboto;
    Typeface tfRobotoBold;
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;
    TextView tvForgPass;
    TextView tvRegister;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        tfRoboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        tfRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvForgPass = (TextView) findViewById(R.id.tvForgPass);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        txtUsername.setTypeface(tfRoboto);
        txtPassword.setTypeface(tfRoboto);
        btnLogin.setTypeface(tfRobotoBold);
        tvForgPass.setTypeface(tfRoboto);
        tvRegister.setTypeface(tfRoboto);
        intent = getIntent();
    }
}
