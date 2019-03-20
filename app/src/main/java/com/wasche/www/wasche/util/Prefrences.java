package com.wasche.www.wasche.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.activites.SplashScreenActvity;
import com.wasche.www.wasche.beans.CustomerBean;

public class Prefrences {
    private static String USER_DETAILS_PREFRENCES="user_details";

    public CustomerBean getCustomerBean(Context context){
        SharedPreferences sharedPreferencespre =context.getSharedPreferences(USER_DETAILS_PREFRENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferencespre.getString("customerBean", null);
        return gson.fromJson(json, CustomerBean.class);
    }


    public void addtosharedpreference(Context context,CustomerBean customerBean){
        SharedPreferences sharedPreferences =context.getSharedPreferences(USER_DETAILS_PREFRENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();


        SharedPreferences sharedPreferencespre =context.getSharedPreferences(USER_DETAILS_PREFRENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferencespre.edit();
        Gson gson = new Gson();
        String json = gson.toJson(customerBean); // myObject - instance of MyObject
        editor.putString("customerBean", json);
        editor.apply();
        editor.commit();


    }


    public void logout(Context context){

        SharedPreferences sharedPreferences =context.getSharedPreferences(USER_DETAILS_PREFRENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        Intent intent=new Intent(context,SplashScreenActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show();

    }
}
