package com.wasche.www.wasche.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.beans.CustomerBean;
import com.wasche.www.wasche.fragments.AccountFragment;
import com.wasche.www.wasche.fragments.BucketFragment;
import com.wasche.www.wasche.fragments.DeliveryDetailsFragment;
import com.wasche.www.wasche.fragments.HomeFragment;
import com.wasche.www.wasche.fragments.MyOrdersFragment;
import com.wasche.www.wasche.fragments.OrderFragment;
import com.wasche.www.wasche.fragments.ServiceItemsFragment;
import com.wasche.www.wasche.util.CONST;
import com.wasche.www.wasche.util.Prefrences;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNav;
    boolean ServicesItemActivity =false;
    public static final String TAG = "HomeActivity";
    CustomerBean customerBean;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        int flag = getIntent().getIntExtra("flag",0);
        int serviceId=getIntent().getIntExtra("service_id",0);
        String serviceBannerImg=getIntent().getStringExtra("service_img");
        String serviceTitle=getIntent().getStringExtra("service_name");
        int deliveryId=getIntent().getIntExtra("delivery_id",0);
        double totalBill=getIntent().getDoubleExtra("total_bill",0);
        int totalQty=getIntent().getIntExtra("total_qty",0);

        switch (flag)
        {
            case CONST.ORDER_DETAILS_FRAGMENT :
                ServicesItemActivity=true;
                 goInServicesItemsFragment(serviceId,serviceTitle,serviceBannerImg);
                 getSupportActionBar().setTitle(serviceTitle);
                break;
            case CONST.ORDER_FRAGMENT :
                ServicesItemActivity=true;
                goInPalceOrder();
                 getSupportActionBar().setTitle("Palce Order");
                break;
            case CONST.BUCKET_FRAGMENT :
                getSupportActionBar().setTitle("Bucket");
                goInBucket();
                break;
            case CONST.DELIVERY_DETAILS_FRAGMENT:
                getSupportActionBar().setTitle("Delivery Details");
                goInDeliveryDetails(deliveryId,totalBill,totalQty);
                break;
            default:
                replaceFragment(new HomeFragment());
                getSupportActionBar().setTitle("Wasche");
        }



        SharedPreferences ob = getSharedPreferences("user_details", Context.MODE_PRIVATE);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView tvNavName = (TextView) headerView.findViewById(R.id.tv_name);
        TextView tvNavPhone = (TextView) headerView.findViewById(R.id.tv_contact);
        CircleImageView imageViewProfileDrawer = (CircleImageView) headerView.findViewById(R.id.profile_image);
        imageViewProfileDrawer.setImageResource(R.drawable.mrrobot);

        Prefrences prefrences=new Prefrences();
        customerBean=prefrences.getCustomerBean(getApplicationContext());


        tvNavName.setText(customerBean.getName());
        tvNavPhone.setText(customerBean.getContact());
        Picasso.get().load(customerBean.getProfilePicture()).fit().centerCrop()
                .placeholder(R.drawable.blankimg)
                .error(R.drawable.blankimg)
                .into(imageViewProfileDrawer);


    }

    private void goInDeliveryDetails(int deliveryId,double totalBill,int totalQty) {

        DeliveryDetailsFragment fragment = new DeliveryDetailsFragment(deliveryId,totalBill,totalQty);
        replaceFragment(fragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;
                        case R.id.nav_bucket:
                            selectFragment = new BucketFragment();
                            break;
                        case R.id.nav_account:
                            selectFragment = new AccountFragment();
                            break;


                    }

                    replaceFragment(selectFragment);

                    return true;
                }
            };

    @Override
    public void onBackPressed() {


        if(bottomNav.getSelectedItemId()==R.id.nav_account){
            replaceFragment( new HomeFragment());
            getSupportActionBar().setTitle("Wasche");


        }
        else if(bottomNav.getSelectedItemId()==R.id.nav_bucket){
            replaceFragment( new HomeFragment());
            getSupportActionBar().setTitle("Wasche");


        }
        else if(bottomNav.getSelectedItemId()==R.id.nav_home){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            replaceFragment(new MyOrdersFragment());
            getSupportActionBar().setTitle("My Orders");


        } else if (id == R.id.nav_pricing) {

        } else if (id == R.id.nav_profile) {
            replaceFragment(new AccountFragment());
            getSupportActionBar().setTitle("My Profile");


        } else if (id == R.id.nav_bucket) {
            replaceFragment(new BucketFragment());
            getSupportActionBar().setTitle("Bucket");


        } else if (id == R.id.nav_settings) {
            goToSetting();

        } else if (id == R.id.nav_logout) {
            new Prefrences().logout(getApplicationContext());
             finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goToSetting() {
        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));

    }


    private void replaceFragment(Fragment newFragment) {
        if (newFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newFragment).commit();
        }
    }


    private void goInServicesItemsFragment(int serviceId,String serviceTitle,String serviceBannerImg){
        ServiceItemsFragment fragment = new ServiceItemsFragment(serviceId,serviceTitle,serviceBannerImg);
        replaceFragment(fragment);

    }

    private void goInPalceOrder(){
        OrderFragment fragment = new OrderFragment();
        replaceFragment(fragment);

    }
    private void goInBucket(){
        BucketFragment fragment = new BucketFragment();
        replaceFragment(fragment);

    }
}