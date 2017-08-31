package com.rsh.android.rekammedisbosimrsh.pasien;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.Api;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.Verify;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedisTambah;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSpinner;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Lab;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelLab;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelObat;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelTindakan;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Obat;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Tindakan;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedis;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiRekamMedisInap;
import com.rsh.android.rekammedisbosimrsh.rest.RestApiSpinner;
import com.rsh.android.rekammedisbosimrsh.volley.MyCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class PasienTambahRekamMedis extends AppCompatActivity {

    private EditText editTenagaMedis,
            editNamaMahasiswa,
            editAnamnesis,
            editKeadaanUmum,
            editFrekuensiNafas,
            editFrekuensiPulsus,
            editTemperaturTubuh,
            editKulitRambut,
            editSelaputLendir,
            editKelenjarLimfa,
            editPernafasan,
            editPeredaranDarah,
            editPencernaan,
            editKelaminPerkencingan,
            editSyaraf,
            editAnggotaGerak,
            editBeratBadan,
            editLainAnamnesis,
            editNamaPemeriksaan,
            editKetLab,
            editDiagnosis,
            editPrognosis,
            editNamaObat,
            editJumlahResep,
            editSatuanResep,
            editPerintahPembuatan,
            editPetunjukPenggunaan,
            editNamaTindakan,
            editJumlahTindakan,
            editKetTindakan;

    private String
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
            ketTindakan, foto1, foto2, foto3, foto4;

    private Button btnTambahResep1, btnTambahResep2, btnTambahResep3, btnTambahResep4,
            btnHapusResep2, btnHapusResep3, btnHapusResep4, btnHapusResep5,
            btnTambahObatR1O1, btnTambahObatR1O2, btnTambahObatR1O3, btnTambahObatR1O4,
            btnMinusObatR1O2, btnMinusObatR1O3, btnMinusObatR1O4, btnMinusObatR1O5,
            btnTambahObatR2O1, btnTambahObatR2O2, btnTambahObatR2O3, btnTambahObatR2O4,
            btnMinusObatR2O2, btnMinusObatR2O3, btnMinusObatR2O4, btnMinusObatR2O5,
            btnTambahObatR3O1, btnTambahObatR3O2, btnTambahObatR3O3, btnTambahObatR3O4,
            btnMinusObatR3O2, btnMinusObatR3O3, btnMinusObatR3O4, btnMinusObatR3O5,
            btnTambahObatR4O1, btnTambahObatR4O2, btnTambahObatR4O3, btnTambahObatR4O4,
            btnMinusObatR4O2, btnMinusObatR4O3, btnMinusObatR4O4, btnMinusObatR4O5,
            btnTambahObatR5O1, btnTambahObatR5O2, btnTambahObatR5O3, btnTambahObatR5O4,
            btnMinusObatR5O2, btnMinusObatR5O3, btnMinusObatR5O4, btnMinusObatR5O5;

    private Button btnTambahTindakan1, btnTambahTindakan2, btnTambahTindakan3, btnTambahTindakan4, btnTambahTindakan5,
            btnHapusTindakan1, btnHapusTindakan2, btnHapusTindakan3, btnHapusTindakan4, btnHapusTindakan5;

    private Button btnTambahLab1, btnTambahLab2, btnTambahLab3, btnTambahLab4, btnTambahLab5,
            btnHapusLab1, btnHapusLab2, btnHapusLab3, btnHapusLab4, btnHapusLab5;

    private Button btnTambahDiagnosis1, btnTambahDiagnosis2, btnTambahDiagnosis3, btnTambahDiagnosis4, btnTambahDiagnosis5,
            btnHapusDiagnosis1, btnHapusDiagnosis2, btnHapusDiagnosis3, btnHapusDiagnosis4, btnHapusDiagnosis5;

    /*String Obat*/
    private String r1O1, r1O2, r1O3, r1O4, r1O5,
            r2O1, r2O2, r2O3, r2O4, r2O5,
            r3O1, r3O2, r3O3, r3O4, r3O5,
            r4O1, r4O2, r4O3, r4O4, r4O5,
            r5O1, r5O2, r5O3, r5O4, r5O5;

    private String r1s1, r1s2, r1s3, r1s4, r1s5,
            r2s1, r2s2, r2s3, r2s4, r2s5,
            r3s1, r3s2, r3s3, r3s4, r3s5,
            r4s1, r4s2, r4s3, r4s4, r4s5,
            r5s1, r5s2, r5s3, r5s4, r5s5;

    private String r1j1, r1j2, r1j3, r1j4, r1j5,
            r2j1, r2j2, r2j3, r2j4, r2j5,
            r3j1, r3j2, r3j3, r3j4, r3j5,
            r4j1, r4j2, r4j3, r4j4, r4j5,
            r5j1, r5j2, r5j3, r5j4, r5j5;

    private String perintah1, perintah2, perintah3, perintah4, perintah5, petunjuk1, petunjuk2, petunjuk3, petunjuk4, petunjuk5;

    /*String Lab*/
    private String lab1, lab2, lab3, lab4, lab5, ketLab1, ketLab2, ketLab3, ketLab4, ketLab5;

    private String tindakan1, tindakan2, tindakan3, tindakan4, tindakan5,
            jmlTindakan1, jmlTindakan2, jmlTindakan3, jmlTindakan4,
            jmlTindakan5, ketTIndakan1, ketTIndakan2, ketTIndakan3, ketTIndakan4, ketTIndakan5;

    /*String DIaprog*/
    private String diagnosis1, diagnosis2, diagnosis3, diagnosis4, diagnosis5, prognosis1, prognosis2, prognosis3, prognosis4, prognosis5;

    /*EditText  Obat*/
    private EditText editR1O1, editR1O2, editR1O3, editR1O4, editR1O5,
            editR2O1, editR2O2, editR2O3, editR2O4, editR2O5,
            editR3O1, editR3O2, editR3O3, editR3O4, editR3O5,
            editR4O1, editR4O2, editR4O3, editR4O4, editR4O5,
            editR5O1, editR5O2, editR5O3, editR5O4, editR5O5;

    private EditText editPP1, editPP2, editPP3, editPP4, editPP5,
            editPetunjuk1, editPetunjuk2, editPetunjuk3, editPetunjuk4, editPetunjuk5;

    /*Edit Text Lab*/
    private EditText editKetLab1, editKetLab2, editKetLab3, editKetLab4, editKetLab5;

    /*EditText Diaprog*/
    private EditText editDiagnosis1, editDiagnosis2, editDiagnosis3, editDiagnosis4, editDiagnosis5,
            editPrognosis1, editPrognosis2, editPrognosis3, editPrognosis4, editPrognosis5;

    private EditText editJmlTindakan1, editJmlTindakan2, editJmlTindakan3, editJmlTindakan4, editJmlTindakan5,
            editKetTindakan1, editKetTindakan2, editKetTindakan3, editKetTindakan4, editKetTindakan5;

    private Spinner spinnerR1O1, spinnerR1O2, spinnerR1O3, spinnerR1O4, spinnerR1O5,
            spinnerR2O1, spinnerR2O2, spinnerR2O3, spinnerR2O4, spinnerR2O5,
            spinnerR3O1, spinnerR3O2, spinnerR3O3, spinnerR3O4, spinnerR3O5,
            spinnerR4O1, spinnerR4O2, spinnerR4O3, spinnerR4O4, spinnerR4O5,
            spinnerR5O1, spinnerR5O2, spinnerR5O3, spinnerR5O4, spinnerR5O5,
            spinnerR1j1, spinnerR1j2, spinnerR1j3, spinnerR1j4, spinnerR1j5,
            spinnerR2j1, spinnerR2j2, spinnerR2j3, spinnerR2j4, spinnerR2j5,
            spinnerR3j1, spinnerR3j2, spinnerR3j3, spinnerR3j4, spinnerR3j5,
            spinnerR4j1, spinnerR4j2, spinnerR4j3, spinnerR4j4, spinnerR4j5,
            spinnerR5j1, spinnerR5j2, spinnerR5j3, spinnerR5j4, spinnerR5j5;

    private Spinner spinnerTindakan1, spinnerTindakan2, spinnerTindakan3, spinnerTindakan4, spinnerTindakan5;

    private Spinner spinnerLab1, spinnerLab2, spinnerLab3, spinnerLab4, spinnerLab5;

    private List<Obat> obatGlobal;
    private List<Tindakan> tindakanList;
    private List<Lab> labList;

    private LinearLayout llResep1, llResep2, llResep3, llResep4, llResep5,
            llR1O2, llR1O3, llR1O4, llR1O5,
            llR2O2, llR2O3, llR2O4, llR2O5,
            llR3O2, llR3O3, llR3O4, llR3O5,
            llR4O2, llR4O3, llR4O4, llR4O5,
            llR5O2, llR5O3, llR5O4, llR5O5;

    private LinearLayout llTindakan1, llTindakan2, llTindakan3, llTindakan4, llTindakan5;

    private LinearLayout llLab1, llLab2, llLab3, llLab4, llLab5;

    private LinearLayout llDiagnosis1, llDiagnosis2, llDiagnosis3, llDiagnosis4, llDiagnosis5;

    private TextView tvPasienIdRegistrasi, tvPasienNamaPemilik, tvPasienNamaHewan;


    private ArrayList<String> imageList = new ArrayList<>();

    private ArrayList<String> fileName = new ArrayList<>();

    private CameraPhoto cameraPhotoRm;


    /*Volley  END*/

    private final int CAMERA_REQUEST_1 = 1001, CAMERA_REQUEST_2 = 1002, CAMERA_REQUEST_3 = 1003, CAMERA_REQUEST_4 = 1004;

    private ImageView imageViewRm1, imageViewRm2, imageViewRm3, imageViewRm4;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_tambah_rekam_medis);

        Verify.verifyCameraPermissions(PasienTambahRekamMedis.this);
        Verify.verifyStoragePermissions(PasienTambahRekamMedis.this);

        /*Inisialisasi*/
        editTenagaMedis = (EditText) findViewById(R.id.edit_pasien_rm_tenaga_medis);
        editNamaMahasiswa = (EditText) findViewById(R.id.edit_pasien_rm_mahasiswa);
        editAnamnesis = (EditText) findViewById(R.id.edit_pasien_rm_anamnesis);
        editKeadaanUmum = (EditText) findViewById(R.id.edit_pasien_rm_keadaan_umum);
        editFrekuensiNafas = (EditText) findViewById(R.id.edit_pasien_rm_frek_nafas);
        editFrekuensiPulsus = (EditText) findViewById(R.id.edit_pasien_rm_frek_pulsus);
        editTemperaturTubuh = (EditText) findViewById(R.id.edit_pasien_rm_temperatur_tubuh);
        editKulitRambut = (EditText) findViewById(R.id.edit_pasien_rm_kulit_rambut);
        editSelaputLendir = (EditText) findViewById(R.id.edit_pasien_rm_selaput_lendir);
        editKelenjarLimfa = (EditText) findViewById(R.id.edit_pasien_rm_kelenjar_limfe);
        editPernafasan = (EditText) findViewById(R.id.edit_pasien_rm_pernafasan);
        editPeredaranDarah = (EditText) findViewById(R.id.edit_pasien_rm_peredaran_darah);
        editPencernaan = (EditText) findViewById(R.id.edit_pasien_rm_pencernaan);
        editKelaminPerkencingan = (EditText) findViewById(R.id.edit_pasien_rm_kelamin_perkencingan);
        editSyaraf = (EditText) findViewById(R.id.edit_pasien_rm_syaraf);
        editAnggotaGerak = (EditText) findViewById(R.id.edit_pasien_rm_anggota_gerak);
        editBeratBadan = (EditText) findViewById(R.id.edit_pasien_rm_berat_badan);
        editLainAnamnesis = (EditText) findViewById(R.id.edit_pasien_rm_lain_anamnesis);
        /*editNamaPemeriksaan = (EditText) findViewById(R.id.edit_pasien_rm_nama_pemeriksaan);
        editKetLab = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab);
        editDiagnosis = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis);
        editPrognosis = (EditText) findViewById(R.id.edit_pasien_rm_prognosis);
        editNamaObat = (EditText) findViewById(R.id.edit_pasien_rm_nama_obat);
        editJumlahResep = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_resep);
        editSatuanResep = (EditText) findViewById(R.id.edit_pasien_rm_satuan_resep);
        editPerintahPembuatan = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan);
        editPetunjukPenggunaan = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan);
        editNamaTindakan = (EditText) findViewById(R.id.edit_pasien_rm_nama_tindakan);
        editJumlahTindakan = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan);
        editKetTindakan = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan);*/

        tvPasienIdRegistrasi = (TextView) findViewById(R.id.tv_pasien_rm_id_registrasi);
        tvPasienNamaPemilik = (TextView) findViewById(R.id.tv_pasien_rm_nama_pemilik);
        tvPasienNamaHewan = (TextView) findViewById(R.id.tv_pasien_rm_nama_hewan);

        /*Edit Obat*/
        editR1O1 = (EditText) findViewById(R.id.edit_pasien_rm_r1j1);
        editR1O2 = (EditText) findViewById(R.id.edit_pasien_rm_r1j2);
        editR1O3 = (EditText) findViewById(R.id.edit_pasien_rm_r1j3);
        editR1O4 = (EditText) findViewById(R.id.edit_pasien_rm_r1j4);
        editR1O5 = (EditText) findViewById(R.id.edit_pasien_rm_r1j5);

        editR2O1 = (EditText) findViewById(R.id.edit_pasien_rm_r2j1);
        editR2O2 = (EditText) findViewById(R.id.edit_pasien_rm_r2j2);
        editR2O3 = (EditText) findViewById(R.id.edit_pasien_rm_r2j3);
        editR2O4 = (EditText) findViewById(R.id.edit_pasien_rm_r2j4);
        editR2O5 = (EditText) findViewById(R.id.edit_pasien_rm_r2j5);

        editR3O1 = (EditText) findViewById(R.id.edit_pasien_rm_r3j1);
        editR3O2 = (EditText) findViewById(R.id.edit_pasien_rm_r3j2);
        editR3O3 = (EditText) findViewById(R.id.edit_pasien_rm_r3j3);
        editR3O4 = (EditText) findViewById(R.id.edit_pasien_rm_r3j4);
        editR3O5 = (EditText) findViewById(R.id.edit_pasien_rm_r3j5);

        editR4O1 = (EditText) findViewById(R.id.edit_pasien_rm_r4j1);
        editR4O2 = (EditText) findViewById(R.id.edit_pasien_rm_r4j2);
        editR4O3 = (EditText) findViewById(R.id.edit_pasien_rm_r4j3);
        editR4O4 = (EditText) findViewById(R.id.edit_pasien_rm_r4j4);
        editR4O5 = (EditText) findViewById(R.id.edit_pasien_rm_r4j5);

        editR5O1 = (EditText) findViewById(R.id.edit_pasien_rm_r5j1);
        editR5O2 = (EditText) findViewById(R.id.edit_pasien_rm_r5j2);
        editR5O3 = (EditText) findViewById(R.id.edit_pasien_rm_r5j3);
        editR5O4 = (EditText) findViewById(R.id.edit_pasien_rm_r5j4);
        editR5O5 = (EditText) findViewById(R.id.edit_pasien_rm_r5j5);

        editPP1 = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan_resep_1);
        editPP2 = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan_resep_2);
        editPP3 = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan_resep_3);
        editPP4 = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan_resep_4);
        editPP5 = (EditText) findViewById(R.id.edit_pasien_rm_perintah_pembuatan_resep_5);

        editPetunjuk1 = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan_resep_1);
        editPetunjuk2 = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan_resep_2);
        editPetunjuk3 = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan_resep_3);
        editPetunjuk4 = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan_resep_4);
        editPetunjuk5 = (EditText) findViewById(R.id.edit_pasien_rm_petunjuk_penggunaan_resep_5);

        /*EditText LAB*/
        editKetLab1 = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab_1);
        editKetLab2 = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab_2);
        editKetLab3 = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab_3);
        editKetLab4 = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab_4);
        editKetLab5 = (EditText) findViewById(R.id.edit_pasien_rm_ket_lab_5);

        /*EditDiagnosis*/
        editDiagnosis1 = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis_1);
        editDiagnosis2 = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis_2);
        editDiagnosis3 = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis_3);
        editDiagnosis4 = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis_4);
        editDiagnosis5 = (EditText) findViewById(R.id.edit_pasien_rm_diagnosis_5);

        editPrognosis1 = (EditText) findViewById(R.id.edit_pasien_rm_prognosis_1);
        editPrognosis2 = (EditText) findViewById(R.id.edit_pasien_rm_prognosis_2);
        editPrognosis3 = (EditText) findViewById(R.id.edit_pasien_rm_prognosis_3);
        editPrognosis4 = (EditText) findViewById(R.id.edit_pasien_rm_prognosis_4);
        editPrognosis5 = (EditText) findViewById(R.id.edit_pasien_rm_prognosis_5);

        /*EditTIndakan*/
        editJmlTindakan1 = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan_1);
        editJmlTindakan2 = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan_2);
        editJmlTindakan3 = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan_3);
        editJmlTindakan4 = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan_4);
        editJmlTindakan5 = (EditText) findViewById(R.id.edit_pasien_rm_jumlah_tindakan_5);

        editKetTindakan1 = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan_1);
        editKetTindakan2 = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan_2);
        editKetTindakan3 = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan_3);
        editKetTindakan4 = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan_4);
        editKetTindakan5 = (EditText) findViewById(R.id.edit_pasien_rm_ket_tindakan_5);

        /*Spinner  Tindakan*/
        spinnerTindakan1 = (Spinner) findViewById(R.id.spinner_rm_tindakan1);
        spinnerTindakan2 = (Spinner) findViewById(R.id.spinner_rm_tindakan2);
        spinnerTindakan3 = (Spinner) findViewById(R.id.spinner_rm_tindakan3);
        spinnerTindakan4 = (Spinner) findViewById(R.id.spinner_rm_tindakan4);
        spinnerTindakan5 = (Spinner) findViewById(R.id.spinner_rm_tindakan5);

        /*Spinner Lab*/
        spinnerLab1 = (Spinner) findViewById(R.id.spinner_rm_lab_1);
        spinnerLab2 = (Spinner) findViewById(R.id.spinner_rm_lab_2);
        spinnerLab3 = (Spinner) findViewById(R.id.spinner_rm_lab_3);
        spinnerLab4 = (Spinner) findViewById(R.id.spinner_rm_lab_4);
        spinnerLab5 = (Spinner) findViewById(R.id.spinner_rm_lab_5);

        /*spinner OBAT*/
        spinnerR1O1 = (Spinner) findViewById(R.id.spinner_rm_r1o1);
        spinnerR1O2 = (Spinner) findViewById(R.id.spinner_rm_r1o2);
        spinnerR1O3 = (Spinner) findViewById(R.id.spinner_rm_r1o3);
        spinnerR1O4 = (Spinner) findViewById(R.id.spinner_rm_r1o4);
        spinnerR1O5 = (Spinner) findViewById(R.id.spinner_rm_r1o5);

        spinnerR2O1 = (Spinner) findViewById(R.id.spinner_rm_r2o1);
        spinnerR2O2 = (Spinner) findViewById(R.id.spinner_rm_r2o2);
        spinnerR2O3 = (Spinner) findViewById(R.id.spinner_rm_r2o3);
        spinnerR2O4 = (Spinner) findViewById(R.id.spinner_rm_r2o4);
        spinnerR2O5 = (Spinner) findViewById(R.id.spinner_rm_r2o5);

        spinnerR3O1 = (Spinner) findViewById(R.id.spinner_rm_r3o1);
        spinnerR3O2 = (Spinner) findViewById(R.id.spinner_rm_r3o2);
        spinnerR3O3 = (Spinner) findViewById(R.id.spinner_rm_r3o3);
        spinnerR3O4 = (Spinner) findViewById(R.id.spinner_rm_r3o4);
        spinnerR3O5 = (Spinner) findViewById(R.id.spinner_rm_r3o5);

        spinnerR4O1 = (Spinner) findViewById(R.id.spinner_rm_r4o1);
        spinnerR4O2 = (Spinner) findViewById(R.id.spinner_rm_r4o2);
        spinnerR4O3 = (Spinner) findViewById(R.id.spinner_rm_r4o3);
        spinnerR4O4 = (Spinner) findViewById(R.id.spinner_rm_r4o4);
        spinnerR4O5 = (Spinner) findViewById(R.id.spinner_rm_r4o5);

        spinnerR5O1 = (Spinner) findViewById(R.id.spinner_rm_r5o1);
        spinnerR5O2 = (Spinner) findViewById(R.id.spinner_rm_r5o2);
        spinnerR5O3 = (Spinner) findViewById(R.id.spinner_rm_r5o3);
        spinnerR5O4 = (Spinner) findViewById(R.id.spinner_rm_r5o4);
        spinnerR5O5 = (Spinner) findViewById(R.id.spinner_rm_r5o5);

        spinnerR1j1 = (Spinner) findViewById(R.id.spinner_rm_r1s1);
        spinnerR1j2 = (Spinner) findViewById(R.id.spinner_rm_r1s2);
        spinnerR1j3 = (Spinner) findViewById(R.id.spinner_rm_r1s3);
        spinnerR1j4 = (Spinner) findViewById(R.id.spinner_rm_r1s4);
        spinnerR1j5 = (Spinner) findViewById(R.id.spinner_rm_r1s5);

        spinnerR2j1 = (Spinner) findViewById(R.id.spinner_rm_r2s1);
        spinnerR2j2 = (Spinner) findViewById(R.id.spinner_rm_r2s2);
        spinnerR2j3 = (Spinner) findViewById(R.id.spinner_rm_r2s3);
        spinnerR2j4 = (Spinner) findViewById(R.id.spinner_rm_r2s4);
        spinnerR2j5 = (Spinner) findViewById(R.id.spinner_rm_r2s5);

        spinnerR3j1 = (Spinner) findViewById(R.id.spinner_rm_r3s1);
        spinnerR3j2 = (Spinner) findViewById(R.id.spinner_rm_r3s2);
        spinnerR3j3 = (Spinner) findViewById(R.id.spinner_rm_r3s3);
        spinnerR3j4 = (Spinner) findViewById(R.id.spinner_rm_r3s4);
        spinnerR3j5 = (Spinner) findViewById(R.id.spinner_rm_r3s5);

        spinnerR4j1 = (Spinner) findViewById(R.id.spinner_rm_r4s1);
        spinnerR4j2 = (Spinner) findViewById(R.id.spinner_rm_r4s2);
        spinnerR4j3 = (Spinner) findViewById(R.id.spinner_rm_r4s3);
        spinnerR4j4 = (Spinner) findViewById(R.id.spinner_rm_r4s4);
        spinnerR4j5 = (Spinner) findViewById(R.id.spinner_rm_r4s5);

        spinnerR5j1 = (Spinner) findViewById(R.id.spinner_rm_r5s1);
        spinnerR5j2 = (Spinner) findViewById(R.id.spinner_rm_r5s2);
        spinnerR5j3 = (Spinner) findViewById(R.id.spinner_rm_r5s3);
        spinnerR5j4 = (Spinner) findViewById(R.id.spinner_rm_r5s4);
        spinnerR5j5 = (Spinner) findViewById(R.id.spinner_rm_r5s5);

        loadJsonSpinner();

        /*spinner satuan obat*/

        setupSpinnerSatuanObat(spinnerR1j1);
        setupSpinnerSatuanObat(spinnerR1j2);
        setupSpinnerSatuanObat(spinnerR1j3);
        setupSpinnerSatuanObat(spinnerR1j4);
        setupSpinnerSatuanObat(spinnerR1j5);
        spinnerR1j1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r1s1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r1s1 = "";
            }
        });

        spinnerR1j2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r1s2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r1s2 = "";
            }
        });

        spinnerR1j3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r1s3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r1s3 = "";
            }
        });

        spinnerR1j4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r1s4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r1s4 = "";
            }
        });

        spinnerR1j5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r1s5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r1s5 = "";
            }
        });

        setupSpinnerSatuanObat(spinnerR2j1);
        setupSpinnerSatuanObat(spinnerR2j2);
        setupSpinnerSatuanObat(spinnerR2j3);
        setupSpinnerSatuanObat(spinnerR2j4);
        setupSpinnerSatuanObat(spinnerR2j5);

        spinnerR2j1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r2s1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r2s1 = "";
            }
        });

        spinnerR2j2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r2s2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r2s2 = "";
            }
        });

        spinnerR2j3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r2s3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r2s3 = "";
            }
        });

        spinnerR2j4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r2s4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r2s4 = "";
            }
        });

        spinnerR2j5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r2s5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r2s5 = "";
            }
        });

        setupSpinnerSatuanObat(spinnerR3j1);
        setupSpinnerSatuanObat(spinnerR3j2);
        setupSpinnerSatuanObat(spinnerR3j3);
        setupSpinnerSatuanObat(spinnerR3j4);
        setupSpinnerSatuanObat(spinnerR3j5);

        spinnerR3j1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r3s1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r3s1 = "";
            }
        });

        spinnerR3j2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r3s2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r3s2 = "";
            }
        });

        spinnerR3j3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r3s3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r3s3 = "";
            }
        });

        spinnerR3j4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r3s4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r3s4 = "";
            }
        });

        spinnerR3j5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r3s5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r3s5 = "";
            }
        });

        setupSpinnerSatuanObat(spinnerR4j1);
        setupSpinnerSatuanObat(spinnerR4j2);
        setupSpinnerSatuanObat(spinnerR4j3);
        setupSpinnerSatuanObat(spinnerR4j4);
        setupSpinnerSatuanObat(spinnerR4j5);

        spinnerR4j1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r4s1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r4s1 = "";
            }
        });

        spinnerR4j2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r4s2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r4s2 = "";
            }
        });

        spinnerR4j3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r4s3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r4s3 = "";
            }
        });

        spinnerR4j4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r4s4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r4s4 = "";
            }
        });

        spinnerR4j5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r4s5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r4s5 = "";
            }
        });

        setupSpinnerSatuanObat(spinnerR5j1);
        setupSpinnerSatuanObat(spinnerR5j2);
        setupSpinnerSatuanObat(spinnerR5j3);
        setupSpinnerSatuanObat(spinnerR5j4);
        setupSpinnerSatuanObat(spinnerR5j5);

        spinnerR5j1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r5s1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r5s1 = "";
            }
        });

        spinnerR5j2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r5s2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r5s2 = "";
            }
        });

        spinnerR5j3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r5s3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r5s3 = "";
            }
        });

        spinnerR5j4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r5s4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r5s4 = "";
            }
        });

        spinnerR5j5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r5s5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                r5s5 = "";
            }
        });

        /*end spinner jumlah obat*/

        /*Tambah RESEP*/
        btnTambahResep1 = (Button) findViewById(R.id.btn_tambah_resep_1);
        btnTambahResep2 = (Button) findViewById(R.id.btn_tambah_resep_2);
        btnTambahResep3 = (Button) findViewById(R.id.btn_tambah_resep_3);
        btnTambahResep4 = (Button) findViewById(R.id.btn_tambah_resep_4);

        btnTambahResep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahResep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahResep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahResep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep5.setVisibility(View.VISIBLE);
            }
        });

        btnHapusResep2 = (Button) findViewById(R.id.btn_hapus_resep_2);
        btnHapusResep3 = (Button) findViewById(R.id.btn_hapus_resep_3);
        btnHapusResep4 = (Button) findViewById(R.id.btn_hapus_resep_4);
        btnHapusResep5 = (Button) findViewById(R.id.btn_hapus_resep_5);

        btnHapusResep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep2.setVisibility(View.GONE);
                llR2O2.setVisibility(View.GONE);
                llR2O3.setVisibility(View.GONE);
                llR2O4.setVisibility(View.GONE);
                llR2O5.setVisibility(View.GONE);

                r2O1 = "";
                r2O2 = "";
                r2O3 = "";
                r2O4 = "";
                r2O5 = "";
            }
        });

        btnHapusResep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep3.setVisibility(View.GONE);
                llR3O2.setVisibility(View.GONE);
                llR3O3.setVisibility(View.GONE);
                llR3O4.setVisibility(View.GONE);
                llR3O5.setVisibility(View.GONE);

                r3O1 = "";
                r3O2 = "";
                r3O3 = "";
                r3O4 = "";
                r3O5 = "";
            }
        });

        btnHapusResep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep4.setVisibility(View.GONE);
                llR4O2.setVisibility(View.GONE);
                llR4O3.setVisibility(View.GONE);
                llR4O4.setVisibility(View.GONE);
                llR4O5.setVisibility(View.GONE);

                r4O1 = "";
                r4O2 = "";
                r4O3 = "";
                r4O4 = "";
                r4O5 = "";
            }
        });

        btnHapusResep5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llResep5.setVisibility(View.GONE);
                llR5O2.setVisibility(View.GONE);
                llR5O3.setVisibility(View.GONE);
                llR5O4.setVisibility(View.GONE);
                llR5O5.setVisibility(View.GONE);

                r5O1 = "";
                r5O2 = "";
                r5O3 = "";
                r5O4 = "";
                r5O5 = "";
            }
        });

        btnTambahObatR1O1 = (Button) findViewById(R.id.btn_tambah_r1o1);
        btnTambahObatR1O2 = (Button) findViewById(R.id.btn_tambah_r1o2);
        btnTambahObatR1O3 = (Button) findViewById(R.id.btn_tambah_r1o3);
        btnTambahObatR1O4 = (Button) findViewById(R.id.btn_tambah_r1o4);

        btnTambahObatR1O1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR1O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR1O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR1O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O5.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR2O1 = (Button) findViewById(R.id.btn_tambah_r2o1);
        btnTambahObatR2O2 = (Button) findViewById(R.id.btn_tambah_r2o2);
        btnTambahObatR2O3 = (Button) findViewById(R.id.btn_tambah_r2o3);
        btnTambahObatR2O4 = (Button) findViewById(R.id.btn_tambah_r2o4);

        btnTambahObatR2O1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR2O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR2O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR2O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O5.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR3O1 = (Button) findViewById(R.id.btn_tambah_r3o1);
        btnTambahObatR3O2 = (Button) findViewById(R.id.btn_tambah_r3o2);
        btnTambahObatR3O3 = (Button) findViewById(R.id.btn_tambah_r3o3);
        btnTambahObatR3O4 = (Button) findViewById(R.id.btn_tambah_r3o4);

        btnTambahObatR3O1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR3O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR3O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR3O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O5.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR4O1 = (Button) findViewById(R.id.btn_tambah_r4o1);
        btnTambahObatR4O2 = (Button) findViewById(R.id.btn_tambah_r4o2);
        btnTambahObatR4O3 = (Button) findViewById(R.id.btn_tambah_r4o3);
        btnTambahObatR4O4 = (Button) findViewById(R.id.btn_tambah_r4o4);

        btnTambahObatR4O1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR4O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR4O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR4O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O5.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR5O1 = (Button) findViewById(R.id.btn_tambah_r5o1);
        btnTambahObatR5O2 = (Button) findViewById(R.id.btn_tambah_r5o2);
        btnTambahObatR5O3 = (Button) findViewById(R.id.btn_tambah_r5o3);
        btnTambahObatR5O4 = (Button) findViewById(R.id.btn_tambah_r5o4);

        btnTambahObatR5O1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR5O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR5O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahObatR5O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O5.setVisibility(View.VISIBLE);
            }
        });

        btnMinusObatR1O2 = (Button) findViewById(R.id.btn_minus_r1o2);
        btnMinusObatR1O3 = (Button) findViewById(R.id.btn_minus_r1o3);
        btnMinusObatR1O4 = (Button) findViewById(R.id.btn_minus_r1o4);
        btnMinusObatR1O5 = (Button) findViewById(R.id.btn_minus_r1o5);

        btnMinusObatR1O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O2.setVisibility(View.GONE);
                r1O2 = "";
                r1j2 = "";
                r1s2 = "";
            }
        });

        btnMinusObatR1O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O3.setVisibility(View.GONE);
                r1O3 = "";
                r1j3 = "";
                r1s3 = "";
            }
        });

        btnMinusObatR1O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O4.setVisibility(View.GONE);
                r1O4 = "";
                r1j4 = "";
                r1s4 = "";
            }
        });

        btnMinusObatR1O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O5.setVisibility(View.GONE);
                r1O4 = "";
                r1j4 = "";
                r1s4 = "";
            }
        });

        btnMinusObatR2O2 = (Button) findViewById(R.id.btn_minus_r2o2);
        btnMinusObatR2O3 = (Button) findViewById(R.id.btn_minus_r2o3);
        btnMinusObatR2O4 = (Button) findViewById(R.id.btn_minus_r2o4);
        btnMinusObatR2O5 = (Button) findViewById(R.id.btn_minus_r2o5);

        btnMinusObatR2O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O2.setVisibility(View.GONE);
                r2O2 = "";
                r2j2 = "";
                r2s2 = "";
            }
        });

        btnMinusObatR2O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O3.setVisibility(View.GONE);
                r2O3 = "";
                r2j3 = "";
                r2s3 = "";
            }
        });

        btnMinusObatR2O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O4.setVisibility(View.GONE);
                r2O4 = "";
                r2j4 = "";
                r2s4 = "";
            }
        });

        btnMinusObatR2O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O5.setVisibility(View.GONE);
                r2O5 = "";
                r2j5 = "";
                r2s5 = "";
            }
        });

        btnMinusObatR3O2 = (Button) findViewById(R.id.btn_minus_r3o2);
        btnMinusObatR3O3 = (Button) findViewById(R.id.btn_minus_r3o3);
        btnMinusObatR3O4 = (Button) findViewById(R.id.btn_minus_r3o4);
        btnMinusObatR3O5 = (Button) findViewById(R.id.btn_minus_r3o5);

        btnMinusObatR3O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O2.setVisibility(View.GONE);
                r3O2 = "";
                r3j2 = "";
                r3s2 = "";
            }
        });

        btnMinusObatR3O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O3.setVisibility(View.GONE);
                r3O3 = "";
                r3j3 = "";
                r3s3 = "";
            }
        });

        btnMinusObatR3O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O4.setVisibility(View.GONE);
                r3O4 = "";
                r3j4 = "";
                r3s4 = "";
            }
        });

        btnMinusObatR3O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O5.setVisibility(View.GONE);
                r3O5 = "";
                r3j5 = "";
                r3s5 = "";
            }
        });

        btnMinusObatR4O2 = (Button) findViewById(R.id.btn_minus_r4o2);
        btnMinusObatR4O3 = (Button) findViewById(R.id.btn_minus_r4o3);
        btnMinusObatR4O4 = (Button) findViewById(R.id.btn_minus_r4o4);
        btnMinusObatR4O5 = (Button) findViewById(R.id.btn_minus_r4o5);

        btnMinusObatR4O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O2.setVisibility(View.GONE);
                r4O2 = "";
                r4j2 = "";
                r4s2 = "";
            }
        });

        btnMinusObatR4O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O3.setVisibility(View.GONE);
                r4O3 = "";
                r4j3 = "";
                r4s3 = "";
            }
        });

        btnMinusObatR4O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O4.setVisibility(View.GONE);
                r4O4 = "";
                r4j4 = "";
                r4s4 = "";
            }
        });

        btnMinusObatR4O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O5.setVisibility(View.GONE);
                r4O5 = "";
                r4j5 = "";
                r4s5 = "";
            }
        });

        btnMinusObatR5O2 = (Button) findViewById(R.id.btn_minus_r5o2);
        btnMinusObatR5O3 = (Button) findViewById(R.id.btn_minus_r5o3);
        btnMinusObatR5O4 = (Button) findViewById(R.id.btn_minus_r5o4);
        btnMinusObatR5O5 = (Button) findViewById(R.id.btn_minus_r5o5);

        btnMinusObatR5O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O2.setVisibility(View.GONE);
                r5O2 = "";
                r5j2 = "";
                r5s2 = "";
            }
        });

        btnMinusObatR5O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O3.setVisibility(View.GONE);
                r5O3 = "";
                r5j3 = "";
                r5s3 = "";
            }
        });

        btnMinusObatR5O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O4.setVisibility(View.GONE);
                r5O4 = "";
                r5j4 = "";
                r5s4 = "";
            }
        });

        btnMinusObatR5O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O5.setVisibility(View.GONE);
                r5O5 = "";
                r5j5 = "";
                r5s5 = "";
            }
        });

        /*btnTambahTindakan*/
        btnTambahTindakan1 = (Button) findViewById(R.id.btn_tambah_tindakan_1);
        btnTambahTindakan2 = (Button) findViewById(R.id.btn_tambah_tindakan_2);
        btnTambahTindakan3 = (Button) findViewById(R.id.btn_tambah_tindakan_3);
        btnTambahTindakan4 = (Button) findViewById(R.id.btn_tambah_tindakan_4);
        btnTambahTindakan5 = (Button) findViewById(R.id.btn_tambah_tindakan_5);

        btnTambahTindakan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahTindakan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahTindakan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahTindakan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan5.setVisibility(View.VISIBLE);
            }
        });

        /*btmHapusTindakan*/
        btnHapusTindakan2 = (Button) findViewById(R.id.btn_hapus_tindakan_2);
        btnHapusTindakan3 = (Button) findViewById(R.id.btn_hapus_tindakan_3);
        btnHapusTindakan4 = (Button) findViewById(R.id.btn_hapus_tindakan_4);
        btnHapusTindakan5 = (Button) findViewById(R.id.btn_hapus_tindakan_5);

        btnHapusTindakan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan2.setVisibility(View.GONE);
                tindakan2 = "";
                jmlTindakan2 = "";
                ketTIndakan2 = "";
            }
        });

        btnHapusTindakan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan3.setVisibility(View.GONE);
                tindakan3 = "";
                jmlTindakan3 = "";
                ketTIndakan3 = "";
            }
        });

        btnHapusTindakan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan4.setVisibility(View.GONE);
                tindakan4 = "";
                jmlTindakan4 = "";
                ketTIndakan4 = "";
            }
        });

        btnHapusTindakan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTindakan5.setVisibility(View.GONE);
                tindakan5 = "";
                jmlTindakan5 = "";
                ketTIndakan5 = "";
            }
        });
        /*btn Tindakan END*/
        /*btn LAB START*/
        btnTambahLab1 = (Button) findViewById(R.id.btn_tambah_lab_1);
        btnTambahLab2 = (Button) findViewById(R.id.btn_tambah_lab_2);
        btnTambahLab3 = (Button) findViewById(R.id.btn_tambah_lab_3);
        btnTambahLab4 = (Button) findViewById(R.id.btn_tambah_lab_4);
        btnTambahLab5 = (Button) findViewById(R.id.btn_tambah_lab_5);

        btnTambahLab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahLab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahLab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahLab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab5.setVisibility(View.VISIBLE);
            }
        });

        btnHapusLab2 = (Button) findViewById(R.id.btn_hapus_lab_2);
        btnHapusLab3 = (Button) findViewById(R.id.btn_hapus_lab_3);
        btnHapusLab4 = (Button) findViewById(R.id.btn_hapus_lab_4);
        btnHapusLab5 = (Button) findViewById(R.id.btn_hapus_lab_5);

        btnHapusLab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab2.setVisibility(View.GONE);
                lab2 = "";
                ketLab2 = "";
            }
        });

        btnHapusLab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab3.setVisibility(View.GONE);
                lab3 = "";
                ketLab3 = "";
            }
        });

        btnHapusLab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab4.setVisibility(View.GONE);
                lab4 = "";
                ketLab4 = "";
            }
        });

        btnHapusLab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLab5.setVisibility(View.GONE);
                lab5 = "";
                ketLab5 = "";
            }
        });

        btnTambahDiagnosis1 = (Button) findViewById(R.id.btn_tambah_diagnosis_1);
        btnTambahDiagnosis2 = (Button) findViewById(R.id.btn_tambah_diagnosis_2);
        btnTambahDiagnosis3 = (Button) findViewById(R.id.btn_tambah_diagnosis_3);
        btnTambahDiagnosis4 = (Button) findViewById(R.id.btn_tambah_diagnosis_4);
        btnTambahDiagnosis5 = (Button) findViewById(R.id.btn_tambah_diagnosis_5);

        btnTambahDiagnosis1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahDiagnosis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahDiagnosis3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis4.setVisibility(View.VISIBLE);
            }
        });

        btnTambahDiagnosis4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis5.setVisibility(View.VISIBLE);
            }
        });

        btnHapusDiagnosis2 = (Button) findViewById(R.id.btn_hapus_diagnosis_2);
        btnHapusDiagnosis3 = (Button) findViewById(R.id.btn_hapus_diagnosis_3);
        btnHapusDiagnosis4 = (Button) findViewById(R.id.btn_hapus_diagnosis_4);
        btnHapusDiagnosis5 = (Button) findViewById(R.id.btn_hapus_diagnosis_5);

        btnHapusDiagnosis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis2.setVisibility(View.GONE);
                diagnosis2 = "";
                prognosis2 = "";
            }
        });

        btnHapusDiagnosis3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis3.setVisibility(View.GONE);
                diagnosis3 = "";
                prognosis3 = "";
            }
        });

        btnHapusDiagnosis4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis4.setVisibility(View.GONE);
                diagnosis4 = "";
                prognosis4 = "";
            }
        });

        btnHapusDiagnosis5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDiagnosis5.setVisibility(View.GONE);
                diagnosis5 = "";
                prognosis5 = "";
            }
        });

        imageViewRm1 = (ImageView) findViewById(R.id.image_pasien_rm_foto1);
        imageViewRm2 = (ImageView) findViewById(R.id.image_pasien_rm_foto2);
        imageViewRm3 = (ImageView) findViewById(R.id.image_pasien_rm_foto3);
        imageViewRm4 = (ImageView) findViewById(R.id.image_pasien_rm_foto4);

        llResep1 = (LinearLayout) findViewById(R.id.ll_rm_resep_1);
        llResep2 = (LinearLayout) findViewById(R.id.ll_rm_resep_2);
        llResep3 = (LinearLayout) findViewById(R.id.ll_rm_resep_3);
        llResep4 = (LinearLayout) findViewById(R.id.ll_rm_resep_4);
        llResep5 = (LinearLayout) findViewById(R.id.ll_rm_resep_5);

        llR1O2 = (LinearLayout) findViewById(R.id.ll_r1o2);
        llR1O3 = (LinearLayout) findViewById(R.id.ll_r1o3);
        llR1O4 = (LinearLayout) findViewById(R.id.ll_r1o4);
        llR1O5 = (LinearLayout) findViewById(R.id.ll_r1o5);

        llR2O2 = (LinearLayout) findViewById(R.id.ll_r2o2);
        llR2O3 = (LinearLayout) findViewById(R.id.ll_r2o3);
        llR2O4 = (LinearLayout) findViewById(R.id.ll_r2o4);
        llR2O5 = (LinearLayout) findViewById(R.id.ll_r2o5);

        llR3O2 = (LinearLayout) findViewById(R.id.ll_r3o2);
        llR3O3 = (LinearLayout) findViewById(R.id.ll_r3o3);
        llR3O4 = (LinearLayout) findViewById(R.id.ll_r3o4);
        llR3O5 = (LinearLayout) findViewById(R.id.ll_r3o5);

        llR4O2 = (LinearLayout) findViewById(R.id.ll_r4o2);
        llR4O3 = (LinearLayout) findViewById(R.id.ll_r4o3);
        llR4O4 = (LinearLayout) findViewById(R.id.ll_r4o4);
        llR4O5 = (LinearLayout) findViewById(R.id.ll_r4o5);

        llR5O2 = (LinearLayout) findViewById(R.id.ll_r5o2);
        llR5O3 = (LinearLayout) findViewById(R.id.ll_r5o3);
        llR5O4 = (LinearLayout) findViewById(R.id.ll_r5o4);
        llR5O5 = (LinearLayout) findViewById(R.id.ll_r5o5);

        /*Linearlayout  tindakan*/
        llTindakan2 = (LinearLayout) findViewById(R.id.ll_tindakan_2);
        llTindakan3 = (LinearLayout) findViewById(R.id.ll_tindakan_3);
        llTindakan4 = (LinearLayout) findViewById(R.id.ll_tindakan_4);
        llTindakan5 = (LinearLayout) findViewById(R.id.ll_tindakan_5);

        /*Linear layout lab*/
        llLab2 = (LinearLayout) findViewById(R.id.ll_rm_lab_2);
        llLab3 = (LinearLayout) findViewById(R.id.ll_rm_lab_3);
        llLab4 = (LinearLayout) findViewById(R.id.ll_rm_lab_4);
        llLab5 = (LinearLayout) findViewById(R.id.ll_rm_lab_5);

        /*Liear layout Diagnois*/
        llDiagnosis2 = (LinearLayout) findViewById(R.id.ll_diaprog_rm_2);
        llDiagnosis3 = (LinearLayout) findViewById(R.id.ll_diaprog_rm_3);
        llDiagnosis4 = (LinearLayout) findViewById(R.id.ll_diaprog_rm_4);
        llDiagnosis5 = (LinearLayout) findViewById(R.id.ll_diaprog_rm_5);

        final Intent intent = getIntent();
        String intent1 = intent.getStringExtra(Pasien.ID);
        String intent2 = intent.getStringExtra(PasienRekamMedis.ID_PASIEN_RM);

        if (TextUtils.isEmpty(intent1)) {
            idRegistrasi = intent.getStringExtra(PasienRekamMedis.ID_PASIEN_RM);
            namaPemilik = intent.getStringExtra(PasienRekamMedis.NAMA_PEMILIK_RM);
            namaHewan = intent.getStringExtra(PasienRekamMedis.NAMA_HEWAN_RM);
        } else {
            idRegistrasi = intent.getStringExtra(Pasien.ID);
            namaPemilik = intent.getStringExtra(Pasien.NAMA_PEMILIK);
            namaHewan = intent.getStringExtra(Pasien.NAMA_HEWAN);
        }

        tvPasienIdRegistrasi.setText(idRegistrasi);
        tvPasienNamaHewan.setText(namaHewan);
        tvPasienNamaPemilik.setText(namaPemilik);

        cameraPhotoRm = new CameraPhoto(getApplicationContext());

        /*IMAGE*/
        imageViewRm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhotoRm.takePhotoIntent(), CAMERA_REQUEST_1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageViewRm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhotoRm.takePhotoIntent(), CAMERA_REQUEST_2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageViewRm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhotoRm.takePhotoIntent(), CAMERA_REQUEST_3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageViewRm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhotoRm.takePhotoIntent(), CAMERA_REQUEST_4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendPost(String idRegistrasi,
                         String namaPemilik,
                         String namaPasien,
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
                         String ketTindakan,
                         String foto1,
                         String foto2,
                         String foto3,
                         String foto4) {
        RestApiRekamMedis apiService = ApiClient.getClient().create(RestApiRekamMedis.class);
        Call<InputRm> call = apiService.savePost(idRegistrasi, namaPemilik, namaPasien, tenagaMedis,
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
                ketTindakan,
                foto1,
                foto2,
                foto3,
                foto4);
        call.enqueue(new Callback<InputRm>() {
            @Override
            public void onResponse(Call<InputRm> call, Response<InputRm> response) {
                progressDialog.dismiss();
                Toast.makeText(PasienTambahRekamMedis.this, "Post submitted", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<InputRm> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PasienTambahRekamMedis.this, "Unable to submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_1) {
                String photoPath1 = cameraPhotoRm.getPhotoPath();
                imageList.add(photoPath1);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath1).requestSize(512, 512).getBitmap();
                    imageViewRm1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageViewRm1.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_2) {
                String photoPath2 = cameraPhotoRm.getPhotoPath();
                imageList.add(photoPath2);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath2).requestSize(512, 512).getBitmap();
                    imageViewRm2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageViewRm2.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_3) {
                String photoPath3 = cameraPhotoRm.getPhotoPath();
                imageList.add(photoPath3);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath3).requestSize(512, 512).getBitmap();
                    imageViewRm3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageViewRm3.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_4) {
                String photoPath4 = cameraPhotoRm.getPhotoPath();
                imageList.add(photoPath4);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath4).requestSize(512, 512).getBitmap();
                    imageViewRm4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageViewRm4.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_input.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    private void sendInap(String idRegistrasi,
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
        RestApiRekamMedisInap apiService = ApiClient.getClient().create(RestApiRekamMedisInap.class);
        Call<InputRmInap> call = apiService.savePost(idRegistrasi,
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
        call.enqueue(new Callback<InputRmInap>() {
            @Override
            public void onResponse(Call<InputRmInap> call, Response<InputRmInap> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(PasienTambahRekamMedis.this, "Post submitted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PasienTambahRekamMedis.this, "Post failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InputRmInap> call, Throwable t) {
                Toast.makeText(PasienTambahRekamMedis.this, "Post failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateAntrian(String idPasien, String statusAntrian) {
        RestApi updateService = ApiClient.getClient().create(RestApi.class);
        Call<DataPasien> call = updateService.updatePost(idPasien, statusAntrian);
        call.enqueue(new Callback<DataPasien>() {
            @Override
            public void onResponse(Call<DataPasien> call, Response<DataPasien> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
/*                    Toast.makeText(PasienUpdate.this, "Update submitted", Toast.LENGTH_SHORT).show();
                    finish();*/
                } else {
                    /*Toast.makeText(PasienUpdate.this, "Update failed", Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onFailure(Call<DataPasien> call, Throwable t) {
                progressDialog.dismiss();
                /*Toast.makeText(PasienUpdate.this, "Update failed", Toast.LENGTH_SHORT).show();*/
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_simpan:
                final CharSequence[] dialogitem = {"Rawat Jalan", "Rawat Inap"};
                AlertDialog.Builder builder = new AlertDialog.Builder(PasienTambahRekamMedis.this);
                builder.setTitle("Jenis Perawatan");
                final MyCommand myCommand = new MyCommand(getApplicationContext());

                r1j1 = editR1O1.getText().toString().trim();
                r1j2 = editR1O2.getText().toString().trim();
                r1j3 = editR1O3.getText().toString().trim();
                r1j4 = editR1O4.getText().toString().trim();
                r1j5 = editR1O5.getText().toString().trim();

                r2j1 = editR2O1.getText().toString().trim();
                r2j2 = editR2O2.getText().toString().trim();
                r2j3 = editR2O3.getText().toString().trim();
                r2j4 = editR2O4.getText().toString().trim();
                r2j5 = editR2O5.getText().toString().trim();

                r3j1 = editR3O1.getText().toString().trim();
                r3j2 = editR3O2.getText().toString().trim();
                r3j3 = editR3O3.getText().toString().trim();
                r3j4 = editR3O4.getText().toString().trim();
                r3j5 = editR3O5.getText().toString().trim();

                r4j1 = editR4O1.getText().toString().trim();
                r4j2 = editR4O2.getText().toString().trim();
                r4j3 = editR4O3.getText().toString().trim();
                r4j4 = editR4O4.getText().toString().trim();
                r4j5 = editR4O5.getText().toString().trim();

                r5j1 = editR5O1.getText().toString().trim();
                r5j2 = editR5O2.getText().toString().trim();
                r5j3 = editR5O3.getText().toString().trim();
                r5j4 = editR5O4.getText().toString().trim();
                r5j5 = editR5O5.getText().toString().trim();

                petunjuk1 = editPetunjuk1.getText().toString().trim();
                petunjuk2 = editPetunjuk2.getText().toString().trim();
                petunjuk3 = editPetunjuk3.getText().toString().trim();
                petunjuk4 = editPetunjuk4.getText().toString().trim();
                petunjuk5 = editPetunjuk5.getText().toString().trim();

                perintah1 = editPP1.getText().toString().trim();
                perintah2 = editPP2.getText().toString().trim();
                perintah3 = editPP3.getText().toString().trim();
                perintah4 = editPP4.getText().toString().trim();
                perintah5 = editPP5.getText().toString().trim();

                ketLab1 = editKetLab1.getText().toString().trim();
                ketLab2 = editKetLab2.getText().toString().trim();
                ketLab3 = editKetLab3.getText().toString().trim();
                ketLab4 = editKetLab4.getText().toString().trim();
                ketLab5 = editKetLab5.getText().toString().trim();

                diagnosis1 = editDiagnosis1.getText().toString().trim();
                diagnosis2 = editDiagnosis2.getText().toString().trim();
                diagnosis3 = editDiagnosis3.getText().toString().trim();
                diagnosis4 = editDiagnosis4.getText().toString().trim();
                diagnosis5 = editDiagnosis5.getText().toString().trim();

                prognosis1 = editPrognosis1.getText().toString().trim();
                prognosis2 = editPrognosis2.getText().toString().trim();
                prognosis3 = editPrognosis3.getText().toString().trim();
                prognosis4 = editPrognosis4.getText().toString().trim();
                prognosis5 = editPrognosis5.getText().toString().trim();

                jmlTindakan1 = editJmlTindakan1.getText().toString().trim();
                jmlTindakan2 = editJmlTindakan2.getText().toString().trim();
                jmlTindakan3 = editJmlTindakan3.getText().toString().trim();
                jmlTindakan4 = editJmlTindakan4.getText().toString().trim();
                jmlTindakan5 = editJmlTindakan5.getText().toString().trim();

                ketTIndakan1 = editKetTindakan1.getText().toString().trim();
                ketTIndakan2 = editKetTindakan2.getText().toString().trim();
                ketTIndakan3 = editKetTindakan3.getText().toString().trim();
                ketTIndakan4 = editKetTindakan4.getText().toString().trim();
                ketTIndakan5 = editKetTindakan5.getText().toString().trim();

                tenagaMedis = editTenagaMedis.getText().toString().trim();
                namaMahasiswa = editNamaMahasiswa.getText().toString().trim();
                anamnesis = editAnamnesis.getText().toString().trim();
                keadaanUmum = editKeadaanUmum.getText().toString().trim();
                frekuensiNafas = editFrekuensiNafas.getText().toString().trim();
                frekuensiPulsus = editFrekuensiPulsus.getText().toString().trim();
                temperaturTubuh = editTemperaturTubuh.getText().toString().trim();
                kulitRambut = editKulitRambut.getText().toString().trim();
                selaputLendir = editSelaputLendir.getText().toString().trim();
                kelenjarLimfa = editKelenjarLimfa.getText().toString().trim();
                pernafasan = editPernafasan.getText().toString().trim();
                peredaranDarah = editPeredaranDarah.getText().toString().trim();
                pencernaan = editPencernaan.getText().toString().trim();
                kelaminPerkencingan = editKelaminPerkencingan.getText().toString().trim();
                syaraf = editSyaraf.getText().toString().trim();
                anggotaGerak = editAnggotaGerak.getText().toString().trim();
                beratBadan = editBeratBadan.getText().toString().trim();
                lainAnamnesis = editLainAnamnesis.getText().toString().trim();
                namaPemeriksaan = /*editNamaPemeriksaan.getText().toString().trim()*/lab1 + "," + lab2 + "," + lab3 + "," + lab4 + "," + lab5;
                ketLab = /*editKetLab.getText().toString().trim()*/ketLab1 + "," + ketLab2 + "," + ketLab3 + "," + ketLab4 + "," + ketLab5;
                diagnosis = /*editDiagnosis.getText().toString().trim()*/diagnosis1 + "," + diagnosis2 + "," + diagnosis3 + "," + diagnosis4 + "," + diagnosis5;
                prognosis = /*editPrognosis.getText().toString().trim()*/prognosis1 + "," + prognosis2 + "," + prognosis3 + "," + prognosis4 + "," + prognosis5;
                namaObat = /*editNamaObat.getText().toString().trim();*/
                        r1O1 + "," + r1O2 + "," + r1O3 + "," + r1O4 + "," + r1O5 + "," +
                                r2O1 + "," + r2O2 + "," + r2O3 + "," + r2O4 + "," + r2O5 + "," +
                                r3O1 + "," + r3O2 + "," + r3O3 + "," + r3O4 + "," + r3O5 + "," +
                                r4O1 + "," + r4O2 + "," + r4O3 + "," + r4O4 + "," + r4O5 + "," +
                                r5O1 + "," + r5O2 + "," + r5O3 + "," + r5O4 + "," + r5O5;
                jumlahResep = /*editJumlahResep.getText().toString().trim();*/
                        r1j1 + "," + r1j2 + "," + r1j3 + "," + r1j4 + "," + r1j5 + "," +
                                r2j1 + "," + r2j2 + "," + r2j3 + "," + r2j4 + "," + r2j5 + "," +
                                r3j1 + "," + r3j2 + "," + r3j3 + "," + r3j4 + "," + r3j5 + "," +
                                r4j1 + "," + r4j2 + "," + r4j3 + "," + r4j4 + "," + r4j5 + "," +
                                r5j1 + "," + r5j2 + "," + r5j3 + "," + r5j4 + "," + r5j5;
                satuanResep = /*editSatuanResep.getText().toString().trim();*/
                        r1s1 + "," + r1s2 + "," + r1s3 + "," + r1s4 + "," + r1s5 + "," +
                                r2s1 + "," + r2s2 + "," + r2s3 + "," + r2s4 + "," + r2s5 + "," +
                                r3s1 + "," + r3s2 + "," + r3s3 + "," + r3s4 + "," + r3s5 + "," +
                                r4s1 + "," + r4s2 + "," + r4s3 + "," + r4s4 + "," + r4s5 + "," +
                                r5s1 + "," + r5s2 + "," + r5s3 + "," + r5s4 + "," + r5s5;
                perintahPembuatan = /*editPerintahPembuatan.getText().toString().trim();*/perintah1 + "," + perintah2 + "," + perintah3 + "," + perintah4 + "," + perintah5;
                petunjukPenggunaan = /*editPetunjukPenggunaan.getText().toString().trim();*/petunjuk1 + "," + petunjuk2 + "," + petunjuk3 + "," + petunjuk4 + "," + petunjuk5;
                namaTindakan = /*editNamaTindakan.getText().toString().trim()*/tindakan1 + "," + tindakan2 + "," + tindakan3 + "," + tindakan4 + "," + tindakan5;
                jumlahTindakan = /*editJumlahTindakan.getText().toString().trim()*/jmlTindakan1 + "," + jmlTindakan2 + "," + jmlTindakan3 + "," + jmlTindakan4 + "," + jmlTindakan5;
                ketTindakan = /*editKetTindakan.getText().toString().trim()*/ketTIndakan1 + "," + ketTIndakan2 + "," + ketTIndakan3 + "," + ketTIndakan4 + "," + ketTIndakan5;


                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Log.d("Obat", namaObat);
                                Log.d("jumlah Obat", jumlahResep);
                                Log.d("Satuan Obat", satuanResep);

                                Log.d("Lab", namaPemeriksaan);
                                Log.d("Ket Lab", ketLab);

                                Log.d("Diagnosis", diagnosis);
                                Log.d("Prognosis", prognosis);

                                Log.d("Tindakan", namaTindakan);
                                Log.d("Jumlah Tindakan", jumlahTindakan);
                                Log.d("Ket Tindakan", ketTindakan);

                                progressDialog = new ProgressDialog(PasienTambahRekamMedis.this);
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();

                                for (String imagePath : imageList) {
                                    try {
                                        Bitmap bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
                                        final String encodedString = ImageBase64.encode(bitmap);

                                        String url = "http://testrsh.xyz/uploads/upload2.php";
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                fileName.add(response);

                                                if (fileName.size() == 4) {
                                                    imageList.clear();
                                                    foto1 = "http://testrsh.xyz/uploads/image/" + fileName.get(0) + ".jpeg";
                                                    foto2 = "http://testrsh.xyz/uploads/image/" + fileName.get(1) + ".jpeg";
                                                    foto3 = "http://testrsh.xyz/uploads/image/" + fileName.get(2) + ".jpeg";
                                                    foto4 = "http://testrsh.xyz/uploads/image/" + fileName.get(3) + ".jpeg";

                                                    sendPost(idRegistrasi, namaPemilik, namaHewan, tenagaMedis, namaMahasiswa, anamnesis, keadaanUmum, frekuensiNafas, frekuensiPulsus, temperaturTubuh,
                                                            kulitRambut, selaputLendir, kelenjarLimfa, pernafasan, peredaranDarah, pencernaan, kelaminPerkencingan, syaraf, anggotaGerak, beratBadan,
                                                            lainAnamnesis, namaPemeriksaan, ketLab, diagnosis, prognosis, namaObat, jumlahResep, satuanResep, perintahPembuatan, petunjukPenggunaan,
                                                            namaTindakan, jumlahTindakan, ketTindakan, foto1, foto2, foto3, foto4);

                                                    String statusAntrian = "0";

                                                    updateAntrian(idRegistrasi, statusAntrian);

                                                } else {
                                                    /*Toast.makeText(PasienTambahRekamMedis.this, "Foto anda kurang", Toast.LENGTH_LONG).show();*/
                                                }
                                            }
                                        }, new com.android.volley.Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                                            }
                                        }) {
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> params = new HashMap<>();
                                                params.put("image", encodedString);
                                                return params;
                                            }
                                        };

                                        myCommand.add(stringRequest);

                                    } catch (FileNotFoundException e) {
                                        Toast.makeText(getApplicationContext(), "Error while loading image/Foto anda kurang", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                myCommand.execute();
                                break;

                            case 1:
                                progressDialog = new ProgressDialog(PasienTambahRekamMedis.this);
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();

                                for (String imagePath : imageList) {
                                    try {
                                        Bitmap bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
                                        final String encodedString = ImageBase64.encode(bitmap);

                                        String url = "http://testrsh.xyz/uploads/upload2.php";
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                fileName.add(response);
                                                for (String fotoName : fileName) {
                                                    Log.i("Foto name: ", fotoName);
                                                    /*Toast.makeText(PasienTambahRekamMedis.this, fotoName, Toast.LENGTH_LONG).show();*/
                                                }

                                                if (fileName.size() == 4) {
                                                    imageList.clear();
                                                    foto1 = "http://testrsh.xyz/uploads/image/" + fileName.get(0) + ".jpeg";
                                                    foto2 = "http://testrsh.xyz/uploads/image/" + fileName.get(1) + ".jpeg";
                                                    foto3 = "http://testrsh.xyz/uploads/image/" + fileName.get(2) + ".jpeg";
                                                    foto4 = "http://testrsh.xyz/uploads/image/" + fileName.get(3) + ".jpeg";

                                                    sendPost(idRegistrasi, namaPemilik, namaHewan, tenagaMedis, namaMahasiswa, anamnesis, keadaanUmum, frekuensiNafas, frekuensiPulsus, temperaturTubuh,
                                                            kulitRambut, selaputLendir, kelenjarLimfa, pernafasan, peredaranDarah, pencernaan, kelaminPerkencingan, syaraf, anggotaGerak, beratBadan,
                                                            lainAnamnesis, namaPemeriksaan, ketLab, diagnosis, prognosis, namaObat, jumlahResep, satuanResep, perintahPembuatan, petunjukPenggunaan,
                                                            namaTindakan, jumlahTindakan, ketTindakan, foto1, foto2, foto3, foto4);

                                                    String semesterMahasiswa = "", pssm = "", pengobatan = "", ketPengobatan = "", statusInap = "1";

                                                    sendInap(idRegistrasi,
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

                                                    String statusAntrian = "0";

                                                    updateAntrian(idRegistrasi, statusAntrian);

                                                } else {
                                                    /*Toast.makeText(PasienTambahRekamMedis.this, "Foto anda kurang", Toast.LENGTH_LONG).show();*/
                                                }
                                            }
                                        }, new com.android.volley.Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                                            }
                                        }) {
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> params = new HashMap<>();
                                                params.put("image", encodedString);
                                                return params;
                                            }
                                        };

                                        myCommand.add(stringRequest);

                                    } catch (FileNotFoundException e) {
                                        Toast.makeText(getApplicationContext(), "Error while loading image/Foto anda kurang", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                myCommand.execute();
                                break;
                        }
                    }
                });
                builder.create().show();

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadJsonSpinner() {
        RestApiSpinner apiSpinner = ApiClient.getClient().create(RestApiSpinner.class);
        Call<ModelObat> callObat = apiSpinner.loadListObat();
        callObat.enqueue(new Callback<ModelObat>() {
            @Override
            public void onResponse(Call<ModelObat> call, Response<ModelObat> response) {
                if (response.isSuccessful()) {
                    List<Obat> obatObatan = response.body().getObat();
                    obatGlobal = obatObatan;

                    setupSpinnerObat(spinnerR1O1);
                    setupSpinnerObat(spinnerR1O2);
                    setupSpinnerObat(spinnerR1O3);
                    setupSpinnerObat(spinnerR1O4);
                    setupSpinnerObat(spinnerR1O5);
                    spinnerR1O1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r1O1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR1O2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r1O2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR1O3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r1O3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR1O4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r1O4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR1O5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r1O5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    setupSpinnerObat(spinnerR2O1);
                    setupSpinnerObat(spinnerR2O2);
                    setupSpinnerObat(spinnerR2O3);
                    setupSpinnerObat(spinnerR2O4);
                    setupSpinnerObat(spinnerR2O5);

                    spinnerR2O1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r2O1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR2O2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r2O2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR2O3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r2O3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR2O4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r2O4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR2O5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r2O5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    setupSpinnerObat(spinnerR3O1);
                    setupSpinnerObat(spinnerR3O2);
                    setupSpinnerObat(spinnerR3O3);
                    setupSpinnerObat(spinnerR3O4);
                    setupSpinnerObat(spinnerR3O5);

                    spinnerR3O1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r3O1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR3O2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r3O2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR3O3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r3O3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR3O4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r3O4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR3O5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r3O5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    setupSpinnerObat(spinnerR4O1);
                    setupSpinnerObat(spinnerR4O2);
                    setupSpinnerObat(spinnerR4O3);
                    setupSpinnerObat(spinnerR4O4);
                    setupSpinnerObat(spinnerR4O5);

                    spinnerR4O1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r4O1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR4O2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r4O2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR4O3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r4O3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR4O4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r4O4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR4O5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r4O5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                    setupSpinnerObat(spinnerR5O1);
                    setupSpinnerObat(spinnerR5O2);
                    setupSpinnerObat(spinnerR5O3);
                    setupSpinnerObat(spinnerR5O4);
                    setupSpinnerObat(spinnerR5O5);

                    spinnerR5O1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r5O1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR5O2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r5O2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR5O3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r5O3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR5O4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r5O4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerR5O5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            r5O5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ModelObat> call, Throwable t) {

            }
        });

        Call<ModelTindakan> callTindakan = apiSpinner.loadListTindakan();
        callTindakan.enqueue(new Callback<ModelTindakan>() {
            @Override
            public void onResponse(Call<ModelTindakan> call, Response<ModelTindakan> response) {
                if (response.isSuccessful()) {
                    List<Tindakan> tindaken = response.body().getTindakan();
                    tindakanList = tindaken;

                    setupSpinnerTindakan(spinnerTindakan1);
                    setupSpinnerTindakan(spinnerTindakan2);
                    setupSpinnerTindakan(spinnerTindakan3);
                    setupSpinnerTindakan(spinnerTindakan4);
                    setupSpinnerTindakan(spinnerTindakan5);

                    spinnerTindakan1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tindakan1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerTindakan2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tindakan2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerTindakan3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tindakan3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerTindakan4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tindakan4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerTindakan5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tindakan5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ModelTindakan> call, Throwable t) {

            }
        });

        Call<ModelLab> callLab = apiSpinner.loadListLab();
        callLab.enqueue(new Callback<ModelLab>() {
            @Override
            public void onResponse(Call<ModelLab> call, Response<ModelLab> response) {
                if (response.isSuccessful()) {
                    List<Lab> labs = response.body().getLab();
                    labList = labs;

                    setupSpinnerLab(spinnerLab1);
                    setupSpinnerLab(spinnerLab2);
                    setupSpinnerLab(spinnerLab3);
                    setupSpinnerLab(spinnerLab4);
                    setupSpinnerLab(spinnerLab5);

                    spinnerLab1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            lab1 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerLab2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            lab2 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerLab3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            lab3 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerLab4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            lab4 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spinnerLab5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            lab5 = adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ModelLab> call, Throwable t) {

            }
        });
    }

    private void setupSpinnerObat(Spinner spinner) {
        String items[] = new String[obatGlobal.size()];
        for (int i = 0; i < obatGlobal.size(); i++) {
            items[i] = obatGlobal.get(i).getNamaObat();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);
    }

    private void setupSpinnerTindakan(Spinner spinner) {
        String items[] = new String[tindakanList.size()];
        for (int i = 0; i < tindakanList.size(); i++) {
            items[i] = tindakanList.get(i).getNamaTindakan();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);
    }

    private void setupSpinnerLab(Spinner spinner) {
        String items[] = new String[labList.size()];
        for (int i = 0; i < labList.size(); i++) {
            items[i] = labList.get(i).getNamaPemeriksaan();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);
    }

    private void setupSpinnerSatuanObat(Spinner spinner) {
        ArrayAdapter spinnerAdapterSatuanObat = ArrayAdapter.createFromResource(this,
                R.array.array_satuan_obat, android.R.layout.simple_spinner_item);

        spinnerAdapterSatuanObat.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(spinnerAdapterSatuanObat);

        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String satuanObat = adapterView.getItemAtPosition(i).toString();
                test(satuanObat,satuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }
}
