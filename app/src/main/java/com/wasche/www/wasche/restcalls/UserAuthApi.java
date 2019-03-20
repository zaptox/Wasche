package com.wasche.www.wasche.restcalls;

import com.wasche.www.wasche.beans.CustomerBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAuthApi {


    @GET("user-management/Login.php")
    Call<CustomerBean> performUserLogin(@Query("email") String email, @Query("password") String password);


    @GET("user-management/updateAccount.php")
    Call<CustomerBean> updateAccount(@Query("id") int id, @Query("name") String name, @Query("contact") String contact,@Query("email") String email,@Query("address") String address);
}

