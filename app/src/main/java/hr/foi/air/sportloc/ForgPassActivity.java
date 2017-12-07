package hr.foi.air.sportloc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.data.User;
import hr.foi.air.sportloc.helper.UIHelperActivity;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class ForgPassActivity extends UIHelperActivity {
    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindViews({R.id.btnSend})
    List<View> excludedViews;

    @BindView(R.id.layout)
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forg_pass);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        setupUI(layout, excludedViews, ForgPassActivity.this);
    }

    @OnClick(R.id.btnSend)
    public void resetPasswordListener(View view) {
        hideSoftKeyboard(ForgPassActivity.this);
        changeFocus(getFocusThief());
        String email = txtEmail.getText().toString();
        String errorMsg = getString(R.string.toast_empty_email);
        if(checkFieldsEmpty(errorMsg, null, email)) {
            resetPassword(email);
        }
    }

    public void resetPassword(String email) {
        String type = "resetPassword";
        User user = new User();
        user.setEmail(email);

        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.callWebService(user, type, getApplicationContext(), new WebServiceHandler() {
            @Override
            public void onDataArrived(Object result) {
                boolean answer = (boolean) result;
                String message = getString(R.string.toast_forg_pass_fail);
                if(answer) {
                    message = getString(R.string.toast_forg_pass_success);
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
