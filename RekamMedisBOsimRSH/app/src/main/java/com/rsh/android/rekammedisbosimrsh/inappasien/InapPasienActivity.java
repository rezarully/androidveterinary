package com.rsh.android.rekammedisbosimrsh.inappasien;

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

import com.rsh.android.rekammedisbosimrsh.DataAdapterInapRm;
import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.RecyclerTouchListener;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedis;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedisLihat;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedisTambah;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInap;
import com.rsh.android.rekammedisbosimrsh.pasien.PasienLihat;
import com.rsh.android.rekammedisbosimrsh.pasien.PasienRekamMedisInap;
import com.rsh.android.rekammedisbosimrsh.pasien.PasienTambahRekamMedisInap;
import com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedisInap;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis.ID_RM;

public class InapPasienActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<InputRmInap> data;
    private DataAdapterInapRm adapter;
    private TextView textError;
    private ProgressDialog progress;

    public static final String ID_PASIEN_INAP = "idInap";
    public static final String NAMA_HEWAN_PASIEN_INAP = "namaHewanInap";
    public static final String NAMA_PEMILIK_PASIEN_INAP = "namaPemilikInap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inap_pasien);

        progress = new ProgressDialog(InapPasienActivity.this);
        progress.setCancelable(true);
        progress.setMessage("Loading...");
        progress.show();

        textError = (TextView) findViewById(R.id.tv_error_list_inap_pasien);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_inap_pasien);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_inap_pasien);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InapPasienActivity.this, InapRekamMedisTambah.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(InapPasienActivity.this, recyclerView, new com.rsh.android.rekammedisbosimrsh.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                final InputRmInap inputRmInap1 = data.get(position);
                final String idRmInap = inputRmInap1.getIdRegistrasi();
                final CharSequence[] dialogitem = {"Show Inpatient History", "Add Inpatient Medical Recorde", "Inpatient Done"};
                AlertDialog.Builder builder = new AlertDialog.Builder(InapPasienActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Intent intentRmInap = new Intent(InapPasienActivity.this, PasienRekamMedisInap.class);
                                intentRmInap.putExtra(ID_PASIEN_INAP, idRmInap);
                                startActivity(intentRmInap);
                                break;

                            case 1:
                                Intent intentLihat = new Intent(InapPasienActivity.this, PasienTambahRekamMedisInap.class);
                                /*final InputRmInap inputRmInap = data.get(position);*/
                                intentLihat.putExtra(ID_PASIEN_INAP, idRmInap);
                                intentLihat.putExtra(NAMA_PEMILIK_PASIEN_INAP, inputRmInap1.getNamaPemilik());
                                intentLihat.putExtra(NAMA_HEWAN_PASIEN_INAP, inputRmInap1.getNamaHewan());
                                startActivity(intentLihat);
                                break;

                            case 2:
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InapPasienActivity.this);
                                alertDialogBuilder.setMessage("Apakah anda yakin untuk selesai?");
                                alertDialogBuilder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                progress = new ProgressDialog(InapPasienActivity.this);
                                                progress.setCancelable(false);
                                                progress.setMessage("Loading...");
                                                progress.show();

                                                String statusInap = "0";
                                                updateInap(idRmInap,statusInap);
                                            }
                                        });

                                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertDialogBuilder.create().show();
                                break;
                        }
                    }
                });
                builder.create().show();

            }

            @Override
            public void onLongClick(View view, final int position) {


            }
        }));
    }

    private void loadJSON() {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<ModelInputRmInap> call = apiService.loadListInap();
        call.enqueue(new Callback<ModelInputRmInap>() {
            @Override
            public void onResponse(Call<ModelInputRmInap> call, Response<ModelInputRmInap> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    ModelInputRmInap jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getInputRmInap()));
                    adapter = new DataAdapterInapRm(data);
                    recyclerView.setAdapter(adapter);
                } else {
                    progress.dismiss();
                    textError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ModelInputRmInap> call, Throwable t) {
                Log.d("Error", t.getMessage() + " ");
            }
        });
    }

    public void updateInap(String idRegistrasi, String statusInap){
        RestApiRekamMedisInap updateInap = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<InputRmInap> call = updateInap.updatePost(idRegistrasi,statusInap);
        call.enqueue(new Callback<InputRmInap>() {
            @Override
            public void onResponse(Call<InputRmInap> call, Response<InputRmInap> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InputRmInap> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_inap_pasien);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
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
                progress = new ProgressDialog(InapPasienActivity.this);
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
