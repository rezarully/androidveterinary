package com.rsh.android.rekammedisbosimrsh.rekammedis;

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

import com.rsh.android.rekammedisbosimrsh.DataAdapterRekamMedis;
import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.RecyclerTouchListener;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRm;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedis;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekamMedis extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<InputRm> data;
    private DataAdapterRekamMedis adapter;
    private TextView textError;

    private ProgressDialog progress;

    public static final String ID_RM = "id";

    InputRm inputRm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis);

        progress = new ProgressDialog(RekamMedis.this);
        progress.setCancelable(true);
        progress.setMessage("Loading...");
        progress.show();

        textError = (TextView) findViewById(R.id.tv_error_list_rm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_rm);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_rm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RekamMedis.this, RekamMedisTambah.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(RekamMedis.this, recyclerView, new com.rsh.android.rekammedisbosimrsh.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Values are passing to activity & to fragment as well

                Toast.makeText(RekamMedis.this, "Press on position : " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RekamMedis.this, RekamMedisLihat.class);

                InputRm inputRm = data.get(position);

                intent.putExtra(ID_RM, inputRm.getId());

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

                final CharSequence[] dialogitem = {"Lihat Rekam Medis", "Hapus Rekam Medis"};
                AlertDialog.Builder builder = new AlertDialog.Builder(RekamMedis.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Intent intentLihat = new Intent(RekamMedis.this, RekamMedisLihat.class);
                                inputRm = data.get(position);
                                intentLihat.putExtra(ID_RM, inputRm.getId());
                                startActivity(intentLihat);
                                break;

                            case 1:
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RekamMedis.this);
                                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                                alertDialogBuilder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                progress = new ProgressDialog(RekamMedis.this);
                                                progress.setCancelable(false);
                                                progress.setMessage("Loading...");
                                                progress.show();

                                                inputRm = data.get(position);
                                                String idRm = inputRm.getId();
                                                hapusRm(idRm);
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
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<ModelInputRm> call = apiService.loadListRekamMedis();
        call.enqueue(new Callback<ModelInputRm>() {
            @Override
            public void onResponse(Call<ModelInputRm> call, Response<ModelInputRm> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    ModelInputRm jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getInputRm()));
                    adapter = new DataAdapterRekamMedis(data);
                    recyclerView.setAdapter(adapter);
                } else {
                    progress.dismiss();
                    textError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ModelInputRm> call, Throwable t) {
                Log.d("Error", t.getMessage() + " ");
            }
        });
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_rekam_medis);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void hapusRm(String idPasien) {
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<InputRm> call = apiService.deleteRekamMedis(idPasien);
        call.enqueue(new Callback<InputRm>() {
            @Override
            public void onResponse(Call<InputRm> call, Response<InputRm> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(RekamMedis.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(RekamMedis.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InputRm> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(RekamMedis.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
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
                progress = new ProgressDialog(RekamMedis.this);
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
