package com.rsh.android.rekammedisbosimrsh.pasien;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.inappasien.InapPasienActivity;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedisLihat;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInapPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSingle;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedisInap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienRekamMedisInap extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private TextView tvPasienRekamMedis;
    private List<InputRmInap> pasienRmInap;

    private String idPasien;

    public static final String ID_PASIEN_RM_INAP = "idPasienRmInap";
    public static final String NAMA_PEMILIK_RM_INAP = "namaPemilikRmInap";
    public static final String NAMA_HEWAN_RM_INAP = "namaHewanRmInap";

    private String idRegistrasi,namaPemilik,namaHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_rekam_medis_inap);

        tvPasienRekamMedis = (TextView) findViewById(R.id.tv_pasien_rm_inap);
        listView = (ListView) findViewById(R.id.list_view_pasien_rekam_medis_inap);
        listView.setOnItemClickListener(PasienRekamMedisInap.this);
        /*listView.setOnItemClickListener(PasienRekamMedisInap.this);*/

        final Intent intent = getIntent();

        idPasien = intent.getStringExtra(InapPasienActivity.ID_PASIEN_INAP);

        loadPasien(idPasien);

        getPasienRmInap(idPasien);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pasien_rm_inap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTambah = new Intent(PasienRekamMedisInap.this, PasienTambahRekamMedisInap.class);
                intentTambah.putExtra(ID_PASIEN_RM_INAP,idRegistrasi);
                intentTambah.putExtra(NAMA_PEMILIK_RM_INAP,namaPemilik);
                intentTambah.putExtra(NAMA_HEWAN_RM_INAP,namaHewan);
                startActivity(intentTambah);
            }
        });
    }

    private void getPasienRmInap(String idPasien) {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<ModelInputRmInapPasien> call = apiService.loadListRekamMedisPasienInap(idPasien);
        call.enqueue(new Callback<ModelInputRmInapPasien>() {
            @Override
            public void onResponse(Call<ModelInputRmInapPasien> call, Response<ModelInputRmInapPasien> response) {

                if (response.isSuccessful()){
                    List<InputRmInap> patientRmInap = response.body().getInputRmInap();

                    pasienRmInap = patientRmInap;

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
            public void onFailure(Call<ModelInputRmInapPasien> call, Throwable t) {
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
                Toast.makeText(PasienRekamMedisInap.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showList() {
        //String array untuk menyimpan nama semua nama buku
        String[] items = new String[pasienRmInap.size()];

        for (int i = 0; i < pasienRmInap.size(); i++) {
            items[i] = pasienRmInap.get(i).getWaktu();
        }
        //Membuat Array Adapter for listview
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_row_pasien_rm, items);

        //setting adapter untuk listview
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(PasienRekamMedisInap.this, InapRekamMedisLihat.class);
        intent.putExtra(ID_PASIEN_RM_INAP,pasienRmInap.get(i).getId());
        startActivity(intent);
    }
}
