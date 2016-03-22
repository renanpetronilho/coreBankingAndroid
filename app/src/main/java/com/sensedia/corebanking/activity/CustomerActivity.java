package com.sensedia.corebanking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sensedia.corebanking.R;
import com.sensedia.corebanking.fragment.CustomerFragment;
import com.sensedia.corebanking.model.Customer;
import com.sensedia.corebanking.util.BaseActivity;

/**
 * Created by renan on 19/03/16.
 */
public class CustomerActivity extends BaseActivity {


    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        setContentView(R.layout.customer_activity);

        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomerFragment customerFragment = (CustomerFragment) getSupportFragmentManager().findFragmentById(R.id.CustomerFragment);

        Customer customer = (Customer) getIntent().getSerializableExtra("customer");

        customerFragment.setCustomer(customer);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpTo(this, intent);
                return true;
            case 888:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
