package com.wasche.www.wasche.daoImp;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.wasche.www.wasche.beans.ItemBean;
import com.wasche.www.wasche.beans.ServiceItemsList;
import com.wasche.www.wasche.dao.ItemDao;
import com.wasche.www.wasche.dao.UrgentDao;
import com.wasche.www.wasche.dbtables.ItemTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.ServiceAPI;
import com.wasche.www.wasche.util.calculatePercentCost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDaoImpl implements ItemDao {

    private double urgentPercent=0.0;

    @Override
    public void addItems(List<ItemBean> items,double urgentPercent) {

        if(new Select().all().from(ItemTable.class).execute().size()!=0){
            new Delete().from(ItemTable.class).execute();
        }


        for (ItemBean item : items) {
            ItemTable itemTable = new ItemTable();

            itemTable.setItemId(item.getId());
            itemTable.setName(item.getName());
            itemTable.setCost(item.getCost());
            itemTable.setImage(item.getImageBaseUrl()+"/"+item.getImagePath());
            itemTable.setUrgentPercent(urgentPercent);
            itemTable.setUrgentCost(calculatePercentCost.getUrgentCost(item.getCost(), urgentPercent));
            itemTable.setServiceId(item.getServiceId());
            itemTable.save();

        }
    }

    @Override
    public List<ItemTable> getItemsByServiceId(Integer id) {

        List<ItemTable>items=new Select().all().from(ItemTable.class).where("serviceId=?",id).execute();
        return items;
    }

    @Override
    public void loadItems(final double urgentPercent) {
        UrgentDao urgentDao=new UrgentDaoImpl();
//        final Double urgentPer=urgentDao.getUrgentPercent().getUrgentPercent();
        ServiceAPI api = ApiClient.getApiClient().create(ServiceAPI.class);

        Call<ServiceItemsList> callItems=api.getServicesItems();
        callItems.enqueue(new Callback<ServiceItemsList>() {
            @Override
            public void onResponse(Call<ServiceItemsList> call, Response<ServiceItemsList> response) {
                ItemDao itemDao=new ItemDaoImpl();
                itemDao.addItems(response.body().getServiceItemsList(),urgentPercent);

            }

            @Override
            public void onFailure(Call<ServiceItemsList> call, Throwable t) {

            }
        });
    }
}
