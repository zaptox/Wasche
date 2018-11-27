package com.wasche.www.wasche;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn_Activity extends Activity {

    TextView tx, tx2;
    Button btn_signin;
    EditText et_email, et_password;

    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.angry_btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.1 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        Intent intent = new Intent(SignIn_Activity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);



        tx = findViewById(R.id.textView);


        tx2 = findViewById(R.id.textView2);


        btn_signin = findViewById(R.id.angry_btn);

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn_Activity.this, Sign_up.class);
                startActivity(intent);
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn_Activity.this,Forgot_password_activity.class);
                startActivity(intent);
            }
        });






    }
}
