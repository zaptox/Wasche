package com.wasche.www.wasche.restcalls;

import com.wasche.www.wasche.beans.DeliveriesList;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.beans.DeliveryDetailBean;
import com.wasche.www.wasche.beans.DeliveryDetailsList;
import com.wasche.www.wasche.beans.UrgentCostList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DeliveryApi {
//    @POST("delivery/insert_delivery.php")
//    Call<DeliveryBean> saveDelivery(@Body DeliveryBean deliveryBean);

    @GET("delivery/insert_delivery.php")
    Call<DeliveryBean> saveDelivery(@Query("customer_id") int customerId, @Query("address") String address, @Query("expected_date") String expected_date, @Query("total_delivery_cost") double total_delivery_cost, @Query("delivery_status") String delivery_status, @Query("urgent_status") int urgent_statut, @Query("created_by") int created_by, @Query("quantity") int quantity);


    @GET("delivery/insert_delivery_details.php")
    Call<DeliveryDetailBean> saveDeliveryDetail(@Query("service_id") int service_id, @Query("item_id") int item_id, @Query("total_cost") double total_cost, @Query("customer_id") int customer_id, @Query("qunatity") int qunatity);



    @GET("delivery/getDeliveriesByCustomerId.php")
    Call<DeliveriesList> getdeliveriesByCustomerId(@Query("customer_id") int customer_id);

    @GET("delivery/getDeliveryDetailsByDelivId.php")
    Call<DeliveryDetailsList> getdeliveryDetailsById(@Query("delivery_id") int deliveryId);


}
