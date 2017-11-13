package hr.foi.air.sportloc.rest;

import hr.foi.air.sportloc.model.LoginUserResponse;
import hr.foi.air.sportloc.model.RegisterUserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Gabriel on 8.11.2017..
 */

public interface ApiInterface {
    @GET("login")
    Call<LoginUserResponse> getLoginUserInfo(@Query("username") String username, @Query("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterUserResponse> getRegisterUserInfo(@Field("firstName") String firstName,
                                                   @Field("lastName") String lastName,
                                                   @Field("userName") String userName,
                                                   @Field("email") String email,
                                                   @Field("gender") String gender,
                                                   @Field("password") String password,
                                                   @Field("dob") String dob);
}
