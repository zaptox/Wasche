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

import com.wasche.www.wasche.R;
import com.wasche.www.wasche.beans.UserBean;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.UserAuthApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends Activity {

    TextView tx, tx2;
    Button btnSignIn;
    EditText editTextEmail, editTextPassword;
   public static UserAuthApi userAuthApi;
    private ProgressDialog progressDialog;


    public void didTapButton(View view) {
//        Button button = (Button)findViewById(R.id.angry_btn);
//        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
//
////         Use bounce interpolator with amplitude 0.1 and frequency 20
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
//        myAnim.setInterpolator(interpolator);
//
//        button.startAnimation(myAnim);
//        Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
//        startActivity(intent);
    }

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


            UserBean userBean = perfromLogin(email, password);
            addtosharedpreference(userBean);




        } catch (Exception e) {

            // Toast.makeText(SiginInActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
});


    }


    public UserBean perfromLogin(String email1, String password1) {
        final UserBean userLoggedIn = new UserBean();

        Call<UserBean> call = SignInActivity.userAuthApi.performUserLogin(email1, password1);



        setUIToWait(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        call.enqueue(new Callback<UserBean>() {
            @Override

            public void onResponse(Call<UserBean> call, Response<UserBean> response) {

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
            public void onFailure(Call<UserBean> call, Throwable t) {

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

    public void addtosharedpreference(UserBean userBean){

        SharedPreferences sharedPreferencespre =getSharedPreferences("user_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferencespre.edit();
        editor.putInt("user_id",userBean.getId());
        editor.putString("email",userBean.getEmail());
        editor.putString("password",userBean.getPassword());
        editor.putString("name",userBean.getName());
        editor.putString("profile_img",userBean.getProfilePicture());
        editor.putInt("active",userBean.getActive());
        editor.putString("address",userBean.getAddress());
        editor.putString("contact",userBean.getContact());

        editor.apply();
        editor.commit();


    }
}
