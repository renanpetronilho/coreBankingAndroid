package com.sensedia.corebanking.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sensedia.corebanking.R;
import com.sensedia.corebanking.model.Customer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by renan on 19/03/16.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private Context context;
    private List<Customer> customers;
    private LayoutInflater inflater;
    private CustomerOnClickListener customerOnClickListener;


    public CustomerAdapter(Context _context, List<Customer> customers, CustomerOnClickListener customerOnClickListener) {
        this.context = _context;
        this.customers = customers;
        this.customerOnClickListener = customerOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.customers != null ? this.customers.size() : 0;
    }


    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.customer_line, parent, false);

        CardView cardView = (CardView) view.findViewById(R.id.customer_card);

        CustomerViewHolder holder = new CustomerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomerViewHolder holder, final int position) {

        populateView(holder, position);

        if (customerOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerOnClickListener.onClickCustomer(holder.itemView, position);
                }
            });
        }
    }

    private void populateView(final CustomerViewHolder holder, final int position) {
        holder.tvName.setText(customers.get(position).getFirst_name());
        holder.tvLastName.setText(customers.get(position).getLast_name());
    }


    public interface CustomerOnClickListener {
        public void onClickCustomer(View view, int idx);
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_lastName)
        TextView tvLastName;

        public CustomerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
