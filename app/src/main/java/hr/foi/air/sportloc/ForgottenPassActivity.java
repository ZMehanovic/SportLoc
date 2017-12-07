package hr.foi.air.sportloc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.data.beans.UserBean;
import hr.foi.air.sportloc.helper.UIHelperActivity;
import hr.foi.air.webservice.WebServiceCaller;

public class ForgottenPassActivity extends UIHelperActivity {
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
        webServiceCaller.CallWebService(userBean, type, getApplicationContext());
    }
}
