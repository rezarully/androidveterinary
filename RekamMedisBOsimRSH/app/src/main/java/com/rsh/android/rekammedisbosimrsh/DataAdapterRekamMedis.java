package com.rsh.android.rekammedisbosimrsh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by rezar on 20/04/2017.
 */

public class DataAdapterRekamMedis extends RecyclerView.Adapter<DataAdapterRekamMedis.ViewHolder> implements Filterable {
    private ArrayList<InputRm> android;
    private ArrayList<InputRm> mFilteredList;
    private ArrayList<InputRmInap> androidinap;
    private ArrayList<InputRmInap> mFilteredListInap;
    String ttl = null;

    public DataAdapterRekamMedis(ArrayList<InputRm> android) {
        this.android = android;
        mFilteredList = android;
    }

    @Override
    public DataAdapterRekamMedis.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_rekam_medis, viewGroup, false);
        return new DataAdapterRekamMedis.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterRekamMedis.ViewHolder viewHolder, int i) {
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
        viewHolder.tv_nama_pemilik.setText(android.get(i).getNamaPemilik());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_waktu, tv_nama_hewan_rm, tv_nama_pemilik, tv_jenis_hewan;

        public ViewHolder(View view) {
            super(view);

            tv_waktu = (TextView) view.findViewById(R.id.tv_waktu_rm);
            tv_nama_hewan_rm = (TextView) view.findViewById(R.id.tv_nama_hewan_rm);
            tv_nama_pemilik = (TextView) view.findViewById(R.id.tv_nama_pemilik_rm);

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

                    android = mFilteredList ;
                } else {

                    ArrayList<InputRm> filteredList = new ArrayList<>();

                    for (InputRm inputRm : mFilteredList) {

                        if (inputRm.getWaktu().toLowerCase().contains(charString) || inputRm.getNamaPemilik().toLowerCase().contains(charString) || inputRm.getNamaHewan().toLowerCase().contains(charString)) {

                            filteredList.add(inputRm);
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
                android = (ArrayList<InputRm>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
