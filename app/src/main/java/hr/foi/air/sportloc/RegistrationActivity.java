package hr.foi.air.sportloc;

import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hr.foi.air.sportloc.model.RegisterUserResponse;
import hr.foi.air.sportloc.rest.ApiClient;
import hr.foi.air.sportloc.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText txtName, txtSurname, txtUsername, txtEmail, txtPassword, txtBirthday;
    private Button btnMale, btnFemale, btnRegistration;
    private TextView tvGender;
    private String genderSelected;
    private Typeface tfRoboto, tfRobotoBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initializeValues();
        createDesign();
    }

    public void initializeValues() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText) findViewById(R.id.txtSurname);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtBirthday = (EditText) findViewById(R.id.txtBirthday);
        btnMale = (Button) findViewById(R.id.btnMale);
        btnMale.setOnClickListener(selectMale);
        btnFemale = (Button) findViewById(R.id.btnFemale);
        btnFemale.setOnClickListener(selectFemale);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(registerUser);
        tvGender = (TextView) findViewById(R.id.tvGender);
        genderSelected = "male";
        ConstraintLayout layoutParent = (ConstraintLayout) findViewById(R.id.layout_parent);
        hideKeyboardOnClick(layoutParent);
        ConstraintLayout layoutChild = (ConstraintLayout) findViewById(R.id.layout_child);
        hideKeyboardOnClick(layoutChild);
    }

    public void createDesign() {
        tfRoboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        tfRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        txtName.setTypeface(tfRoboto);
        txtSurname.setTypeface(tfRoboto);
        txtUsername.setTypeface(tfRoboto);
        txtEmail.setTypeface(tfRoboto);
        txtPassword.setTypeface(tfRoboto);
        txtBirthday.setTypeface(tfRoboto);
        btnMale.setTypeface(tfRobotoBold);
        btnFemale.setTypeface(tfRoboto);
        btnRegistration.setTypeface(tfRobotoBold);
        tvGender.setTypeface(tfRoboto);
    }

    View.OnClickListener selectMale = new View.OnClickListener() {
        public void onClick(View view) {
            if(genderSelected.equals("female")) {
                changeGender(btnMale, btnFemale);
                genderSelected = "male";
            }
        }
    };

    View.OnClickListener selectFemale = new View.OnClickListener() {
        public void onClick(View view) {
            if(genderSelected.equals("male")) {
                changeGender(btnFemale, btnMale);
                genderSelected = "female";
            }
        }
    };

    View.OnClickListener registerUser = new View.OnClickListener() {
        public void onClick(View view) {
            String name = txtName.getText().toString();
            String surname = txtSurname.getText().toString();
            String username = txtUsername.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            String birthday = txtBirthday.getText().toString();
            if(checkFieldsEmpty(name, surname, username, email, password, birthday)) {
                registerUser(name, surname, username, email ,password, birthday);
            }
        }
    };

    public void changeGender(Button btnFocusedGender, Button btnUnfocusedGender) {
        hideSoftKeyboard();
        btnUnfocusedGender.setFocusable(false);
        btnUnfocusedGender.setFocusableInTouchMode(false);
        btnFocusedGender.setTypeface(tfRobotoBold);
        btnFocusedGender.setBackgroundResource(R.drawable.btn_general_selected);
        btnFocusedGender.setFocusable(true);
        btnFocusedGender.setFocusableInTouchMode(true);
        btnFocusedGender.requestFocus();
        btnUnfocusedGender.setTypeface(tfRoboto);
        btnUnfocusedGender.setBackgroundResource(R.drawable.btn_general);
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void hideKeyboardOnClick(ConstraintLayout layout) {
        for(int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if(!(v instanceof EditText) && !(v instanceof Button)) {
                v.setOnClickListener(hideKeyboardListener);
            }
        }
    }

    View.OnClickListener hideKeyboardListener = new View.OnClickListener() {
        public void onClick(View view) {
            hideSoftKeyboard();
        }
    };

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

        Call<RegisterUserResponse> call = apiService.getRegisterUserInfo(name.trim(), surname.trim(), username.trim(), email.trim(), genderSelected, password.trim(), birthday.trim());
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
