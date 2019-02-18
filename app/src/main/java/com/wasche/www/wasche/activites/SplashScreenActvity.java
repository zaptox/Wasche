package com.wasche.www.wasche.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wasche.www.wasche.R;
import com.wasche.www.wasche.daoImp.ItemDaoImpl;
import com.wasche.www.wasche.daoImp.ServiceDaoImpl;
import com.wasche.www.wasche.daoImp.UrgentDaoImpl;
import com.wasche.www.wasche.dao.ItemDao;
import com.wasche.www.wasche.dao.ServiceDao;
import com.wasche.www.wasche.dao.UrgentDao;

public class SplashScreenActvity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private static final String TAG="SplashScrren";
    public  static double urgentDeliveryPercent=0.0;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView  imageView=(ImageView) findViewById(R.id.imageViewLogoSplash);


        UrgentDao urgentDao=new UrgentDaoImpl();
        ServiceDao serviceDao=new ServiceDaoImpl();
        ItemDao itemDao=new ItemDaoImpl();
        urgentDeliveryPercent=urgentDao.loadUrgentPercent();
        serviceDao.loadServices();
        itemDao.loadItems(urgentDeliveryPercent);
        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                finish();


    }



}
