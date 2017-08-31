package com.rsh.android.rekammedisbosimrsh.inaprm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInapSingle;
import com.rsh.android.rekammedisbosimrsh.pasien.PasienRekamMedisInap;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedisInap;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InapRekamMedisLihat extends AppCompatActivity {

    private TextView textWaktu, textIdRegistrasi, textNamaPemilik, textNamaHewan, textDiagnosis, textBeratBaadan, textNamaMahasiswa,
            textSemMahasiswa, textNamaObat, textJumlahResep, textSatuanResep, textPerintahPembuatan, textPetunjukPenggunaan,
            textPssm, textPengobatan, textKetPengobatan, textStatusInap;

    private ImageView imageFoto1, imageFoto2, imageFoto3, imageFoto4;

    private String waktu;
    private String id;
    private String idRmInap;
    private String idRmInap2;
    private String idRegistrasi;
    private String namaPemilik;
    private String namaHewan;
    private String diagnosis;
    private String beratBadan;
    private String namaMahasiswa;
    private String semesterMahasiswa;
    private String namaObat;
    private String jumlahResep;
    private String satuanResep;
    private String perintahPembuatan;
    private String petunjukPenggunaan;
    private String pssm;
    private String pengobatan;
    private String ketPengobatan;
    private String statusInap;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inap_rekam_medis_lihat);

        textIdRegistrasi = (TextView) findViewById(R.id.tv_rm_inap_id_regstrasi);
        textNamaPemilik = (TextView) findViewById(R.id.tv_rm_inap_nama_pemilik);
        textNamaHewan = (TextView) findViewById(R.id.tv_rm_inap_nama_hewan);
        textDiagnosis = (TextView) findViewById(R.id.tv_rm_inap_diagnosis);
        textBeratBaadan = (TextView) findViewById(R.id.tv_rm_inap_berat_badan);
        textNamaMahasiswa = (TextView) findViewById(R.id.tv_rm_inap_nama_mahasiswa);
        textSemMahasiswa = (TextView) findViewById(R.id.tv_rm_inap_semester_mahasiswa);
        textNamaObat = (TextView) findViewById(R.id.tv_rm_inap_nama_obat);
        textJumlahResep = (TextView) findViewById(R.id.tv_rm_inap_jumlah_resep);
        textSatuanResep = (TextView) findViewById(R.id.tv_rm_inap_satuan_resep);
        textPerintahPembuatan = (TextView) findViewById(R.id.tv_rm_inap_perintah_pembuatan);
        textPetunjukPenggunaan = (TextView) findViewById(R.id.tv_rm_inap_petunjuk_penggunaan);
        textPssm = (TextView) findViewById(R.id.tv_rm_inap_pssm);
        textPengobatan = (TextView) findViewById(R.id.tv_rm_inap_pengobatan);
        textKetPengobatan = (TextView) findViewById(R.id.tv_rm_inap_ket_pengobatan);
        textStatusInap = (TextView) findViewById(R.id.tv_rm_inap_status_inap);

        imageFoto1 = (ImageView) findViewById(R.id.image_rm_inap_foto_1);
        imageFoto2 = (ImageView) findViewById(R.id.image_rm_inap_foto_2);
        imageFoto3 = (ImageView) findViewById(R.id.image_rm_inap_foto_3);
        imageFoto4 = (ImageView) findViewById(R.id.image_rm_inap_foto_4);

        final Intent intent = getIntent();
        idRmInap = intent.getStringExtra(InapRekamMedis.ID_RM_INAP);
        idRmInap2 = intent.getStringExtra(PasienRekamMedisInap.ID_PASIEN_RM_INAP);

        if (TextUtils.isEmpty(idRmInap2)) {
            loadRmInap(idRmInap);
            Log.i("TEST", idRmInap);
        } else {
            loadRmInap(idRmInap2);
            Log.i("TEST", idRmInap2);
        }
    }

    private void loadRmInap(String idRmInap) {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<ModelInputRmInapSingle> call = apiService.loadRmInap(idRmInap);
        call.enqueue(new Callback<ModelInputRmInapSingle>() {
            @Override
            public void onResponse(Call<ModelInputRmInapSingle> call, Response<ModelInputRmInapSingle> response) {
                if (response.isSuccessful()) {

                    ModelInputRmInapSingle inputRmInapSingle = response.body();

                    idRegistrasi = inputRmInapSingle.getInputRmInap().getIdRegistrasi();
                    namaPemilik = inputRmInapSingle.getInputRmInap().getNamaPemilik();
                    namaHewan = inputRmInapSingle.getInputRmInap().getNamaHewan();
                    diagnosis = inputRmInapSingle.getInputRmInap().getDiagnosis();
                    beratBadan = inputRmInapSingle.getInputRmInap().getBeratBadan();
                    namaMahasiswa = inputRmInapSingle.getInputRmInap().getNamaMahasiswa();
                    semesterMahasiswa = inputRmInapSingle.getInputRmInap().getSemesterMahasiswa();
                    namaObat = inputRmInapSingle.getInputRmInap().getNamaObat();
                    jumlahResep = inputRmInapSingle.getInputRmInap().getJumlahResep();
                    satuanResep = inputRmInapSingle.getInputRmInap().getSatuanResep();
                    perintahPembuatan = inputRmInapSingle.getInputRmInap().getPerintahPembuatan();
                    petunjukPenggunaan = inputRmInapSingle.getInputRmInap().getPetunjukPenggunaan();
                    pssm = inputRmInapSingle.getInputRmInap().getPssm();
                    pengobatan = inputRmInapSingle.getInputRmInap().getPengobatan();
                    ketPengobatan = inputRmInapSingle.getInputRmInap().getKetPengobatan();
                    statusInap = inputRmInapSingle.getInputRmInap().getStatusInap();
                    foto1 = inputRmInapSingle.getInputRmInap().getFoto1();
                    foto2 = inputRmInapSingle.getInputRmInap().getFoto2();
                    foto3 = inputRmInapSingle.getInputRmInap().getFoto3();
                    foto4 = inputRmInapSingle.getInputRmInap().getFoto4();

                    showRmInap(idRegistrasi,
                            namaPemilik,
                            namaHewan,
                            diagnosis,
                            beratBadan,
                            namaMahasiswa,
                            semesterMahasiswa,
                            namaObat,
                            jumlahResep,
                            satuanResep,
                            perintahPembuatan,
                            petunjukPenggunaan,
                            pssm,
                            pengobatan,
                            ketPengobatan,
                            statusInap,
                            foto1,
                            foto2,
                            foto3,
                            foto4);

                }
            }

            @Override
            public void onFailure(Call<ModelInputRmInapSingle> call, Throwable t) {

            }
        });
    }

    private void showRmInap(String idRegistrasi,
                            String namaPemilik,
                            String namaHewan,
                            String diagnosis,
                            String beratBadan,
                            String namaMahasiswa,
                            String semesterMahasiswa,
                            String namaObat,
                            String jumlahResep,
                            String satuanResep,
                            String perintahPembuatan,
                            String petunjukPenggunaan,
                            String pssm,
                            String pengobatan,
                            String ketPengobatan,
                            String statusInap,
                            String foto1,
                            String foto2,
                            String foto3,
                            String foto4) {

        Context context = getApplicationContext();

        textIdRegistrasi.setText(idRegistrasi);
        textNamaPemilik.setText(namaPemilik);
        textNamaHewan.setText(namaHewan);
        textDiagnosis.setText(diagnosis);
        textBeratBaadan.setText(beratBadan);
        textNamaMahasiswa.setText(namaMahasiswa);
        textSemMahasiswa.setText(semesterMahasiswa);
        textNamaObat.setText(namaObat);
        textJumlahResep.setText(jumlahResep);
        textSatuanResep.setText(satuanResep);
        textPerintahPembuatan.setText(perintahPembuatan);
        textPetunjukPenggunaan.setText(petunjukPenggunaan);
        textPssm.setText(pssm);
        textPengobatan.setText(pengobatan);
        textKetPengobatan.setText(ketPengobatan);
        textStatusInap.setText(statusInap);

        if (foto1.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto1);
        } else {
            Picasso.with(context)
                    .load(foto1)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto1);
        }

        if (foto2.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto2);
        } else {
            Picasso.with(context)
                    .load(foto2)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto2);
        }

        if (foto3.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto3);
        } else {
            Picasso.with(context)
                    .load(foto3)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto3);
        }

        if (foto4.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto4);
        } else {
            Picasso.with(context)
                    .load(foto4)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageFoto4);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_input.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_lihat_rekam_medis_inap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            /*case R.id.action_edit_rm:
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete_rm:
                // Do nothing for now
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InapRekamMedisLihat.this);
                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                progress = new ProgressDialog(InapRekamMedisLihat.this);
                                progress.setCancelable(false);
                                progress.setMessage("Loading...");
                                progress.show();

//                String idPasien = intent.getStringExtra(Pasien.ID);
                                hapusRmInap(idRmInap);
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;*/

            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (PasienActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hapusRmInap(String idPasien) {
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<InputRmInap> call = apiService.deleteRekamMedisInap(idPasien);
        call.enqueue(new Callback<InputRmInap>() {
            @Override
            public void onResponse(Call<InputRmInap> call, Response<InputRmInap> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(InapRekamMedisLihat.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(InapRekamMedisLihat.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InputRmInap> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(InapRekamMedisLihat.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
