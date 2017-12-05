package hr.foi.air.webservice.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 30.11.2017..
 */

public class WebServiceResponse {
    @SerializedName("loginSuccessful")
    private boolean loginSuccessful;

    @SerializedName("registrationSuccessful")
    private boolean registrationSuccessful;

    public WebServiceResponse(boolean loginSuccessful, boolean registrationSuccessful) {
        this.loginSuccessful = loginSuccessful;
        this.registrationSuccessful = registrationSuccessful;
    }

    public boolean getLoginSuccessful() {
        return loginSuccessful;
    }
    public boolean getRegistrationSuccessful() {
        return registrationSuccessful;
    }
}
