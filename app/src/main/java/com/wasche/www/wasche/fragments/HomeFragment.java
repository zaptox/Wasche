package com.wasche.www.wasche.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.adapter.ServicesListAdapter;

import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.util.AppProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerViewServices;
    ServicesListAdapter servicesListAdapter;
    List<ServiceTable> services;

//    CompositeDisposable compositeDisposable=new CompositeDisposable();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);


        recyclerViewServices=(RecyclerView) view.findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setHasFixedSize(true);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));
        Context context = inflater.getContext();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerViewServices.setLayoutManager(mLayoutManager);
         services=new ArrayList<>();

         services=new Select().all().from(ServiceTable.class).execute();
         generateServicesList(services);




   return view; }

    private void generateServicesList(List<ServiceTable> servicesList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        servicesListAdapter= new ServicesListAdapter(getContext(),servicesList);
        recyclerViewServices.setAdapter(servicesListAdapter);
    }


    public void checkInternetConncetion(View view){

    }

}
