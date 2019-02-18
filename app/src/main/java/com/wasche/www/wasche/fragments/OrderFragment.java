package com.wasche.www.wasche.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.app.DatePickerDialog;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.beans.CustomerBean;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.beans.DeliveryDetailBean;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.DeliveryApi;
import com.wasche.www.wasche.util.Prefrences;

import org.jetbrains.annotations.TestOnly;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    Switch switchUserMyAccountDetails;
    EditText editTextFName,editTextLname,editTextEmail,editTextPhone,editTextAddress;
    Button btnOrderDone;
    List<DeliveryDetailTable> ddts;
    int urgentStatus;
    double totalDeliveryCost=0.0;
    int qty=0;
    Prefrences prefrences;
    CustomerBean customerBean;
    private ImageView imageViewDate;
    private TextView textViewDate;
    LinearLayout linearLayout;
    private ProgressDialog progressDialog;



    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_order, container, false);
        switchUserMyAccountDetails=(Switch)view.findViewById(R.id.switchUserMyAccountDetails);
        btnOrderDone=(Button)view.findViewById(R.id.btnOrderDone);
        editTextFName=(EditText)view.findViewById(R.id.editTextFName);
        editTextLname=(EditText)view.findViewById(R.id.editTextLname);
        editTextEmail=(EditText)view.findViewById(R.id.editTextEmail);
        editTextPhone=(EditText)view.findViewById(R.id.editTextPhone);
        editTextAddress=(EditText)view.findViewById(R.id.editTextAddress);
        btnOrderDone=(Button)view.findViewById(R.id.btnOrderDone);
        textViewDate=(TextView)view.findViewById(R.id.textViewDate);
        imageViewDate=(ImageView) view.findViewById(R.id.imageViewDate);
        linearLayout=(LinearLayout)view.findViewById(R.id.linearLayoutBase);


         prefrences=new Prefrences();
         customerBean=prefrences.getCustomerBean(getContext());
        editTextEmail.setText(customerBean.getEmail());
        editTextPhone.setText(customerBean.getContact());
        String name[]=customerBean.getName().split(" ");
        editTextFName.setText(name[0]);
        if(name.length>1){
            editTextLname.setText(name[1]);
        }


        ddts=new Select().all().from(DeliveryDetailTable.class).execute();
        for(DeliveryDetailTable ddt: ddts){
            if(ddt.getUrgentStatus()==1)
                totalDeliveryCost+=ddt.getTotalUrgentCost();
            else
                totalDeliveryCost+=ddt.getTotalCost();

            qty+=ddt.getQuantity();
            urgentStatus=ddt.getUrgentStatus();

    }


        btnOrderDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUIToWait(true);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Prefrences prefrences=new Prefrences();
                DeliveryApi deliveryApi = ApiClient.getApiClient().create(DeliveryApi.class);
                int customerId =prefrences.getCustomerBean(getContext()).getId();
                String address=editTextAddress.getText().toString();
                String expectedDate="1544055";
                String deliveryStatus="0";
                int createdBy=customerId;

                Call<DeliveryBean> callDelivery=deliveryApi.saveDelivery(customerId,address,expectedDate,totalDeliveryCost,deliveryStatus,urgentStatus,createdBy,qty);
                callDelivery.enqueue(new Callback<DeliveryBean>() {
                    @Override
                    public void onResponse(Call<DeliveryBean> call, Response<DeliveryBean> response) {
                        if(response.isSuccessful()){
                            addDeliveryDetails(ddts);

                        }
                    }

                    @Override
                    public void onFailure(Call<DeliveryBean> call, Throwable t) {
                        Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        switchUserMyAccountDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchUserMyAccountDetails.setTextOff("Use my account details");
                switchUserMyAccountDetails.setTextOn("Not use my account details");

                if(b){

                    editTextAddress.setText(customerBean.getAddress());



                }
                else{
                    editTextAddress.setText("");

                }
            }
        });


        imageViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment datePicker=new DatePickerFragment();
//                datePicker.show(getFragmentManager(),"Date");
            }
        });

        editTextAddress.requestFocus();

        return  view;}


        private  void addDeliveryDetails(List<DeliveryDetailTable> deliveryDetails){
        int counter=0;
            for(final DeliveryDetailTable ddt: deliveryDetails) {


                 counter++;
                Prefrences prefrences = new Prefrences();
                DeliveryApi deliveryApi = ApiClient.getApiClient().create(DeliveryApi.class);
                int customerId = prefrences.getCustomerBean(getContext()).getId();
                int serviceId = ddt.getServiceId();

                final int itemId = ddt.getItemId();
                double totalCost = 0.0;
                if (ddt.getUrgentStatus() == 1) {
                    totalCost = ddt.getTotalUrgentCost();
                } else {
                    totalCost = ddt.getTotalCost();
                }
                int qty = ddt.getQuantity();

                Call<DeliveryDetailBean> callDeliveryDetail = deliveryApi.saveDeliveryDetail(serviceId,itemId,totalCost,customerId,qty);
                callDeliveryDetail.enqueue(new Callback<DeliveryDetailBean>() {
                    @Override
                    public void onResponse(Call<DeliveryDetailBean> call, Response<DeliveryDetailBean> response) {
                     if(response.isSuccessful()){
                     }
                    }

                    @Override
                    public void onFailure(Call<DeliveryDetailBean> call, Throwable t) {

                    }
                });
                if(counter==ddts.size()){
                    new Delete().from(DeliveryDetailTable.class).execute();
                    startActivity(new Intent(getContext(), HomeActivity.class));
                    getActivity().finish();
                }
            }}
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textViewDate.setText(currentDate);
    }
    private void setUIToWait(boolean wait) {

        if (wait) {
            progressDialog = ProgressDialog.show(getContext(), null, null, true, true);
//            progressDialog.setContentView(new ProgressBar(this));
            progressDialog.setContentView(R.layout.loader);

        } else {
            progressDialog.dismiss();
        }

    }
}
