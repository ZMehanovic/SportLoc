package hr.foi.air.sportloc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.data.registration.User;
import hr.foi.air.sportloc.helper.UIHelperActivity;
import hr.foi.air.webservice.model.RegisterUserResponse;
import hr.foi.air.webservice.rest.ApiClient;
import hr.foi.air.webservice.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends UIHelperActivity {
    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.txtSurname)
    EditText txtSurname;

    @BindView(R.id.txtUsername)
    EditText txtUsername;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtBirthday)
    EditText txtBirthday;

    @BindView(R.id.btnMale)
    Button btnMale;

    @BindView(R.id.btnFemale)
    Button btnFemale;

    @BindViews({R.id.btnMale, R.id.btnFemale, R.id.btnRegistration})
    List<View> excludedViews;

    @BindView(R.id.layout)
    View layout;

    private String genderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        genderSelected = "male";
        setupUI(layout, excludedViews, RegistrationActivity.this);
    }

    @OnClick(R.id.btnMale)
    public void selectMale(View view) {
        hideSoftKeyboard(RegistrationActivity.this);
        changeFocus(getFocusThief());
        if(genderSelected.equals("female")) {
            changeGender(btnMale, btnFemale);
            genderSelected = "male";
        }
    }

    @OnClick(R.id.btnFemale)
    public void selectFemale(View view) {
        hideSoftKeyboard(RegistrationActivity.this);
        changeFocus(getFocusThief());
        if(genderSelected.equals("male")) {
            changeGender(btnFemale, btnMale);
            genderSelected = "female";
        }
    }

    @OnClick(R.id.btnRegistration)
    public void registerUser(View view) {
        hideSoftKeyboard(RegistrationActivity.this);
        changeFocus(getFocusThief());
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String username = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String birthday = txtBirthday.getText().toString();
        //lista stringova
        if(checkFieldsEmpty(name, surname, username, email, password, birthday)) {
            registerUser(name, surname, username, email ,password, birthday);
        }
    }

    public void changeGender(Button btnFocusedGender, Button btnUnfocusedGender) {
        btnFocusedGender.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        btnFocusedGender.setBackgroundResource(R.drawable.btn_general_selected);
        btnUnfocusedGender.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        btnUnfocusedGender.setBackgroundResource(R.drawable.btn_general);
    }

    public boolean checkFieldsEmpty(String name, String surname, String username, String email, String password, String birthday) {
        boolean success = false;
        String errorMsg = "";
        if(name.trim().isEmpty() || surname.trim().isEmpty() || username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || birthday.trim().isEmpty()) {
            errorMsg = getString(R.string.toast_empty_registration);
        }
        else {
            success = true;
        }
        if(!success) {
            Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
        }
        return success;
    }

    public void registerUser(String name, String surname, String username, String email, String password, String birthday) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        User user = new User(name.trim(), surname.trim(), username.trim(), email.trim(), genderSelected, password.trim(), birthday.trim());

        Call<RegisterUserResponse> call = apiService.getRegisterUserInfo(user);
        call.enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {
                boolean result = response.body().getRegistrationSuccessful();
                String message = getString(R.string.toast_registration_fail);
                if(result) {
                    message = getString(R.string.toast_registration_success);
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                String message = getString(R.string.toast_error);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
