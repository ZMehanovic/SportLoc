package hr.foi.air.sportloc.helper;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import hr.foi.air.sportloc.R;

/**
 * Created by Gabriel on 19.11.2017..
 */

public class UIHelperActivity extends AppCompatActivity {

    private View focusThief;
    private Activity currentActivity;

    public View getFocusThief() {
        return focusThief;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
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

    public void applyOnClickListener(View view, List<View> excludedViews) {
        if(!(view instanceof EditText || excludedViews.contains(view))) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard(currentActivity);
                    changeFocus(focusThief);
                }
            });
        }

        if(view instanceof ViewGroup) {
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                applyOnClickListener(innerView, excludedViews);
            }
        }
    }

    public void setupUI(View view, List<View> excludedViews, Activity currentActivity) {
        setCurrentActivity(currentActivity);
        LayoutInflater inflater = (LayoutInflater) currentActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View thiefLayout = inflater.inflate(R.layout.focus_thief, (ViewGroup) view, false);
        ((ViewGroup) view).addView(thiefLayout);
        focusThief = findViewById(R.id.focus_thief);
        applyOnClickListener(view, excludedViews);
    }

    public boolean checkFieldsEmpty(String errorMsg, String password, String... fieldsArray) {
        boolean success = true;
        if(password != null && password.isEmpty()) {
            success = false;
        }
        for(String field : fieldsArray) {
            if(field.trim().isEmpty()) {
                success = false;
            }
        }
        if(!success) {
            Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
        }
        return success;
    }
}
