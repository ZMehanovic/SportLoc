package hr.foi.air.sportloc.rest;

import hr.foi.air.sportloc.model.LoginUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gabriel on 8.11.2017..
 */

public interface ApiInterface {
    @GET("login")
    Call<LoginUserResponse> getLoginUserInfo(@Query("username") String username, @Query("password") String password);
}
