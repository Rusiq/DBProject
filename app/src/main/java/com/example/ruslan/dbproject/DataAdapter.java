package com.example.ruslan.dbproject;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;

    public DataAdapter(Context context, List<Contact> objects) {
        mContext = context;
        mContactList = objects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvShowFirstName, tvShowLastName, tvShowAdress, tvShowPhone;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.card_view);
            tvShowFirstName = (TextView) itemView.findViewById(R.id.tvShowFirstName);
            tvShowLastName = (TextView) itemView.findViewById(R.id.tvShowLastName);
            tvShowAdress = (TextView) itemView.findViewById(R.id.tvShowAdress);
            tvShowPhone = (TextView) itemView.findViewById(R.id.tvShowPhone);
        }
    }

   /* public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
    }*/


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mContactList.get(position);

        holder.tvShowFirstName.setText(contact.getFirstName());
        holder.tvShowLastName.setText(contact.getLastName());
        holder.tvShowAdress.setText(contact.getAdress());
        holder.tvShowPhone.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void dismissContact (int pos){
        mContactList.remove(pos);
        this.notifyItemRemoved(pos);


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
