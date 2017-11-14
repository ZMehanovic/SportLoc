package hr.foi.air.sportloc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.sportloc.model.LoginUserResponse;
import hr.foi.air.sportloc.rest.ApiClient;
import hr.foi.air.sportloc.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.txtUsername)
    EditText txtUsername;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.tvForgPass)
    TextView tvForgPass;

    @BindView(R.id.tvRegister)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        createDesign();
    }

    public void createDesign() {
        Typeface tfRoboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface tfRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        txtUsername.setTypeface(tfRoboto);
        txtPassword.setTypeface(tfRoboto);
        btnLogin.setTypeface(tfRobotoBold);
        tvForgPass.setTypeface(tfRoboto);
        tvRegister.setTypeface(tfRoboto);
    }

    @OnClick(R.id.btnLogin)
    public void loginUserListener(View view) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if(checkFieldsEmpty(username, password)) {
            loginUser(username, password);
        }
    }

    public boolean checkFieldsEmpty(String username, String password) {
        boolean success = false;
        String errorMsg = "";
        if(username.trim().isEmpty() && password.isEmpty()) {
            errorMsg = getString(R.string.toast_empty_user_pass);
        }
        else if(username.trim().isEmpty()) {
            errorMsg = getString(R.string.toast_empty_user);
        }
        else if(password.trim().isEmpty()) {
            errorMsg = getString(R.string.toast_empty_pass);
        }
        else {
            success = true;
        }
        if(!success) {
            Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
        }
        return success;
    }

    public void loginUser(String username, String password) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginUserResponse> call = apiService.getLoginUserInfo(username.trim(), password);
        call.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                boolean result = response.body().getLoginSuccessful();
                String message = getString(R.string.toast_login_fail);
                if(result) {
                    message = getString(R.string.toast_login_success);
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                String message = getString(R.string.toast_error);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.tvRegister)
    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
