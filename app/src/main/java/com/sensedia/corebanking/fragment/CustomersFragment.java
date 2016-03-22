package com.sensedia.corebanking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sensedia.corebanking.R;
import com.sensedia.corebanking.activity.CustomerActivity;
import com.sensedia.corebanking.adapter.CustomerAdapter;
import com.sensedia.corebanking.api.CustomerAPI;
import com.sensedia.corebanking.client.ServiceGenerator;
import com.sensedia.corebanking.model.Customer;
import com.sensedia.corebanking.util.BaseFragment;
import com.sensedia.corebanking.util.Key;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by renan on 19/03/16.
 */
public class CustomersFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    private List<Customer> customers = new ArrayList<Customer>();
    private LinearLayoutManager mLayoutManager;
    FloatingActionButton btAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customers_fragment, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcCustomers);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        getActionBar().setTitle(getString(R.string.customers));
        btAdd = (FloatingActionButton) view.findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CustomerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCustomers();
    }

    private void taskCustomers() {
        customers.clear();
        CustomerAPI api = ServiceGenerator.createService(CustomerAPI.class);
        try {
            Response<List<Customer>> response = api.getCustomers(Key.VALUE).execute();
            if (response.isSuccessful()) {
                customers = response.body();
                recyclerView.setAdapter(new CustomerAdapter(getContext(), customers, onClickCustomer()));
            }
        } catch (IOException e) {
            toast("Failed to fetch customers");
        }
    }

    private CustomerAdapter.CustomerOnClickListener onClickCustomer() {
        return new CustomerAdapter.CustomerOnClickListener() {
            @Override
            public void onClickCustomer(View view, int idx) {
                Customer  customer = customers.get(idx);
                Intent intent = new Intent(getContext(), CustomerActivity.class);
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        taskCustomers();
    }


}
