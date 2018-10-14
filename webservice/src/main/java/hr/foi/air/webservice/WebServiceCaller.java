package hr.foi.air.webservice;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import hr.foi.air.data.beans.EventBean;
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
        Call<ArrayList<EventBean>> callEvents = null;
        final String errorMessage = context.getString(R.string.toast_error);

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
                break;
            }
            case "getEventList": {
                callEvents = apiService.getEventListInfo();
                break;
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
                    Toast.makeText(context.getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        }

        if(callEvents != null) {
            callEvents.enqueue(new Callback<ArrayList<EventBean>>() {
                @Override
                public void onResponse(Call<ArrayList<EventBean>> call, Response<ArrayList<EventBean>> response) {
                    try {
                        switch(type) {
                            case "getEventList": {
                                callback.onDataArrived(response.body());
                                break;
                            }
                        }
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<EventBean>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
