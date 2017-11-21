package hr.foi.air.webservice.rest;

import hr.foi.air.data.registration.User;
import hr.foi.air.webservice.model.LoginUserResponse;
import hr.foi.air.webservice.model.RegisterUserResponse;
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
    Call<LoginUserResponse> getLoginUserInfo(@Query("username") String username, @Query("password") String password);

    @POST("register")
    Call<RegisterUserResponse> getRegisterUserInfo(@Body User user);
}
