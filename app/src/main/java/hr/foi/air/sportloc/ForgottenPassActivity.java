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

public class ForgottenPassActivity extends UIHelperActivity {
    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindViews({R.id.btnSend})
    List<View> excludedViews;

    @BindView(R.id.layout)
    View layout;

    public static final String EXTRA_MESSAGE = "hr.foi.air.sportloc.FORGOTTEN_PASS_SUCCESSFUL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forg_pass);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        setupUI(layout, excludedViews, ForgottenPassActivity.this);
    }

    @OnClick(R.id.btnSend)
    public void resetPasswordListener(View view) {
        hideSoftKeyboard(ForgottenPassActivity.this);
        changeFocus(null,true);
        String email = txtEmail.getText().toString();
        String errorMsg = getString(R.string.toast_empty_email);
        if(checkFieldsEmpty(errorMsg, null, email)) {
            resetPassword(email);
        }
    }

    public void resetPassword(String email) {
        String type = "resetPassword";
        UserBean userBean = new UserBean();
        userBean.setEmail(email);

        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.callWebService(userBean, type, getApplicationContext(), new WebServiceHandler() {
            @Override
            public void onDataArrived(Object result) {
                boolean answer = (boolean) result;
                String message = getString(R.string.toast_forg_pass_fail);
                if(answer) {
                    message = getString(R.string.toast_forg_pass_success);
                    Intent intent = new Intent(ForgottenPassActivity.this, LoginActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
