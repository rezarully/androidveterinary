package com.rsh.android.rekammedisbosimrsh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by rezar on 02/05/2017.
 */

public class DataAdapterInapRm extends RecyclerView.Adapter<DataAdapterInapRm.ViewHolder> implements Filterable {
    private ArrayList<InputRmInap> android;
    private ArrayList<InputRmInap> mFilteredList;

    private String ttl;

    public DataAdapterInapRm(ArrayList<InputRmInap> android) {
        mFilteredList = android;
        this.android = android;
    }

    @Override
    public DataAdapterInapRm.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_inap_rm, viewGroup, false);
        return new DataAdapterInapRm.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterInapRm.ViewHolder viewHolder, int i) {
        SimpleDateFormat formatFromString = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatConvert = new SimpleDateFormat("dd MMMM yyyy");


        try {
            Date date = formatFromString.parse(android.get(i).getWaktu());
            ttl = formatConvert.format(date);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        viewHolder.tv_waktu.setText(ttl);
        viewHolder.tv_nama_hewan_rm.setText(android.get(i).getNamaHewan());
        viewHolder.tv_nama_pemilik_rm.setText(android.get(i).getNamaPemilik());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_waktu, tv_nama_hewan_rm, tv_nama_pemilik_rm;

        public ViewHolder(View view) {
            super(view);

            tv_waktu = (TextView) view.findViewById(R.id.tv_waktu_inap_rm);
            tv_nama_hewan_rm = (TextView) view.findViewById(R.id.tv_nama_hewan_inap_rm);
            tv_nama_pemilik_rm = (TextView) view.findViewById(R.id.tv_nama_pemilik_inap_rm);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    android = mFilteredList;
                } else {

                    ArrayList<InputRmInap> filteredList = new ArrayList<>();

                    for (InputRmInap inputRmInap : mFilteredList) {

                        if (inputRmInap.getWaktu().toLowerCase().contains(charString) || inputRmInap.getNamaPemilik().toLowerCase().contains(charString) || inputRmInap.getNamaHewan().toLowerCase().contains(charString)) {

                            filteredList.add(inputRmInap);
                        }
                    }

                    android = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = android;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                android = (ArrayList<InputRmInap>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
