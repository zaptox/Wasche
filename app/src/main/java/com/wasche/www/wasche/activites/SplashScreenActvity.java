package com.wasche.www.wasche.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.beans.ItemBean;
import com.wasche.www.wasche.beans.ServiceBean;
import com.wasche.www.wasche.beans.ServiceItemsList;
import com.wasche.www.wasche.beans.ServicesList;
import com.wasche.www.wasche.beans.UrgentCostList;
import com.wasche.www.wasche.dbtables.ItemTable;
import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActvity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private static final String TAG="SplashScrren";
    public  static double urgentDeliveryPercent=0.0;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        ServiceAPI UApi = ApiClient.getApiClient().create(ServiceAPI.class);
        Call<UrgentCostList> callU=UApi.getUrgentDetails();
        callU.enqueue(new Callback<UrgentCostList>() {
            @Override
            public void onResponse(Call<UrgentCostList> call, Response<UrgentCostList> response) {
                urgentDeliveryPercent=Double.parseDouble(response.body().getUrgentBeanList().get(0).getUrgentPercent()+"");
            }

            @Override
            public void onFailure(Call<UrgentCostList> call, Throwable t) {
                Log.d("Excep",t.getMessage());
            }
        });



        ServiceAPI api = ApiClient.getApiClient().create(ServiceAPI.class);

        Call<ServiceItemsList> callItems=api.getServicesItems();
        callItems.enqueue(new Callback<ServiceItemsList>() {
            @Override
            public void onResponse(Call<ServiceItemsList> call, Response<ServiceItemsList> response) {
               saveServicesItemsToLocalDB(response.body().getServiceItemsList());

            }

            @Override
            public void onFailure(Call<ServiceItemsList> call, Throwable t) {

            }
        });


           ServiceAPI serviceAPI = ApiClient.getApiClient().create(ServiceAPI.class);
        Call<ServicesList> call=serviceAPI.getServicesData();
        call.enqueue(new Callback<ServicesList>() {
            @Override
            public void onResponse(Call<ServicesList> call, Response<ServicesList> response) {
                        saveServicesToLocalDB(response.body().getServicesArrayList());
                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                        finish();

            }

            @Override
            public void onFailure(Call<ServicesList> call, Throwable t) {

            }
        });


}


    private void saveServicesToLocalDB(ArrayList<ServiceBean> servicesArrayList) {
        if(new Select().all().from(ServiceTable.class).execute().size()!=0){
            new Delete().from(ServiceTable.class).execute();
        }
        for(ServiceBean serviceBean:servicesArrayList){
            ServiceTable service=new ServiceTable();
            service.setName(serviceBean.getName());
            service.setImage(serviceBean.getImage());
            service.setServiceId(serviceBean.getServiceId());
            service.save();
        }}


    private void saveServicesItemsToLocalDB(ArrayList<ItemBean> itemsArrayList) {

        if(new Select().all().from(ItemTable.class).execute().size()!=0){
            new Delete().from(ItemTable.class).execute();
        }


        for (ItemBean item : itemsArrayList) {
            ItemTable itemTable = new ItemTable();

            itemTable.setItemId(item.getId());
            itemTable.setName(item.getName());
            itemTable.setCost(item.getCost());
            itemTable.setImage(item.getImage());
            itemTable.setUrgentPercent(urgentDeliveryPercent);
            itemTable.setUrgentCost(getUrgentCost(item.getCost(),urgentDeliveryPercent));
            itemTable.setServiceId(item.getServiceId());
            itemTable.save();

        }


    }
 public  double getUrgentCost(double actualCost,double percent){

        return actualCost+(actualCost*(percent/100));
 }

}
