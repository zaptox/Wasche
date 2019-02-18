package com.wasche.www.wasche.activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.beans.CustomerBean;
import com.wasche.www.wasche.beans.DeliveriesList;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.DeliveryApi;
import com.wasche.www.wasche.restcalls.ServiceAPI;
import com.wasche.www.wasche.restcalls.UserAuthApi;
import com.wasche.www.wasche.util.Prefrences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends Activity {

    TextView tx, tx2;
    Button btnSignIn;
    EditText editTextEmail, editTextPassword;
   public static UserAuthApi userAuthApi;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userAuthApi = ApiClient.getApiClient().create(UserAuthApi.class);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);



        tx = findViewById(R.id.textView);


        tx2 = findViewById(R.id.textView2);


        btnSignIn = findViewById(R.id.btnOrderDone);

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, Sign_up.class);
                startActivity(intent);
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });



btnSignIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        try {


            CustomerBean customerBean = perfromLogin(email, password);



        } catch (Exception e) {

        }
    }
});


    }


    public CustomerBean perfromLogin(String email1, String password1) {
        final CustomerBean userLoggedIn = new CustomerBean();

        Call<CustomerBean> call = SignInActivity.userAuthApi.performUserLogin(email1, password1);



        setUIToWait(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        call.enqueue(new Callback<CustomerBean>() {
            @Override

            public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {

//
                if (response.isSuccessful()) {
                    setUIToWait(false);
                }
//

                if (response.body().getResponse().equals("ok")) {

                    userLoggedIn.setId(response.body().getId());
                    userLoggedIn.setName(response.body().getName());
                    userLoggedIn.setContact( response.body().getContact());
                    userLoggedIn.setEmail(response.body().getEmail());
                    userLoggedIn.setAddress(response.body().getAddress());
                    userLoggedIn.setCreatedAt(response.body().getCreatedAt());
                    userLoggedIn.setUpdatedAt(response.body().getUpdatedAt());
                    userLoggedIn.setUpdatedBy(response.body().getUpdatedBy());
                    userLoggedIn.setCreatedBy(response.body().getCreatedBy());
                    userLoggedIn.setActive(response.body().getActive());
                    userLoggedIn.setProfilePicture(response.body().getProfilePicture());
                    userLoggedIn.setPassword(response.body().getPassword());

                    Prefrences prefrences = new Prefrences();
                    prefrences.addtosharedpreference(getApplicationContext(), userLoggedIn);
                    setUIToWait(false);
//                    Toast.makeText(SiginInActivity.this, ""+userLoggedIn, Toast.LENGTH_SHORT).show();
                    //
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);

                    startActivity(intent);
                    finish();
//
//
                } else if (response.body().getResponse().equals("failed")) {
                    setUIToWait(false);
                    //  Toast.makeText(SiginInActivity.this, "Response: " + response.body().getResponse(), Toast.LENGTH_SHORT).show();

                }
//
                else {
                    setUIToWait(false);
                    //  Toast.makeText(SiginInActivity.this, "Response: " + response.body().getResponse(), Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<CustomerBean> call, Throwable t) {

                setUIToWait(false);

            }
        });
        return userLoggedIn;
    }

    private void setUIToWait(boolean wait) {

        if (wait) {
            progressDialog = ProgressDialog.show(this, null, null, true, true);
//            progressDialog.setContentView(new ProgressBar(this));
            progressDialog.setContentView(R.layout.loader);

        } else {
            progressDialog.dismiss();
        }

    }


}
