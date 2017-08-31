package com.rsh.android.rekammedisbosimrsh.pasien;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.DataAdapter;
import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.RecyclerTouchListener;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasien;
import com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pasien extends AppCompatActivity {

    ProgressDialog progress;

    private RecyclerView recyclerView;
    private ArrayList<DataPasien> data;
    private DataAdapter adapter;

    private TextView textError;

    public static final String ID = "id";
    public static final String NAMA_PEMILIK = "namaPemilik";
    public static final String NAMA_HEWAN = "namaHewan";
    public static final String JENIS_HEWAN = "jenisHewan";
    public static final String USERNAME = "username";

    private DataPasien dataPasien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien);
        progress = new ProgressDialog(Pasien.this);
        progress.setCancelable(true);
        progress.setMessage("Loading...");
        progress.show();

        textError = (TextView) findViewById(R.id.tv_error_list_pasien);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initViews();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(Pasien.this, recyclerView, new com.rsh.android.rekammedisbosimrsh.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Values are passing to activity & to fragment as well

                Intent intent = new Intent(Pasien.this, PasienTambahRekamMedis.class);

                dataPasien = data.get(position);

                intent.putExtra(ID, dataPasien.getId());
                intent.putExtra(NAMA_PEMILIK, dataPasien.getNamaPemilik());
                intent.putExtra(NAMA_HEWAN, dataPasien.getNamaHewan());

                startActivity(intent);

//                ImageView picture=(ImageView)view.findViewById(R.id.picture);
//                picture.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(Pasien.this, "Single Click on Image :"+position,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onLongClick(View view, final int position) {
//                Toast.makeText(Pasien.this, "Long press on position :" + position,
//                        Toast.LENGTH_LONG).show();
//            }
                final CharSequence[] dialogitem = {"Lihat Pasien", "Hapus Pasien"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Pasien.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Intent intentLihat = new Intent(Pasien.this, PasienLihat.class);
                                dataPasien = data.get(position);
                                intentLihat.putExtra(ID, dataPasien.getId());
                                startActivity(intentLihat);
                                break;

                            case 1:
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Pasien.this);
                                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                                alertDialogBuilder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                progress = new ProgressDialog(Pasien.this);
                                                progress.setCancelable(false);
                                                progress.setMessage("Loading...");
                                                progress.show();

                                                dataPasien = data.get(position);
                                                String idPasien = dataPasien.getId();
                                                hapusPasien(idPasien);
//                String idPasien = intent.getStringExtra(Pasien.ID);
                                                hapusPasien(idPasien);
                                            }
                                        });

                                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertDialogBuilder.create().show();

                        }
                    }
                });
                builder.create().show();
            }
        }));

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pasien.this, PasienTambah.class);
                startActivity(intent);
            }
        });*/
    }

    protected void onStart() {
        super.onStart();
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();

    }

    private void loadJSON() {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<ModelPasien> call = apiService.loadListAntrian();
        call.enqueue(new Callback<ModelPasien>() {
            @Override
            public void onResponse(Call<ModelPasien> call, Response<ModelPasien> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    ModelPasien jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getDataPasien()));
                    adapter = new DataAdapter(data);
                    recyclerView.setAdapter(adapter);
                } else {
                    progress.dismiss();
                    textError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ModelPasien> call, Throwable t) {
                progress.dismiss();
                Log.d("Error", t.getMessage() + " ");
                Toast.makeText(Pasien.this, "Error : Gagal Memuat", Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Pasien.this);
                alertDialogBuilder.setMessage("Gagal memuat");
                alertDialogBuilder.setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        initViews();
                    }
                });
                alertDialogBuilder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
            }
        });
    }

    public void hapusPasien(String idPasien) {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<DataPasien> call = apiService.deletePasien(idPasien);
        call.enqueue(new Callback<DataPasien>() {
            @Override
            public void onResponse(Call<DataPasien> call, Response<DataPasien> response) {
                progress.dismiss();
                Toast.makeText(Pasien.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<DataPasien> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(Pasien.this, "Gagal dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                progress = new ProgressDialog(Pasien.this);
                progress.setCancelable(true);
                progress.setMessage("Loading...");
                progress.show();
                initViews();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}