package com.sensedia.corebanking.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sensedia.corebanking.R;
import com.sensedia.corebanking.api.CustomerAPI;
import com.sensedia.corebanking.client.ServiceGenerator;
import com.sensedia.corebanking.model.Address;
import com.sensedia.corebanking.model.Customer;
import com.sensedia.corebanking.util.BaseFragment;
import com.sensedia.corebanking.util.Key;
import com.sensedia.corebanking.util.Operation;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * Created by renan on 19/03/16.
 */
public class CustomerFragment extends BaseFragment {

    private Customer customer;
    private String id;

    @Bind(R.id.edFisrtName)
    protected EditText edFirstName;

    @Bind(R.id.edLastName)
    protected EditText edLastName;

    @Bind(R.id.edCity)
    protected EditText edCity;

    @Bind(R.id.edState)
    protected EditText edState;

    @Bind(R.id.edStreetName)
    protected EditText edStreetName;

    @Bind(R.id.edStreetNumber)
    protected EditText edStreetNumber;

    @Bind(R.id.edZipCode)
    protected EditText edZipCode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_crud, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save() {
        if (this.customer.get_id() == null)
            createCustomer();
        else
            updateCustomer();

    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
            this.id = customer.get_id();
            populateView();
            edFirstName.setEnabled(false);
            edLastName.setEnabled(false);
        } else {
            this.customer = new Customer();
            edFirstName.setEnabled(true);
            edLastName.setEnabled(true);
        }
    }

    private void populateView() {
        edFirstName.setText(customer.getFirst_name());
        edLastName.setText(customer.getLast_name());
        edCity.setText(customer.getAddress().getCity());
        edState.setText(customer.getAddress().getState());
        edStreetName.setText(customer.getAddress().getStreetName());
        edStreetNumber.setText(customer.getAddress().getStreetNumber());
        edZipCode.setText(customer.getAddress().getZip());
    }

    private void populateBean(Operation op) {

        if (op.equals(Operation.CREATE)) {
            customer.setFirst_name(edFirstName.getText().toString());
            customer.setLast_name(edLastName.getText().toString());
        }

        Address address = new Address();
        address.setStreetName(edStreetName.getText().toString());
        address.setStreetNumber(edStreetNumber.getText().toString());
        address.setCity(edCity.getText().toString());
        address.setState(edState.getText().toString());
        address.setZip(edZipCode.getText().toString());

        customer.setAddress(address);
    }

    private void createCustomer() {
        populateBean(Operation.CREATE);
        CustomerAPI api = ServiceGenerator.createService(CustomerAPI.class);
        try {
            Response response = api.createCustomer(customer, Key.VALUE).execute();
            if (response.isSuccessful()) {
                toast("Customer create successful");
                getActivity().finish();
            } else {
                toast(response.errorBody().string());
            }
        } catch (IOException e) {
            toast(e.getMessage());
        }


    }

    private void updateCustomer() {
        populateBean(Operation.UPDATE);
        CustomerAPI api = ServiceGenerator.createService(CustomerAPI.class);
        try {
            Customer customerUpdate = new Customer(customer.getAddress());
            Response response = api.updateCustomer(customerUpdate, customer.get_id(), Key.VALUE).execute();
            if (response.isSuccessful()) {
                toast("Customer update successful");
                getActivity().finish();
            } else {
                toast(response.errorBody().string());
            }
        } catch (IOException e) {
            toast(e.getMessage());
        }
    }

}
