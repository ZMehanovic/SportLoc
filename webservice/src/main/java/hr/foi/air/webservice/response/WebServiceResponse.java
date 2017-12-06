package hr.foi.air.webservice.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 30.11.2017..
 */

public class WebServiceResponse {
    @SerializedName("success")
    private boolean success;

    public WebServiceResponse(boolean success) {
        this.success = success;
    }

    public boolean getLoginSuccessful() {
        return success;
    }
    public boolean getRegistrationSuccessful() {
        return success;
    }
}
