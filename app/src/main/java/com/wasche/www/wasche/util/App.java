package com.wasche.www.wasche.util;

import android.app.Application;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.dbtables.ItemTable;
import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.dbtables.UrgentTable;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Configuration dbConfiguration =
                new Configuration.Builder(this).
                        setDatabaseName("wascheApp.db").setModelClasses(ServiceTable.class,ItemTable.class, UrgentTable.class,
                        DeliveryDetailTable.class)
                        .create();

        ActiveAndroid.initialize(dbConfiguration);

    }
}