package com.wasche.www.wasche.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.beans.CustomerBean;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.DeliveryApi;
import com.wasche.www.wasche.restcalls.UserAuthApi;
import com.wasche.www.wasche.util.Prefrences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private ImageView imageViewprofileImage,imageViewEdit;
   private EditText etFullname,etMobile,etEmail,etAddress;
   private Button btnSaveUpdates;
    Prefrences prefrences;
    public UserAuthApi userAuthApi;
    private ProgressDialog progressDialog;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        imageViewprofileImage=(ImageView)view.findViewById(R.id.imageViewprofileImage);
        imageViewEdit=(ImageView)view.findViewById(R.id.imageViewEdit);
        etFullname=(EditText) view.findViewById(R.id.etFullname);
        etMobile=(EditText) view.findViewById(R.id.etMobile);
        etEmail=(EditText) view.findViewById(R.id.etEmail);
        etAddress=(EditText) view.findViewById(R.id.etAddress);
        btnSaveUpdates=(Button)view.findViewById(R.id.btnSaveUpdates);

        disableEditTexts();

        Prefrences prefrences=new Prefrences();
        CustomerBean customerBean=prefrences.getCustomerBean(getContext());
        etFullname.setText(customerBean.getName());
        etAddress.setText(customerBean.getAddress());
        etEmail.setText(customerBean.getEmail());
        etMobile.setText(customerBean.getContact());

        Picasso.get()
                .load(customerBean.getProfilePicture())
                .error(R.drawable.blankprofile)
                .placeholder(R.drawable.blankprofile)
                .into(imageViewprofileImage);




        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSaveUpdates.setVisibility(View.VISIBLE);
                enableEditTexts();

            }
        });



        btnSaveUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSaveUpdates.setVisibility(View.GONE);
                disableEditTexts();
                updateAccount();


            }
        });
        return  view; }

        private  void updateAccount(){
            setUIToWait(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            final Prefrences prefrences=new Prefrences();
             UserAuthApi userAuthApi  = ApiClient.getApiClient().create(UserAuthApi.class);
            int customerId=prefrences.getCustomerBean(getContext()).getId();
            final String custoName=etFullname.getText().toString();
            final String num=etMobile.getText().toString();
            final String email=etEmail.getText().toString();
            final String address=etAddress.getText().toString();
            Call<CustomerBean> callCustomer=userAuthApi.updateAccount(customerId,custoName,num,email,address);
            callCustomer.enqueue(new Callback<CustomerBean>() {
                @Override
                public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {
                    setUIToWait(false);
                    CustomerBean customerBean=prefrences.getCustomerBean(getContext());
                    customerBean.setName(custoName);
                    customerBean.setContact(num);
                    customerBean.setEmail(email);
                    customerBean.setAddress(address);
                    prefrences.addtosharedpreference(getContext(),customerBean);
                    Intent intent=new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<CustomerBean> call, Throwable t) {

                }
            });
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

    private void enableEditTexts(){

        etFullname.setFocusableInTouchMode(true);
        etFullname.setEnabled(true);
        etFullname.setFocusable(true);


        etAddress.setFocusable(true);
        etAddress.setFocusableInTouchMode(true);
        etAddress.setEnabled(true);


        etEmail.setFocusableInTouchMode(true);
        etEmail.setEnabled(true);
        etEmail.setFocusable(true);


        etMobile.setFocusable(true);
        etMobile.setFocusableInTouchMode(true);
        etMobile.setEnabled(true);
    }
    private  void disableEditTexts(){

        etFullname.setFocusableInTouchMode(false);
        etFullname.setEnabled(false);
        etFullname.setFocusable(false);


        etAddress.setFocusable(false);
        etAddress.setFocusableInTouchMode(false);
        etAddress.setEnabled(false);


        etEmail.setFocusableInTouchMode(false);
        etEmail.setEnabled(false);
        etEmail.setFocusable(false);


        etMobile.setFocusable(false);
        etMobile.setFocusableInTouchMode(false);
        etMobile.setEnabled(false);

    }
}
