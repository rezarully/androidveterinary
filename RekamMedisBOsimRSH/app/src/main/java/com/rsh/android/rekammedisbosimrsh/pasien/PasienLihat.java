package com.rsh.android.rekammedisbosimrsh.pasien;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSingle;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienLihat extends AppCompatActivity {

    private TextView tvID;
    private TextView tvUsername;
    private TextView tvNamaHewan;
    private TextView tvNamaPemilik;
    private TextView tvJenisHewan;
    private TextView tvNoRegistrasi;
    private TextView tvNoRm;
    private TextView tvSignalemenKelamin;
    private TextView tvSignalemenTtl;
    private TextView tvStatusAntrian;
    Button hapusBtn;
    Button ubahBtn;

    private String idPasien;
    private String noRegistrasi;
    private String noRm;
    private String namaPemilik;
    private String namaHewan;
    private String jenisHewan;
    private String signalemenTtl;
    private String signalemenKelamin;
    private String username;
    private String statusAntrian;
    private String fotoProfil;

    private ImageView image_profil;

    public static final String ID = "id";
    public static final String NO_REGISTRASI = "noRegistrasi";
    public static final String NO_REKAM_MEDIS = "noRm";
    public static final String NAMA_PEMILIK = "namaPemilik";
    public static final String NAMA_HEWAN = "namaHewan";
    public static final String JENIS_HEWAN = "jenisHewan";
    public static final String SIGNALEMEN_KELAMIN = "kelamin";
    public static final String SIGNALEMEN_TTL = "ttl";
    public static final String USERNAME = "username";
    public static final String STATUS_ANTRIAN = "statAntrian";


    private ProgressDialog progress;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_lihat);

//        tvID = (TextView) findViewById(R.id.tv_id);
        tvNoRegistrasi = (TextView) findViewById(R.id.tv_no_registrasi);
        tvNoRm = (TextView) findViewById(R.id.tv_no_rm);
//        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvNamaPemilik = (TextView) findViewById(R.id.tv_nama_pemilik);
        tvNamaHewan = (TextView) findViewById(R.id.tv_nama_hewan);
        tvJenisHewan = (TextView) findViewById(R.id.tv_jenis_hewan);
        tvSignalemenKelamin = (TextView) findViewById(R.id.tv_signalemen_kelamin);
        tvSignalemenTtl = (TextView) findViewById(R.id.tv_signalemen_ttl);
//        tvStatusAntrian = (TextView) findViewById(R.id.tv_status_antrian);

        image_profil = (ImageView) findViewById(R.id.img_profil);

        final Intent intent = getIntent();

        idPasien = intent.getStringExtra(Pasien.ID);

        loadPasien(idPasien);
    }

    private void loadPasien(final String idPasien) {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<ModelPasienSingle> call = apiService.loadPasien(idPasien);
        call.enqueue(new Callback<ModelPasienSingle>() {
            @Override
            public void onResponse(Call<ModelPasienSingle> call, Response<ModelPasienSingle> response) {
                if (response.isSuccessful()) {
                    ModelPasienSingle dataPasien = response.body();
//                final String idPasien = dataPasien.getDataPasien().getId();
                    noRegistrasi = dataPasien.getDataPasien().getNoRegistrasi();
                    noRm = dataPasien.getDataPasien().getNoRm();
                    namaPemilik = dataPasien.getDataPasien().getNamaPemilik();
                    namaHewan = dataPasien.getDataPasien().getNamaHewan();
                    jenisHewan = dataPasien.getDataPasien().getJenisHewan();
                    signalemenKelamin = dataPasien.getDataPasien().getSignalemenKelamin();
                    signalemenTtl = dataPasien.getDataPasien().getSignalemenTtl();
                    username = dataPasien.getDataPasien().getUsername();
                    statusAntrian = dataPasien.getDataPasien().getStatusAntrian();
                    fotoProfil = dataPasien.getDataPasien().getFotoProfil();

                    showPasien(noRegistrasi, noRm, namaPemilik, namaHewan, jenisHewan, signalemenKelamin, signalemenTtl, username, statusAntrian, fotoProfil);
                } else {
                    Toast.makeText(PasienLihat.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPasienSingle> call, Throwable t) {
                Toast.makeText(PasienLihat.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPasien(String noRegistrasi, String noRm, String namaPemilik, String namaHewan, String jenisHewan, String signalemenKelamin, String signalemenTtl, String username, String statusAntrian, String fotoProfil) {
//        tvID.setText("ID = " + idPasien);
        Context context = getApplicationContext();

        SimpleDateFormat formatFromString = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatConvert = new SimpleDateFormat("dd MMMM yyyy");
        String ttl = null;

        try {
            Date date = formatFromString.parse(signalemenTtl);
            ttl = formatConvert.format(date);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        tvNoRegistrasi.setText(noRegistrasi);
        tvNoRm.setText(noRm);
        tvNamaPemilik.setText(namaPemilik);
        tvNamaHewan.setText(namaHewan);
        tvJenisHewan.setText(jenisHewan);
        tvSignalemenKelamin.setText(signalemenKelamin);
        tvSignalemenTtl.setText(ttl);

        Picasso.with(context)
                .load(fotoProfil)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image_profil);
    }

    public void hapusPasien(String idPasien) {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<DataPasien> call = apiService.deletePasien(idPasien);
        call.enqueue(new Callback<DataPasien>() {
            @Override
            public void onResponse(Call<DataPasien> call, Response<DataPasien> response) {
                progress.dismiss();
                Toast.makeText(PasienLihat.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<DataPasien> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(PasienLihat.this, "Gagal dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_input.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_lihat_pasien, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_edit:
                // Do nothing for now
                Intent intent = new Intent(PasienLihat.this, PasienUpdate.class);

                intent.putExtra(ID, idPasien);
                intent.putExtra(NO_REGISTRASI, noRegistrasi);
                intent.putExtra(NO_REKAM_MEDIS, noRm);
                intent.putExtra(NAMA_PEMILIK, namaPemilik);
                intent.putExtra(NAMA_HEWAN, namaHewan);
                intent.putExtra(JENIS_HEWAN, jenisHewan);
                intent.putExtra(SIGNALEMEN_TTL, signalemenTtl);
                intent.putExtra(SIGNALEMEN_KELAMIN, signalemenKelamin);
                intent.putExtra(USERNAME, username);
                intent.putExtra(STATUS_ANTRIAN, statusAntrian);

                startActivity(intent);
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PasienLihat.this);
                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                progress = new ProgressDialog(PasienLihat.this);
                                progress.setCancelable(false);
                                progress.setMessage("Loading...");
                                progress.show();

//                String idPasien = intent.getStringExtra(Pasien.ID);
                                hapusPasien(idPasien);
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;

            case R.id.action_rekam_medis:
                Intent intentRm = new Intent(PasienLihat.this, PasienRekamMedis.class);
                intentRm.putExtra(ID, idPasien);
                startActivity(intentRm);
                return true;

            case R.id.action_rekam_medis_inap:
                Intent intentRmInap = new Intent(PasienLihat.this, PasienRekamMedisInap.class);
                intentRmInap.putExtra(ID, idPasien);
                startActivity(intentRmInap);
                return true;

            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (PasienActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
