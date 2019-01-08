package com.wasche.www.wasche.activites;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.beans.ServiceItemsList;
import com.wasche.www.wasche.fragments.AccountFragment;
import com.wasche.www.wasche.fragments.CartFragment;
import com.wasche.www.wasche.fragments.HomeFragment;
import com.wasche.www.wasche.fragments.NoNetworkFragment;
import com.wasche.www.wasche.fragments.ServiceItemsFragment;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.ServiceAPI;
import com.wasche.www.wasche.util.CONST;
import com.wasche.www.wasche.util.receiver.NetworkStateChangeReceiver;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wasche.www.wasche.util.receiver.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNav;
    boolean ServicesItemActivity =false;
    public static final String TAG = "HomeActivity";


    //Animation

    public void didTapButton(View view) {
        CardView cardView = findViewById(R.id.card1);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.1 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
        myAnim.setInterpolator(interpolator);

        cardView.startAnimation(myAnim);
        Intent intent = new Intent(HomeActivity.this, OrderDetails.class);
        startActivity(intent);
    }

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


        Log.d(TAG,"flag : "+flag);
        switch (flag)
        {
            case CONST.ORDER_DETAILS_FRAGMENT :
                ServicesItemActivity=true;
                 goInServicesItemsFragment(serviceId,serviceTitle,serviceBannerImg);
                 getSupportActionBar().setTitle(serviceTitle);
                break;

            default:
                replaceFragment(new HomeFragment());
                getSupportActionBar().setTitle("Wasche");
        }







//        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
//        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
//                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";
//                if (isNetworkAvailable) {
//                    Toast.makeText(getApplication(), "Network Status: " + networkStatus, Toast.LENGTH_SHORT).show();
//
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
//
//                } else {
//                    Toast.makeText(getApplication(), "Network Status: " + networkStatus, Toast.LENGTH_SHORT).show();
//
//                }
//                Toast.makeText(getApplication(), "Network Status: " + networkStatus, Toast.LENGTH_SHORT).show();
//            }
//        }, intentFilter);


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


        tvNavName.setText("Zain");
        tvNavPhone.setText("03457406821");


        Picasso.get().load("http://zaptox.com/wasche_app/images/user-profiles/zain.jpg").fit().centerCrop()
                .placeholder(R.drawable.mrrobot)
                .error(R.drawable.mrrobot)
                .into(imageViewProfileDrawer);


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
                        case R.id.nav_cart:
                            selectFragment = new CartFragment();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        if(ServicesItemActivity==true) {
//            getMenuInflater().inflate(R.menu.home, menu);
//
//            MenuItem itemSwitch = menu.findItem(R.id.urgent_switch);
//            itemSwitch.setActionView(R.layout.switch_btn_layout);
//            final Switch sw = (Switch) menu.findItem(R.id.urgent_switch).getActionView().findViewById(R.id.action_switch);
//            return true;
//
//        }
//        return false;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            // Handle the camera action
        } else if (id == R.id.nav_pricing) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(getApplicationContext(),SignInActivity.class));
             finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void replaceFragment(Fragment newFragment) {
        if (newFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newFragment).commit();
        }
    }


    public void goInServicesItemsFragment(int serviceId,String serviceTitle,String serviceBannerImg){
        ServiceItemsFragment fragment = new ServiceItemsFragment(serviceId,serviceTitle,serviceBannerImg);
        replaceFragment(fragment);

    }

}