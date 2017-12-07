package hr.foi.air.webservice;

import android.content.Context;
import android.widget.Toast;

import hr.foi.air.data.beans.UserBean;
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

    public void callWebService(Object data, final String type, final Context context, final WebServiceHandler callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WebServiceResponse> call = null;

        switch(type) {
            case "login": {
                UserBean userBean = (UserBean) data;
                call = apiService.getLoginUserInfo(userBean.getUserName(), userBean.getPassword());
                break;
            }
            case "registration": {
                UserBean userBean = (UserBean) data;
                call = apiService.getRegisterUserInfo(userBean);
                break;
            }
            case "resetPassword": {
                UserBean userBean = (UserBean) data;
                call = apiService.getResetPasswordInfo(userBean.getEmail());
            }
        }

        if(call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    try {
                        switch(type) {
                            case "login": {
                                callback.onDataArrived(response.body().getLoginSuccessful());
                                break;
                            }
                            case "registration": {
                                callback.onDataArrived(response.body().getRegistrationSuccessful());
                                break;
                            }
                            case "resetPassword": {
                                callback.onDataArrived(response.body().getResetPasswordSuccessful());
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
}
