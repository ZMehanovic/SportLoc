package hr.foi.air.sportloc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 8.11.2017..
 */

public class LoginUserResponse {
    @SerializedName("loginSuccessful")
    private String loginSuccessful;

    public LoginUserResponse(String loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public String getLoginSuccessful() {
        return loginSuccessful;
    }
}
