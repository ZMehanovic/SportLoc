package hr.foi.air.sportloc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.data.User;
import hr.foi.air.sportloc.helper.UIHelperActivity;
import hr.foi.air.webservice.WebServiceCaller;

public class LoginActivity extends UIHelperActivity {
    @BindView(R.id.txtUsername)
    EditText txtUsername;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindViews({R.id.btnLogin, R.id.tvForgPass, R.id.tvRegister})
    List<View> excludedViews;

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
        setupUI(layout, excludedViews, LoginActivity.this);
    }

    @OnClick(R.id.btnLogin)
    public void loginUserListener(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(getFocusThief());
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String errorMsg = getString(R.string.toast_empty_user_pass);
        if(checkFieldsEmpty(errorMsg, password, username)) {
            loginUser(username, password);
        }
    }

    public void loginUser(String username, String password) {
        String type = "login";
        User user = new User();
        user.setLoginData(username, password);

        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.CallWebService(user, type, getApplicationContext());
    }

    @OnClick(R.id.tvForgPass)
    public void openForgPassActivity(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(getFocusThief());
        Intent intent = new Intent(LoginActivity.this, ForgPassActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvRegister)
    public void openRegistrationActivity(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(getFocusThief());
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
