package com.wasche.www.wasche.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.wasche.www.wasche.R;

public class ForgotPasswordActivity extends Activity {

    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.btnSaveUpdates);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.1 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_activity);
    }
}
