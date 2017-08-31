package com.rsh.android.rekammedisbosimrsh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rezar on 29/03/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    Context context;

    private ArrayList<DataPasien> android;
    private ArrayList<DataPasien> mFilteredList;

    private String ttl;

    public DataAdapter(ArrayList<DataPasien> android) {
        this.android = android;
        mFilteredList = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        context = viewGroup.getContext();
        this.context = context;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
/*
        Picasso.with(context).load(android.get(i).getFotoProfil()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.img_profil);
*/
        SimpleDateFormat formatFromString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatConvert = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");

        try {
            Date date = formatFromString.parse(android.get(i).getWaktu());
            ttl = formatConvert.format(date);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        viewHolder.tvNamaPemilik.setText(android.get(i).getNamaPemilik());
        viewHolder.tvNamaHewan.setText(android.get(i).getNamaHewan());
        viewHolder.tvJenisHewan.setText(android.get(i).getJenisHewan());
        viewHolder.tv_waktu.setText(ttl);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNamaPemilik, tvNamaHewan, tvJenisHewan, tv_waktu;
        private ImageView img_profil;

        public ViewHolder(View view) {
            super(view);

            /*img_profil = (ImageView) view.findViewById(R.id.image_profil);*/
            tvNamaPemilik = (TextView) view.findViewById(R.id.tv_nama_pemilik_pasien);
            tvNamaHewan = (TextView) view.findViewById(R.id.tv_nama_hewan_pasien);
            tvJenisHewan = (TextView) view.findViewById(R.id.tv_jenis_hewan_pasien);
            tv_waktu = (TextView) view.findViewById(R.id.tv_time);
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

                    ArrayList<DataPasien> filteredList = new ArrayList<>();

                    for (DataPasien dataPasien : mFilteredList) {

                        if (dataPasien.getId().toLowerCase().contains(charString) || dataPasien.getNamaPemilik().toLowerCase().contains(charString) || dataPasien.getNamaHewan().toLowerCase().contains(charString)) {

                            filteredList.add(dataPasien);
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
                android = (ArrayList<DataPasien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}