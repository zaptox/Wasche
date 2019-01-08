package com.wasche.www.wasche.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.wasche.www.wasche.R;

public class AppProgressBar {
   private static ProgressDialog progressDialog;

    public static void setUIToWait(boolean wait, Context context) {

        progressDialog = new ProgressDialog(context);


        if (wait) {

            progressDialog = ProgressDialog.show(context, null, null, true, true);
            progressDialog.setContentView(R.layout.loader);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        } else {
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            progressDialog.dismiss();
        }

    }
}
