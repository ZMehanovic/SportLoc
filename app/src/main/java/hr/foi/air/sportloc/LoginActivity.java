package hr.foi.air.sportloc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.data.beans.UserBean;
import hr.foi.air.sportloc.helper.UIHelperActivity;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class LoginActivity extends UIHelperActivity {
    @BindView(R.id.txtUsername)
    EditText txtUsername;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindViews({R.id.btnLogin, R.id.tvForgPass, R.id.tvRegister})
    List<View> excludedViews;

    @BindView(R.id.layout)
    View layout;

    public static final String EXTRA_MESSAGE = "hr.foi.air.sportloc.LOGIN_SUCCESSFUL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        setupUI(layout, excludedViews, LoginActivity.this);

        Intent intent = getIntent();
        String registrationMessage = intent.getStringExtra(RegistrationActivity.EXTRA_MESSAGE);
        String forgottenPassMessage = intent.getStringExtra(ForgottenPassActivity.EXTRA_MESSAGE);
        if(registrationMessage != null && !registrationMessage.isEmpty()) {
            Toast.makeText(getApplicationContext(), registrationMessage, Toast.LENGTH_LONG).show();
        }
        else if(forgottenPassMessage != null && !forgottenPassMessage.isEmpty()) {
            Toast.makeText(getApplicationContext(), forgottenPassMessage, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btnLogin)
    public void loginUserListener(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(null, true);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String errorMsg = getString(R.string.toast_empty_user_pass);
        if(checkFieldsEmpty(errorMsg, password, username)) {
            loginUser(username, password);
        }
    }

    public void loginUser(String username, String password) {
        String type = "login";
        UserBean userBean = new UserBean();
        userBean.setLoginData(username, password);

        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.callWebService(userBean, type, getApplicationContext(), new WebServiceHandler() {
            @Override
            public void onDataArrived(Object result) {
                boolean answer = (boolean) result;
                String message = getString(R.string.toast_login_fail);
                if(answer) {
                    message = getString(R.string.toast_login_success);
                    Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick(R.id.tvForgPass)
    public void openForgPassActivity(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(null, true);
        Intent intent = new Intent(LoginActivity.this, ForgottenPassActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvRegister)
    public void openRegistrationActivity(View view) {
        hideSoftKeyboard(LoginActivity.this);
        changeFocus(null, true);
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
