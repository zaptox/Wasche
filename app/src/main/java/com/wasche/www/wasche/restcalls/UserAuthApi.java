package com.wasche.www.wasche.restcalls;

import com.wasche.www.wasche.beans.UserBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAuthApi {


    @GET("user-management/Login.php")
    Call<UserBean> performUserLogin(@Query("email") String email, @Query("password") String password);
}
