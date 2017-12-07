package hr.foi.air.webservice;

import android.content.Context;
import android.widget.Toast;

import hr.foi.air.data.User;
import hr.foi.air.webservice.response.WebServiceResponse;
import hr.foi.air.webservice.rest.ApiClient;
import hr.foi.air.webservice.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gabriel on 30.11.2017..
 */

public class WebServiceCaller {

    public void CallWebService(Object data, final String type, final Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WebServiceResponse> call = null;

        switch(type) {
            case "login": {
                User user = (User) data;
                call = apiService.getLoginUserInfo(user.getUserName(), user.getPassword());
                break;
            }
            case "registration": {
                User user = (User) data;
                call = apiService.getRegisterUserInfo(user);
                break;
            }
            case "resetPassword": {
                User user = (User) data;
                call = apiService.getResetPasswordInfo(user.getEmail());
            }
        }

        if(call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    try {
                        switch(type) {
                            case "login": {
                                handleLogin(response, context);
                                break;
                            }
                            case "registration": {
                                handleRegistration(response, context);
                                break;
                            }
                            case "resetPassword": {
                                handleResetPassword(response, context);
                                break;
                            }
                        }
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    String message = context.getString(R.string.toast_error);
                    Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void handleLogin(Response<WebServiceResponse> response, Context context) {
        boolean result = response.body().getLoginSuccessful();
        String message = context.getString(R.string.toast_login_fail);
        if(result) {
            message = context.getString(R.string.toast_login_success);
        }
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void handleRegistration(Response<WebServiceResponse> response, Context context) {
        boolean result = response.body().getRegistrationSuccessful();
        String message = context.getString(R.string.toast_registration_fail);
        if(result) {
            message = context.getString(R.string.toast_registration_success);
        }
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void handleResetPassword(Response<WebServiceResponse> response, Context context) {
        boolean result = response.body().getResetPasswordSuccessful();
        String message = context.getString(R.string.toast_forg_pass_fail);
        if(result) {
            message = context.getString(R.string.toast_forg_pass_success);
        }
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
