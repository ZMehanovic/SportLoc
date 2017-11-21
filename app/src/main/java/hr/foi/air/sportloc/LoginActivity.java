package hr.foi.air.sportloc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.webservice.model.LoginUserResponse;
import hr.foi.air.webservice.rest.ApiClient;
import hr.foi.air.webservice.rest.ApiInterface;
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

    @BindView(R.id.tvRegister)
    TextView tvRegister;

    @BindView(R.id.layout)
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        setupUI(layout);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void changeFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public void setupUI(View view) {
        List<View> excludedViews = Arrays.asList((View)btnLogin, tvRegister);
        if(!(view instanceof EditText || excludedViews.contains(view))) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard(LoginActivity.this);
                    changeFocus(layout);
                }
            });
        }

        if(view instanceof ViewGroup) {
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @OnClick(R.id.btnLogin)
    public void loginUserListener(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(layout);
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
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(layout);
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
