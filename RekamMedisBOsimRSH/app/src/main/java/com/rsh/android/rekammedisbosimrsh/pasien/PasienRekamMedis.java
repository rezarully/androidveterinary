package com.rsh.android.rekammedisbosimrsh.pasien;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRmPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSingle;
import com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedisLihat;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsh.android.rekammedisbosimrsh.DateConverter.*;

public class PasienRekamMedis extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private TextView tvPasienRekamMedis;
    private List<InputRm> pasienRm;

    public static final String ID_PASIEN_RM = "idPasienRm";
    public static final String NAMA_PEMILIK_RM = "namaPemilikRm";
    public static final String NAMA_HEWAN_RM = "namaHewanRm";

    private String idPasien,idRegistrasi,namaPemilik,namaHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_rekam_medis);

        tvPasienRekamMedis = (TextView) findViewById(R.id.tv_pasien_rm);
        listView = (ListView) findViewById(R.id.list_view_pasien_rekam_medis);
        listView.setOnItemClickListener(PasienRekamMedis.this);

        final Intent intent = getIntent();

        idPasien = intent.getStringExtra(PasienLihat.ID);

        Log.i("TAG", idPasien);

        getPasienRm(idPasien);

        loadPasien(idPasien);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pasien_rm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasienRekamMedis.this, PasienTambahRekamMedis.class);
                intent.putExtra(ID_PASIEN_RM,idRegistrasi);
                intent.putExtra(NAMA_PEMILIK_RM,namaPemilik);
                intent.putExtra(NAMA_HEWAN_RM,namaHewan);
                startActivity(intent);
            }
        });
    }

    private void getPasienRm(String idPasien) {
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<ModelInputRmPasien> call = apiService.loadListRekamMedisPasien(idPasien);
        call.enqueue(new Callback<ModelInputRmPasien>() {
            @Override
            public void onResponse(Call<ModelInputRmPasien> call, Response<ModelInputRmPasien> response) {

                if (response.isSuccessful()){
                    List<InputRm> patientRm = response.body().getInputRm();

                    pasienRm = patientRm;

                    showList();
                } else {
                    /*try {
                        String errpr = response.errorBody().string();
                        tvPasienRekamMedis.setText(errpr);
                        tvPasienRekamMedis.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    tvPasienRekamMedis.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ModelInputRmPasien> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server sedang sibuk", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void loadPasien(final String idPasien) {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<ModelPasienSingle> call = apiService.loadPasien(idPasien);
        call.enqueue(new Callback<ModelPasienSingle>() {
            @Override
            public void onResponse(Call<ModelPasienSingle> call, Response<ModelPasienSingle> response) {
                ModelPasienSingle dataPasien = response.body();
//                final String idPasien = dataPasien.getDataPasien().getId();
                idRegistrasi = dataPasien.getDataPasien().getId();
                namaPemilik = dataPasien.getDataPasien().getNamaPemilik();
                namaHewan = dataPasien.getDataPasien().getNamaHewan();
            }

            @Override
            public void onFailure(Call<ModelPasienSingle> call, Throwable t) {
                Toast.makeText(PasienRekamMedis.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(PasienRekamMedis.this,RekamMedisLihat.class);
        intent.putExtra(ID_PASIEN_RM,pasienRm.get(i).getId());
        startActivity(intent);
    }

    private void showList() {
        //String array untuk menyimpan nama semua nama buku
        String[] items = new String[pasienRm.size()];

        for (int i = 0; i < pasienRm.size(); i++) {
            String date = dateConvertPlus(pasienRm.get(i).getWaktu());
            items[i] = date;
        }
        //Membuat Array Adapter for listview
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_row_pasien_rm, items);

        //setting adapter untuk listview
        listView.setAdapter(adapter);

    }
}
