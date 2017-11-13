package hr.foi.air.sportloc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 12.11.2017..
 */

public class RegisterUserResponse {
    @SerializedName("registrationSuccessful")
    private boolean registrationSuccessful;

    public RegisterUserResponse(boolean registrationSuccessful) {
        this.registrationSuccessful = registrationSuccessful;
    }

    public boolean getRegistrationSuccessful() {
        return registrationSuccessful;
    }
}
