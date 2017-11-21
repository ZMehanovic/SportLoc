package hr.foi.air.webservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 8.11.2017..
 */

public class LoginUserResponse {
    @SerializedName("loginSuccessful")
    private boolean loginSuccessful;

    public LoginUserResponse(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public boolean getLoginSuccessful() {
        return loginSuccessful;
    }
}
