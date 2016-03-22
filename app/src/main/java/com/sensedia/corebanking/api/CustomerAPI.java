package com.sensedia.corebanking.api;

import com.sensedia.corebanking.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by renan on 19/03/16.
 */
public interface CustomerAPI {


    @GET("customers")
    Call<List<Customer>> getCustomers(@Query("key") String key);

    @GET("customers/{id}")
    Call<List<Customer>> getCustomersById(@Path("id") String id, @Query("key") String key);

    @POST("customers")
    Call<Customer> createCustomer(@Body Customer customer, @Query("key") String key);

    @PUT("customers/{id}")
    Call<Customer> updateCustomer(@Body Customer customer, @Path("id") String id, @Query("key") String key);
}
