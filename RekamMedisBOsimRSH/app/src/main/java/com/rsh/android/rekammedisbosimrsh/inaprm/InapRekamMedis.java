package com.rsh.android.rekammedisbosimrsh.inaprm;

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
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInap;
import com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedisInap;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis.ID_RM;

public class InapRekamMedis extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<InputRmInap> data;
    private DataAdapterInapRm adapter;
    private TextView textError;
    private ProgressDialog progress;

    public static final String ID_RM_INAP = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inap_rekam_medis);

        progress = new ProgressDialog(InapRekamMedis.this);
        progress.setCancelable(true);
        progress.setMessage("Loading...");
        progress.show();

        textError = (TextView) findViewById(R.id.tv_error_list_rm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_rm_inap);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_inap_rm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InapRekamMedis.this, InapRekamMedisTambah.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(InapRekamMedis.this, recyclerView, new com.rsh.android.rekammedisbosimrsh.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Values are passing to activity & to fragment as well

                Toast.makeText(InapRekamMedis.this, "Press on position : " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InapRekamMedis.this, InapRekamMedisLihat.class);

                InputRmInap inputRmInap = data.get(position);

                intent.putExtra(ID_RM_INAP, inputRmInap.getId());

                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, final int position) {

                final CharSequence[] dialogitem = {"Lihat Rekam Medis Inap", "Hapus Rekam Medis Inap"};
                AlertDialog.Builder builder = new AlertDialog.Builder(InapRekamMedis.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Intent intentLihat = new Intent(InapRekamMedis.this, InapRekamMedisLihat.class);
                                final InputRmInap inputRmInap = data.get(position);
                                intentLihat.putExtra(ID_RM, inputRmInap.getId());
                                startActivity(intentLihat);
                                break;

                            case 1:
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InapRekamMedis.this);
                                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                                alertDialogBuilder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                progress = new ProgressDialog(InapRekamMedis.this);
                                                progress.setCancelable(false);
                                                progress.setMessage("Loading...");
                                                progress.show();

                                                InputRmInap inputRmInap1 = data.get(position);
                                                String idRmInap = inputRmInap1.getId();
                                                hapusRm(idRmInap);
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
    }

    private void loadJSON() {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<ModelInputRmInap> call = apiService.loadListRekamMedis();
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

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_inap_rm);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void hapusRm(String idPasien) {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<InputRmInap> call = apiService.deleteRekamMedisInap(idPasien);
        call.enqueue(new Callback<InputRmInap>() {
            @Override
            public void onResponse(Call<InputRmInap> call, Response<InputRmInap> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(InapRekamMedis.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(InapRekamMedis.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InputRmInap> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(InapRekamMedis.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
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
                progress = new ProgressDialog(InapRekamMedis.this);
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
}
