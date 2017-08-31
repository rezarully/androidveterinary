package com.rsh.android.rekammedisbosimrsh.rekammedis;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRmSingle;
import com.rsh.android.rekammedisbosimrsh.pasien.PasienRekamMedis;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedis;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekamMedisLihat extends AppCompatActivity {

    private TextView
            textIdRegistrasi,
            textNamaHewan,
            textNamaPemilik,
            textTenagaMedis,
            textNamaMahasiswa,
            textAnamnesis,
            textKeadaanUmum,
            textFrekuensiNafas,
            textFrekuensiPulsus,
            textTemperaturTubuh,
            textKulitRambut,
            textSelaputLendir,
            textKelenjarLimfa,
            textPernafasan,
            textPeredaranDarah,
            textPencernaan,
            textKelaminPerkencingan,
            textSyaraf,
            textAnggotaGerak,
            textBeratBadan,
            textLainAnamnesis,
            textNamaPemeriksaan,
            textKetLab,
            textDiagnosis,
            textPrognosis,
            textNamaObat,
            textJumlahResep,
            textSatuanResep,
            textPerintahPembuatan,
            textPetunjukPenggunaan,
            textNamaTindakan,
            textJumlahTindakan,
            textKetTindakan;

    private ImageView foto1, foto2, foto3, foto4;

    private String
            idRm, idRm2,
            idRegistrasi,
            namaPemilik,
            namaHewan,
            tenagaMedis,
            namaMahasiswa,
            anamnesis,
            keadaanUmum,
            frekuensiNafas,
            frekuensiPulsus,
            temperaturTubuh,
            kulitRambut,
            selaputLendir,
            kelenjarLimfa,
            pernafasan,
            peredaranDarah,
            pencernaan,
            kelaminPerkencingan,
            syaraf,
            anggotaGerak,
            beratBadan,
            lainAnamnesis,
            namaPemeriksaan,
            ketLab,
            diagnosis,
            prognosis,
            namaObat,
            jumlahResep,
            satuanResep,
            perintahPembuatan,
            petunjukPenggunaan,
            namaTindakan,
            jumlahTindakan,
            ketTindakan, foto1Get, foto2Get, foto3Get, foto4Get;

    boolean isImageFitToScreen;

    public static final String ID = "id";

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis_lihat);

        textIdRegistrasi = (TextView) findViewById(R.id.tv_rm_id_regstrasi);
        textNamaPemilik = (TextView) findViewById(R.id.tv_rm_nama_pemilik);
        textNamaHewan = (TextView) findViewById(R.id.tv_rm_nama_hewan);
        textTenagaMedis = (TextView) findViewById(R.id.tv_rm_nama_tenaga_medis);
        textNamaMahasiswa = (TextView) findViewById(R.id.tv_rm_nama_mahasiswa);
        textAnamnesis = (TextView) findViewById(R.id.tv_rm_anamnesis);
        textKeadaanUmum = (TextView) findViewById(R.id.tv_rm_keadaan_umum);
        textFrekuensiNafas = (TextView) findViewById(R.id.tv_rm_frek_nafas);
        textFrekuensiPulsus = (TextView) findViewById(R.id.tv_rm_frek_pulsus);
        textTemperaturTubuh = (TextView) findViewById(R.id.tv_rm_temperatur_tubuh);
        textKulitRambut = (TextView) findViewById(R.id.tv_rm_kulit_rambut);
        textSelaputLendir = (TextView) findViewById(R.id.tv_rm_selaput_lendir);
        textKelenjarLimfa = (TextView) findViewById(R.id.tv_rm_kelenjar_limfe);
        textPernafasan = (TextView) findViewById(R.id.tv_rm_pernafasan);
        textPeredaranDarah = (TextView) findViewById(R.id.tv_rm_peredaran_darah);
        textPencernaan = (TextView) findViewById(R.id.tv_rm_pencernaan);
        textKelaminPerkencingan = (TextView) findViewById(R.id.tv_rm_kelamin_perkencingan);
        textSyaraf = (TextView) findViewById(R.id.tv_rm_syaraf);
        textAnggotaGerak = (TextView) findViewById(R.id.tv_rm_anggota_gerak);
        textBeratBadan = (TextView) findViewById(R.id.tv_rm_berat_badan);
        textLainAnamnesis = (TextView) findViewById(R.id.tv_rm_lain_anamnesis);
        textNamaPemeriksaan = (TextView) findViewById(R.id.tv_rm_nama_pmeriksaan);
        textKetLab = (TextView) findViewById(R.id.tv_rm_ket_lab);
        textDiagnosis = (TextView) findViewById(R.id.tv_rm_diagnosis);
        textPrognosis = (TextView) findViewById(R.id.tv_rm_prognosis);
        textNamaObat = (TextView) findViewById(R.id.tv_rm_nama_obat);
        textJumlahResep = (TextView) findViewById(R.id.tv_rm_jumlah_resep);
        textSatuanResep = (TextView) findViewById(R.id.tv_rm_satuan_resep);
        textPerintahPembuatan = (TextView) findViewById(R.id.tv_rm_perintah_pembuatan);
        textPetunjukPenggunaan = (TextView) findViewById(R.id.tv_rm_petunjuk_penggunaan);
        textNamaTindakan = (TextView) findViewById(R.id.tv_rm_nama_tindakan);
        textJumlahTindakan = (TextView) findViewById(R.id.tv_rm_qty_tindakan);
        textKetTindakan = (TextView) findViewById(R.id.tv_rm_ket_tindakan);

        foto1 = (ImageView) findViewById(R.id.image_rm_foto1);
        foto2 = (ImageView) findViewById(R.id.image_rm_foto2);
        foto3 = (ImageView) findViewById(R.id.image_rm_foto3);
        foto4 = (ImageView) findViewById(R.id.image_rm_foto4);

        final Intent intent = getIntent();

        idRm = intent.getStringExtra(RekamMedis.ID_RM);
        idRm2 = intent.getStringExtra(PasienRekamMedis.ID_PASIEN_RM);

        if (TextUtils.isEmpty(idRm2)) {
            loadRm(idRm);
            Log.i("TEST", idRm);
        } else {
            loadRm(idRm2);
            Log.i("TEST", idRm2);
        }

        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    foto1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    foto1.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    foto1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    foto1.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
    }

    private void loadRm(String idRm) {
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<ModelInputRmSingle> call = apiService.loadRm(idRm);
        call.enqueue(new Callback<ModelInputRmSingle>() {
            @Override
            public void onResponse(Call<ModelInputRmSingle> call, Response<ModelInputRmSingle> response) {
                if (response.isSuccessful()) {

                    ModelInputRmSingle inputRm = response.body();

                    idRegistrasi = inputRm.getInputRm().getIdRegistrasi();
                    namaPemilik = inputRm.getInputRm().getNamaPemilik();
                    namaHewan = inputRm.getInputRm().getNamaHewan();
                    tenagaMedis = inputRm.getInputRm().getNamaTenagaMedis();
                    namaMahasiswa = inputRm.getInputRm().getNamaMahasiswa();
                    anamnesis = inputRm.getInputRm().getAnamnesis();
                    keadaanUmum = inputRm.getInputRm().getKeadaanUmum();
                    frekuensiNafas = inputRm.getInputRm().getFrekNafas();
                    frekuensiPulsus = inputRm.getInputRm().getFrekPulsus();
                    temperaturTubuh = inputRm.getInputRm().getTemperaturTubuh();
                    kulitRambut = inputRm.getInputRm().getKulitRambut();
                    selaputLendir = inputRm.getInputRm().getSelaputLendir();
                    kelenjarLimfa = inputRm.getInputRm().getKelenjarLimfe();
                    pernafasan = inputRm.getInputRm().getPernafasan();
                    peredaranDarah = inputRm.getInputRm().getPeredaranDarah();
                    pencernaan = inputRm.getInputRm().getPencernaan();
                    kelaminPerkencingan = inputRm.getInputRm().getKelaminPerkencingan();
                    syaraf = inputRm.getInputRm().getSyaraf();
                    anggotaGerak = inputRm.getInputRm().getAnggotaGerak();
                    beratBadan = inputRm.getInputRm().getBeratBadan();
                    lainAnamnesis = inputRm.getInputRm().getLainAnamnesis();
                    namaPemeriksaan = inputRm.getInputRm().getNamaPemeriksaan();
                    ketLab = inputRm.getInputRm().getKetLab();
                    diagnosis = inputRm.getInputRm().getDiagnosis();
                    prognosis = inputRm.getInputRm().getPrognosis();
                    namaObat = inputRm.getInputRm().getNamaObat();
                    jumlahResep = inputRm.getInputRm().getJumlahResep();
                    satuanResep = inputRm.getInputRm().getSatuanResep();
                    perintahPembuatan = inputRm.getInputRm().getPerintahPembuatan();
                    petunjukPenggunaan = inputRm.getInputRm().getPetunjukPenggunaan();
                    namaTindakan = inputRm.getInputRm().getNamaTindakan();
                    jumlahTindakan = inputRm.getInputRm().getQtyTindakan();
                    ketTindakan = inputRm.getInputRm().getKetTindakan();
                    foto1Get = inputRm.getInputRm().getFoto1();
                    foto2Get = inputRm.getInputRm().getFoto2();
                    foto3Get = inputRm.getInputRm().getFoto3();
                    foto4Get = inputRm.getInputRm().getFoto4();

                    Log.i("TEST", foto3Get);

                    showRm(namaPemilik,
                            namaHewan,
                            tenagaMedis,
                            namaMahasiswa,
                            anamnesis,
                            keadaanUmum,
                            frekuensiNafas,
                            frekuensiPulsus,
                            temperaturTubuh,
                            kulitRambut,
                            selaputLendir,
                            kelenjarLimfa,
                            pernafasan,
                            peredaranDarah,
                            pencernaan,
                            kelaminPerkencingan,
                            syaraf,
                            anggotaGerak,
                            beratBadan,
                            lainAnamnesis,
                            namaPemeriksaan,
                            ketLab,
                            diagnosis,
                            prognosis,
                            namaObat,
                            jumlahResep,
                            satuanResep,
                            perintahPembuatan,
                            petunjukPenggunaan,
                            namaTindakan,
                            jumlahTindakan,
                            ketTindakan, foto1Get, foto2Get, foto3Get, foto4Get);
                }
            }

            @Override
            public void onFailure(Call<ModelInputRmSingle> call, Throwable t) {

            }
        });
    }

    private void showRm(String namaPemilik,
                        String namaHewan,
                        String tenagaMedis,
                        String namaMahasiswa,
                        String anamnesis,
                        String keadaanUmum,
                        String frekuensiNafas,
                        String frekuensiPulsus,
                        String temperaturTubuh,
                        String kulitRambut,
                        String selaputLendir,
                        String kelenjarLimfa,
                        String pernafasan,
                        String peredaranDarah,
                        String pencernaan,
                        String kelaminPerkencingan,
                        String syaraf,
                        String anggotaGerak,
                        String beratBadan,
                        String lainAnamnesis,
                        String namaPemeriksaan,
                        String ketLab,
                        String diagnosis,
                        String prognosis,
                        String namaObat,
                        String jumlahResep,
                        String satuanResep,
                        String perintahPembuatan,
                        String petunjukPenggunaan,
                        String namaTindakan,
                        String jumlahTindakan,
                        String ketTindakan, String foto1Get, String foto2Get, String foto3Get, String foto4Get) {

        Context context = getApplicationContext();

        textIdRegistrasi.setText(idRegistrasi);
        textNamaPemilik.setText(namaPemilik);
        textNamaHewan.setText(namaHewan);
        textTenagaMedis.setText(tenagaMedis);
        textNamaMahasiswa.setText(namaMahasiswa);
        textAnamnesis.setText(anamnesis);
        textKeadaanUmum.setText(keadaanUmum);
        textFrekuensiNafas.setText(frekuensiNafas);
        textFrekuensiPulsus.setText(frekuensiPulsus);
        textTemperaturTubuh.setText(temperaturTubuh);
        textKulitRambut.setText(kulitRambut);
        textSelaputLendir.setText(selaputLendir);
        textKelenjarLimfa.setText(kelenjarLimfa);
        textPernafasan.setText(pernafasan);
        textPeredaranDarah.setText(peredaranDarah);
        textPencernaan.setText(pencernaan);
        textKelaminPerkencingan.setText(kelaminPerkencingan);
        textSyaraf.setText(syaraf);
        textAnggotaGerak.setText(anggotaGerak);
        textBeratBadan.setText(beratBadan);
        textLainAnamnesis.setText(lainAnamnesis);
        textNamaPemeriksaan.setText(namaPemeriksaan);
        textKetLab.setText(ketLab);
        textDiagnosis.setText(diagnosis);
        textPrognosis.setText(prognosis);
        textNamaObat.setText(namaObat);
        textJumlahResep.setText(jumlahResep);
        textSatuanResep.setText(satuanResep);
        textPerintahPembuatan.setText(perintahPembuatan);
        textPetunjukPenggunaan.setText(petunjukPenggunaan);
        textNamaTindakan.setText(namaTindakan);
        textJumlahTindakan.setText(jumlahTindakan);
        textKetTindakan.setText(ketTindakan);

        if (foto1Get.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto1);
        } else {
            Picasso.with(context)
                    .load(foto1Get)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto1);
        }

        if (foto2Get.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto2);
        } else {
            Picasso.with(context)
                    .load(foto2Get)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto2);
        }

        if (foto3Get.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto3);
        } else {
            Picasso.with(context)
                    .load(foto3Get)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto3);
        }

        if (foto4Get.isEmpty()) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto4);
        } else {
            Picasso.with(context)
                    .load(foto4Get)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(foto4);
        }

    }

    private void hapusPasien(String idPasien) {
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<InputRm> call = apiService.deleteRekamMedis(idPasien);
        call.enqueue(new Callback<InputRm>() {
            @Override
            public void onResponse(Call<InputRm> call, Response<InputRm> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(RekamMedisLihat.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(RekamMedisLihat.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InputRm> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(RekamMedisLihat.this, "Gagal memuat", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_input.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_lihat_rekam_medis, menu);
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
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RekamMedisLihat.this);
                alertDialogBuilder.setMessage("Apakah anda yakin untuk menghapus?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                progress = new ProgressDialog(RekamMedisLihat.this);
                                progress.setCancelable(false);
                                progress.setMessage("Loading...");
                                progress.show();

//                String idPasien = intent.getStringExtra(Pasien.ID);
                                hapusPasien(idRm);
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
}
