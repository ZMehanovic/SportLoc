package hr.foi.air.webservice.rest;

import hr.foi.air.data.User;
import hr.foi.air.webservice.response.WebServiceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Gabriel on 8.11.2017..
 */

public interface ApiInterface {
    @GET("login")
    Call<WebServiceResponse> getLoginUserInfo(@Query("username") String username, @Query("password") String password);

    @GET("resetPassword")
    Call<WebServiceResponse> getResetPasswordInfo(@Query("email") String email);

    @POST("register")
    Call<WebServiceResponse> getRegisterUserInfo(@Body User user);
}
