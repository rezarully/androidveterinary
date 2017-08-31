package com.rsh.android.rekammedisbosimrsh.pasien;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.Verify;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.inappasien.InapPasienActivity;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Lab;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelLab;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelObat;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelTindakan;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Obat;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.Tindakan;
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

public class PasienTambahRekamMedisInap extends AppCompatActivity {

    private EditText
            editDiagnosis,
            editBeratBaadan,
            editNamaMahasiswa,
            editSemMahasiswa,
            editNamaObat,
            editJumlahResep,
            editSatuanResep,
            editPerintahPembuatan,
            editPetunjukPenggunaan,
            editPssm,
            editPengobatan,
            editKetPengobatan,
            editStatusInap;

    private TextView textIdRegistrasi, textNamaPemilik, textNamaHewan;

    private String
            idRegistrasi,
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
            foto4;

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

    private Button btnTambahPengobatan1, btnTambahPengobatan2, btnTambahPengobatan3, btnTambahPengobatan4, btnTambahPengobatan5,
            btnHapusPengobatan1, btnHapusPengobatan2, btnHapusPengobatan3, btnHapusPengobatan4, btnHapusPengobatan5;

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

    private String pengobatan1,pengobatan2,pengobatan3,pengobatan4,ketPengobatan1,ketPengobatan2,ketPengobatan3,ketPengobatan4;

    private String pssm1,pssm2,pssm3,pssm4;

    private EditText editR1O1, editR1O2, editR1O3, editR1O4, editR1O5,
            editR2O1, editR2O2, editR2O3, editR2O4, editR2O5,
            editR3O1, editR3O2, editR3O3, editR3O4, editR3O5,
            editR4O1, editR4O2, editR4O3, editR4O4, editR4O5,
            editR5O1, editR5O2, editR5O3, editR5O4, editR5O5;

    private EditText editPP1, editPP2, editPP3, editPP4, editPP5,
            editPetunjuk1, editPetunjuk2, editPetunjuk3, editPetunjuk4, editPetunjuk5;

    private EditText editPengobatan1, editPengobatan2, editPengobatan3, editPengobatan4,
            editKetPengobatan1, editKetPengobatan2, editKetPengobatan3, editKetPengobatan4;

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

    private Spinner spinnerPssm1, spinnerPssm2, spinnerPssm3, spinnerPssm4;

    private LinearLayout llResep1, llResep2, llResep3, llResep4, llResep5,
            llR1O2, llR1O3, llR1O4, llR1O5,
            llR2O2, llR2O3, llR2O4, llR2O5,
            llR3O2, llR3O3, llR3O4, llR3O5,
            llR4O2, llR4O3, llR4O4, llR4O5,
            llR5O2, llR5O3, llR5O4, llR5O5;

    private LinearLayout llPengobatan2, llPengobatan3, llPengobatan4, llPengobatan5;

    private ArrayList<String> imageList = new ArrayList<>();

    private ArrayList<String> fileName = new ArrayList<>();

    CameraPhoto cameraPhoto;

    private final int CAMERA_REQUEST_1 = 1001, CAMERA_REQUEST_2 = 1002, CAMERA_REQUEST_3 = 1003, CAMERA_REQUEST_4 = 1004;

    private ImageView imageView1, imageView2, imageView3, imageView4;

    private List<DataPasien> pasien;

    private ProgressDialog progress;

    private Spinner spinnerPssmPasienInap;

    private List<Obat> obatGlobal;
    private List<Tindakan> tindakanList;
    private List<Lab> labList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_tambah_rekam_medis_inap);

        Verify.verifyCameraPermissions(PasienTambahRekamMedisInap.this);
        Verify.verifyStoragePermissions(PasienTambahRekamMedisInap.this);

        editDiagnosis = (EditText) findViewById(R.id.edit_pasien_rm_inap_diagnosis);
        editBeratBaadan = (EditText) findViewById(R.id.edit_pasien_rm_inap_berat_badan);
        editNamaMahasiswa = (EditText) findViewById(R.id.edit_pasien_rm_inap_nama_mahasiswa);
        editSemMahasiswa = (EditText) findViewById(R.id.edit_pasien_rm_inap_semester_mahasiswa);
        /*editNamaObat = (EditText) findViewById(R.id.edit_pasien_rm_inap_nama_obat);
        editJumlahResep = (EditText) findViewById(R.id.edit_pasien_rm_inap_jumlah_resep);
        editSatuanResep = (EditText) findViewById(R.id.edit_pasien_rm_inap_satuan_resep);*/
      /*  editPerintahPembuatan = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan);
        editPetunjukPenggunaan = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan);*/
        /*editPssm = (EditText) findViewById(R.id.edit_pasien_rm_inap_pssm);
        editPengobatan = (EditText) findViewById(R.id.edit_pasien_rm_inap_pengobatan);
        editKetPengobatan = (EditText) findViewById(R.id.edit_pasien_rm_inap_ket_pengobatan);*/
        editStatusInap = (EditText) findViewById(R.id.edit_pasien_rm_inap_status_inap);

        textIdRegistrasi = (TextView) findViewById(R.id.tv_pasien_tambah_rm_inap_id_registrasi);
        textNamaPemilik = (TextView) findViewById(R.id.tv_pasien_tambah_rm_inap_nama_pemilik);
        textNamaHewan = (TextView) findViewById(R.id.tv_pasien_tambah_rm_inap_nama_hewan);

        imageView1 = (ImageView) findViewById(R.id.image_edit_pasien_rm_inap_foto_1);
        imageView2 = (ImageView) findViewById(R.id.image_edit_pasien_rm_inap_foto_2);
        imageView3 = (ImageView) findViewById(R.id.image_edit_pasien_rm_inap_foto_3);
        imageView4 = (ImageView) findViewById(R.id.image_edit_pasien_rm_inap_foto_4);

        /*linear layout obat*/
        llResep1 = (LinearLayout) findViewById(R.id.ll_rm_inap_resep_1);
        llResep2 = (LinearLayout) findViewById(R.id.ll_rm_inap_resep_2);
        llResep3 = (LinearLayout) findViewById(R.id.ll_rm_inap_resep_3);
        llResep4 = (LinearLayout) findViewById(R.id.ll_rm_inap_resep_4);
        llResep5 = (LinearLayout) findViewById(R.id.ll_rm_inap_resep_5);

        llR1O2 = (LinearLayout) findViewById(R.id.ll_inap_r1o2);
        llR1O3 = (LinearLayout) findViewById(R.id.ll_inap_r1o3);
        llR1O4 = (LinearLayout) findViewById(R.id.ll_inap_r1o4);
        llR1O5 = (LinearLayout) findViewById(R.id.ll_inap_r1o5);

        llR2O2 = (LinearLayout) findViewById(R.id.ll_inap_r2o2);
        llR2O3 = (LinearLayout) findViewById(R.id.ll_inap_r2o3);
        llR2O4 = (LinearLayout) findViewById(R.id.ll_inap_r2o4);
        llR2O5 = (LinearLayout) findViewById(R.id.ll_inap_r2o5);

        llR3O2 = (LinearLayout) findViewById(R.id.ll_inap_r3o2);
        llR3O3 = (LinearLayout) findViewById(R.id.ll_inap_r3o3);
        llR3O4 = (LinearLayout) findViewById(R.id.ll_inap_r3o4);
        llR3O5 = (LinearLayout) findViewById(R.id.ll_inap_r3o5);

        llR4O2 = (LinearLayout) findViewById(R.id.ll_inap_r4o2);
        llR4O3 = (LinearLayout) findViewById(R.id.ll_inap_r4o3);
        llR4O4 = (LinearLayout) findViewById(R.id.ll_inap_r4o4);
        llR4O5 = (LinearLayout) findViewById(R.id.ll_inap_r4o5);

        llR5O2 = (LinearLayout) findViewById(R.id.ll_inap_r5o2);
        llR5O3 = (LinearLayout) findViewById(R.id.ll_inap_r5o3);
        llR5O4 = (LinearLayout) findViewById(R.id.ll_inap_r5o4);
        llR5O5 = (LinearLayout) findViewById(R.id.ll_inap_r5o5);

        /*Edit Obat*/
        editR1O1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r1j1);
        editR1O2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r1j2);
        editR1O3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r1j3);
        editR1O4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r1j4);
        editR1O5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r1j5);

        editR2O1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r2j1);
        editR2O2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r2j2);
        editR2O3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r2j3);
        editR2O4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r2j4);
        editR2O5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r2j5);

        editR3O1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r3j1);
        editR3O2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r3j2);
        editR3O3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r3j3);
        editR3O4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r3j4);
        editR3O5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r3j5);

        editR4O1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r4j1);
        editR4O2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r4j2);
        editR4O3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r4j3);
        editR4O4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r4j4);
        editR4O5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r4j5);

        editR5O1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r5j1);
        editR5O2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r5j2);
        editR5O3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r5j3);
        editR5O4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r5j4);
        editR5O5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_r5j5);

        editPP1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan_resep_1);
        editPP2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan_resep_2);
        editPP3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan_resep_3);
        editPP4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan_resep_4);
        editPP5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_perintah_pembuatan_resep_5);

        editPetunjuk1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan_resep_1);
        editPetunjuk2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan_resep_2);
        editPetunjuk3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan_resep_3);
        editPetunjuk4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan_resep_4);
        editPetunjuk5 = (EditText) findViewById(R.id.edit_pasien_rm_inap_petunjuk_penggunaan_resep_5);

        /*ArrayAdapter spinnerAdapterSatuanObat = ArrayAdapter.createFromResource(this,
                R.array.array_satuan_obat, android.R.layout.simple_spinner_item);

        spinnerAdapterSatuanObat.setDropDownViewResource(android.R.layout.simple_list_item_1);*/

        loadJsonSpinner();

        /*spinner*/
        spinnerR1O1 = (Spinner) findViewById(R.id.spinner_rm_inap_r1o1);
        spinnerR1O2 = (Spinner) findViewById(R.id.spinner_rm_inap_r1o2);
        spinnerR1O3 = (Spinner) findViewById(R.id.spinner_rm_inap_r1o3);
        spinnerR1O4 = (Spinner) findViewById(R.id.spinner_rm_inap_r1o4);
        spinnerR1O5 = (Spinner) findViewById(R.id.spinner_rm_inap_r1o5);

        spinnerR2O1 = (Spinner) findViewById(R.id.spinner_rm_inap_r2o1);
        spinnerR2O2 = (Spinner) findViewById(R.id.spinner_rm_inap_r2o2);
        spinnerR2O3 = (Spinner) findViewById(R.id.spinner_rm_inap_r2o3);
        spinnerR2O4 = (Spinner) findViewById(R.id.spinner_rm_inap_r2o4);
        spinnerR2O5 = (Spinner) findViewById(R.id.spinner_rm_inap_r2o5);

        spinnerR3O1 = (Spinner) findViewById(R.id.spinner_rm_inap_r3o1);
        spinnerR3O2 = (Spinner) findViewById(R.id.spinner_rm_inap_r3o2);
        spinnerR3O3 = (Spinner) findViewById(R.id.spinner_rm_inap_r3o3);
        spinnerR3O4 = (Spinner) findViewById(R.id.spinner_rm_inap_r3o4);
        spinnerR3O5 = (Spinner) findViewById(R.id.spinner_rm_inap_r3o5);

        spinnerR4O1 = (Spinner) findViewById(R.id.spinner_rm_inap_r4o1);
        spinnerR4O2 = (Spinner) findViewById(R.id.spinner_rm_inap_r4o2);
        spinnerR4O3 = (Spinner) findViewById(R.id.spinner_rm_inap_r4o3);
        spinnerR4O4 = (Spinner) findViewById(R.id.spinner_rm_inap_r4o4);
        spinnerR4O5 = (Spinner) findViewById(R.id.spinner_rm_inap_r4o5);

        spinnerR5O1 = (Spinner) findViewById(R.id.spinner_rm_inap_r5o1);
        spinnerR5O2 = (Spinner) findViewById(R.id.spinner_rm_inap_r5o2);
        spinnerR5O3 = (Spinner) findViewById(R.id.spinner_rm_inap_r5o3);
        spinnerR5O4 = (Spinner) findViewById(R.id.spinner_rm_inap_r5o4);
        spinnerR5O5 = (Spinner) findViewById(R.id.spinner_rm_inap_r5o5);

        spinnerR1j1 = (Spinner) findViewById(R.id.spinner_rm_inap_r1s1);
        spinnerR1j2 = (Spinner) findViewById(R.id.spinner_rm_inap_r1s2);
        spinnerR1j3 = (Spinner) findViewById(R.id.spinner_rm_inap_r1s3);
        spinnerR1j4 = (Spinner) findViewById(R.id.spinner_rm_inap_r1s4);
        spinnerR1j5 = (Spinner) findViewById(R.id.spinner_rm_inap_r1s5);

        spinnerR2j1 = (Spinner) findViewById(R.id.spinner_rm_inap_r2s1);
        spinnerR2j2 = (Spinner) findViewById(R.id.spinner_rm_inap_r2s2);
        spinnerR2j3 = (Spinner) findViewById(R.id.spinner_rm_inap_r2s3);
        spinnerR2j4 = (Spinner) findViewById(R.id.spinner_rm_inap_r2s4);
        spinnerR2j5 = (Spinner) findViewById(R.id.spinner_rm_inap_r2s5);

        spinnerR3j1 = (Spinner) findViewById(R.id.spinner_rm_inap_r3s1);
        spinnerR3j2 = (Spinner) findViewById(R.id.spinner_rm_inap_r3s2);
        spinnerR3j3 = (Spinner) findViewById(R.id.spinner_rm_inap_r3s3);
        spinnerR3j4 = (Spinner) findViewById(R.id.spinner_rm_inap_r3s4);
        spinnerR3j5 = (Spinner) findViewById(R.id.spinner_rm_inap_r3s5);

        spinnerR4j1 = (Spinner) findViewById(R.id.spinner_rm_inap_r4s1);
        spinnerR4j2 = (Spinner) findViewById(R.id.spinner_rm_inap_r4s2);
        spinnerR4j3 = (Spinner) findViewById(R.id.spinner_rm_inap_r4s3);
        spinnerR4j4 = (Spinner) findViewById(R.id.spinner_rm_inap_r4s4);
        spinnerR4j5 = (Spinner) findViewById(R.id.spinner_rm_inap_r4s5);

        spinnerR5j1 = (Spinner) findViewById(R.id.spinner_rm_inap_r5s1);
        spinnerR5j2 = (Spinner) findViewById(R.id.spinner_rm_inap_r5s2);
        spinnerR5j3 = (Spinner) findViewById(R.id.spinner_rm_inap_r5s3);
        spinnerR5j4 = (Spinner) findViewById(R.id.spinner_rm_inap_r5s4);
        spinnerR5j5 = (Spinner) findViewById(R.id.spinner_rm_inap_r5s5);

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

        /*Tambah RESEP*/
        btnTambahResep1 = (Button) findViewById(R.id.btn_tambah_resep_inap_1);
        btnTambahResep2 = (Button) findViewById(R.id.btn_tambah_resep_inap_2);
        btnTambahResep3 = (Button) findViewById(R.id.btn_tambah_resep_inap_3);
        btnTambahResep4 = (Button) findViewById(R.id.btn_tambah_resep_inap_4);

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

        btnHapusResep2 = (Button) findViewById(R.id.btn_hapus_resep_inap_2);
        btnHapusResep3 = (Button) findViewById(R.id.btn_hapus_resep_inap_3);
        btnHapusResep4 = (Button) findViewById(R.id.btn_hapus_resep_inap_4);
        btnHapusResep5 = (Button) findViewById(R.id.btn_hapus_resep_inap_5);

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

        btnTambahObatR1O1 = (Button) findViewById(R.id.btn_tambah_inap_r1o1);
        btnTambahObatR1O2 = (Button) findViewById(R.id.btn_tambah_inap_r1o2);
        btnTambahObatR1O3 = (Button) findViewById(R.id.btn_tambah_inap_r1o3);
        btnTambahObatR1O4 = (Button) findViewById(R.id.btn_tambah_inap_r1o4);

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

        btnTambahObatR2O1 = (Button) findViewById(R.id.btn_tambah_inap_r2o1);
        btnTambahObatR2O2 = (Button) findViewById(R.id.btn_tambah_inap_r2o2);
        btnTambahObatR2O3 = (Button) findViewById(R.id.btn_tambah_inap_r2o3);
        btnTambahObatR2O4 = (Button) findViewById(R.id.btn_tambah_inap_r2o4);

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

        btnTambahObatR3O1 = (Button) findViewById(R.id.btn_tambah_inap_r3o1);
        btnTambahObatR3O2 = (Button) findViewById(R.id.btn_tambah_inap_r3o2);
        btnTambahObatR3O3 = (Button) findViewById(R.id.btn_tambah_inap_r3o3);
        btnTambahObatR3O4 = (Button) findViewById(R.id.btn_tambah_inap_r3o4);

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

        btnTambahObatR4O1 = (Button) findViewById(R.id.btn_tambah_inap_r4o1);
        btnTambahObatR4O2 = (Button) findViewById(R.id.btn_tambah_inap_r4o2);
        btnTambahObatR4O3 = (Button) findViewById(R.id.btn_tambah_inap_r4o3);
        btnTambahObatR4O4 = (Button) findViewById(R.id.btn_tambah_inap_r4o4);

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

        btnTambahObatR5O1 = (Button) findViewById(R.id.btn_tambah_inap_r5o1);
        btnTambahObatR5O2 = (Button) findViewById(R.id.btn_tambah_inap_r5o2);
        btnTambahObatR5O3 = (Button) findViewById(R.id.btn_tambah_inap_r5o3);
        btnTambahObatR5O4 = (Button) findViewById(R.id.btn_tambah_inap_r5o4);

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

        btnMinusObatR1O2 = (Button) findViewById(R.id.btn_minus_inap_r1o2);
        btnMinusObatR1O3 = (Button) findViewById(R.id.btn_minus_inap_r1o3);
        btnMinusObatR1O4 = (Button) findViewById(R.id.btn_minus_inap_r1o4);
        btnMinusObatR1O5 = (Button) findViewById(R.id.btn_minus_inap_r1o5);

        btnMinusObatR1O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O2.setVisibility(View.GONE);
            }
        });

        btnMinusObatR1O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O3.setVisibility(View.GONE);
            }
        });

        btnMinusObatR1O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O4.setVisibility(View.GONE);
            }
        });

        btnMinusObatR1O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR1O5.setVisibility(View.GONE);
            }
        });

        btnMinusObatR2O2 = (Button) findViewById(R.id.btn_minus_inap_r2o2);
        btnMinusObatR2O3 = (Button) findViewById(R.id.btn_minus_inap_r2o3);
        btnMinusObatR2O4 = (Button) findViewById(R.id.btn_minus_inap_r2o4);
        btnMinusObatR2O5 = (Button) findViewById(R.id.btn_minus_inap_r2o5);

        btnMinusObatR2O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O2.setVisibility(View.GONE);
            }
        });

        btnMinusObatR2O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O3.setVisibility(View.GONE);
            }
        });

        btnMinusObatR2O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O4.setVisibility(View.GONE);
            }
        });

        btnMinusObatR2O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR2O5.setVisibility(View.GONE);
            }
        });

        btnMinusObatR3O2 = (Button) findViewById(R.id.btn_minus_inap_r3o2);
        btnMinusObatR3O3 = (Button) findViewById(R.id.btn_minus_inap_r3o3);
        btnMinusObatR3O4 = (Button) findViewById(R.id.btn_minus_inap_r3o4);
        btnMinusObatR3O5 = (Button) findViewById(R.id.btn_minus_inap_r3o5);

        btnMinusObatR3O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O2.setVisibility(View.GONE);
            }
        });

        btnMinusObatR3O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O3.setVisibility(View.GONE);
            }
        });

        btnMinusObatR3O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O4.setVisibility(View.GONE);
            }
        });

        btnMinusObatR3O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR3O5.setVisibility(View.GONE);
            }
        });

        btnMinusObatR4O2 = (Button) findViewById(R.id.btn_minus_inap_r4o2);
        btnMinusObatR4O3 = (Button) findViewById(R.id.btn_minus_inap_r4o3);
        btnMinusObatR4O4 = (Button) findViewById(R.id.btn_minus_inap_r4o4);
        btnMinusObatR4O5 = (Button) findViewById(R.id.btn_minus_inap_r4o5);

        btnMinusObatR4O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O2.setVisibility(View.GONE);
            }
        });

        btnMinusObatR4O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O3.setVisibility(View.GONE);
            }
        });

        btnMinusObatR4O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O4.setVisibility(View.GONE);
            }
        });

        btnMinusObatR4O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR4O5.setVisibility(View.GONE);
            }
        });

        btnMinusObatR5O2 = (Button) findViewById(R.id.btn_minus_inap_r5o2);
        btnMinusObatR5O3 = (Button) findViewById(R.id.btn_minus_inap_r5o3);
        btnMinusObatR5O4 = (Button) findViewById(R.id.btn_minus_inap_r5o4);
        btnMinusObatR5O5 = (Button) findViewById(R.id.btn_minus_inap_r5o5);

        btnMinusObatR5O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O2.setVisibility(View.GONE);
            }
        });

        btnMinusObatR5O3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O3.setVisibility(View.GONE);
            }
        });

        btnMinusObatR5O4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O4.setVisibility(View.GONE);
            }
        });

        btnMinusObatR5O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llR5O5.setVisibility(View.GONE);
            }
        });

        llPengobatan2 = (LinearLayout) findViewById(R.id.ll_pengobatan_inap_2);
        llPengobatan3 = (LinearLayout) findViewById(R.id.ll_pengobatan_inap_3);
        llPengobatan4 = (LinearLayout) findViewById(R.id.ll_pengobatan_inap_4);

        btnTambahPengobatan1 = (Button) findViewById(R.id.btn_rm_inap_tambah_pengobatan_1);
        btnTambahPengobatan2 = (Button) findViewById(R.id.btn_rm_inap_tambah_pengobatan_2);
        btnTambahPengobatan3 = (Button) findViewById(R.id.btn_rm_inap_tambah_pengobatan_3);
        btnTambahPengobatan4 = (Button) findViewById(R.id.btn_rm_inap_tambah_pengobatan_4);

        btnTambahPengobatan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan2.setVisibility(View.VISIBLE);
            }
        });

        btnTambahPengobatan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan3.setVisibility(View.VISIBLE);
            }
        });

        btnTambahPengobatan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan4.setVisibility(View.VISIBLE);
            }
        });


        btnHapusPengobatan2 = (Button) findViewById(R.id.btn_rm_inap_hapus_pengobatan_2);
        btnHapusPengobatan3 = (Button) findViewById(R.id.btn_rm_inap_hapus_pengobatan_3);
        btnHapusPengobatan4 = (Button) findViewById(R.id.btn_rm_inap_hapus_pengobatan_4);

        btnHapusPengobatan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan2.setVisibility(View.GONE);
            }
        });

        btnHapusPengobatan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan3.setVisibility(View.GONE);
            }
        });

        btnHapusPengobatan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPengobatan4.setVisibility(View.GONE);
            }
        });


        spinnerPssm1 = (Spinner) findViewById(R.id.spinner_pssm_inap_pasien_1);
        spinnerPssm2 = (Spinner) findViewById(R.id.spinner_pssm_inap_pasien_2);
        spinnerPssm3 = (Spinner) findViewById(R.id.spinner_pssm_inap_pasien_3);
        spinnerPssm4 = (Spinner) findViewById(R.id.spinner_pssm_inap_pasien_4);

        setupSpinerPssm(spinnerPssm1);
        setupSpinerPssm(spinnerPssm2);
        setupSpinerPssm(spinnerPssm3);
        setupSpinerPssm(spinnerPssm4);

        spinnerPssm1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pssm1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pssm1 = "";
            }
        });

        spinnerPssm2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pssm2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pssm2 = "";
            }
        });

        spinnerPssm3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pssm3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pssm3 = "";
            }
        });

        spinnerPssm4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pssm4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pssm4 = "";
            }
        });

        editPengobatan1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_pengobatan_1);
        editPengobatan2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_pengobatan_2);
        editPengobatan3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_pengobatan_3);
        editPengobatan4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_pengobatan_4);

        editKetPengobatan1 = (EditText) findViewById(R.id.edit_pasien_rm_inap_ket_pengobatan_1);
        editKetPengobatan2 = (EditText) findViewById(R.id.edit_pasien_rm_inap_ket_pengobatan_2);
        editKetPengobatan3 = (EditText) findViewById(R.id.edit_pasien_rm_inap_ket_pengobatan_3);
        editKetPengobatan4 = (EditText) findViewById(R.id.edit_pasien_rm_inap_ket_pengobatan_4);

        final Intent intent = getIntent();
        String intent1 = intent.getStringExtra(InapPasienActivity.ID_PASIEN_INAP);

        if (TextUtils.isEmpty(intent1)) {
            idRegistrasi = intent.getStringExtra(PasienRekamMedisInap.ID_PASIEN_RM_INAP);
            namaPemilik = intent.getStringExtra(PasienRekamMedisInap.NAMA_PEMILIK_RM_INAP);
            namaHewan = intent.getStringExtra(PasienRekamMedisInap.NAMA_HEWAN_RM_INAP);
        } else {
            idRegistrasi = intent.getStringExtra(InapPasienActivity.ID_PASIEN_INAP);
            namaPemilik = intent.getStringExtra(InapPasienActivity.NAMA_PEMILIK_PASIEN_INAP);
            namaHewan = intent.getStringExtra(InapPasienActivity.NAMA_HEWAN_PASIEN_INAP);
        }

        textIdRegistrasi.setText(idRegistrasi);
        textNamaHewan.setText(namaHewan);
        textNamaPemilik.setText(namaPemilik);

        cameraPhoto = new CameraPhoto(getApplicationContext());

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST_1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST_2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST_3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST_4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_1) {
                String photoPath1 = cameraPhoto.getPhotoPath();
                imageList.add(photoPath1);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath1).requestSize(512, 512).getBitmap();
                    imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView1.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_2) {
                String photoPath2 = cameraPhoto.getPhotoPath();
                imageList.add(photoPath2);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath2).requestSize(512, 512).getBitmap();
                    imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView2.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_3) {
                String photoPath3 = cameraPhoto.getPhotoPath();
                imageList.add(photoPath3);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath3).requestSize(512, 512).getBitmap();
                    imageView3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView3.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAMERA_REQUEST_4) {
                String photoPath4 = cameraPhoto.getPhotoPath();
                imageList.add(photoPath4);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath4).requestSize(512, 512).getBitmap();
                    imageView4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView4.setImageBitmap(bitmap);
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
        getMenuInflater().inflate(R.menu.menu_tambah_inap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_simpan_rm_inap:
                final MyCommand myCommand = new MyCommand(getApplicationContext());

                progress = new ProgressDialog(PasienTambahRekamMedisInap.this);
                progress.setMessage("Loading...");
                progress.show();

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

                pengobatan1 = editPengobatan1.getText().toString().trim();
                pengobatan2 = editPengobatan2.getText().toString().trim();
                pengobatan3 = editPengobatan3.getText().toString().trim();
                pengobatan4 = editPengobatan4.getText().toString().trim();

                ketPengobatan1 = editKetPengobatan1.getText().toString().trim();
                ketPengobatan2 = editKetPengobatan2.getText().toString().trim();
                ketPengobatan3 = editKetPengobatan3.getText().toString().trim();
                ketPengobatan4 = editKetPengobatan4.getText().toString().trim();

                diagnosis = editDiagnosis.getText().toString().trim();
                beratBadan = editBeratBaadan.getText().toString().trim();
                namaMahasiswa = editNamaMahasiswa.getText().toString().trim();
                semesterMahasiswa = editSemMahasiswa.getText().toString().trim();
                /*namaObat = *//*editNamaObat.getText().toString().trim();*//*"";
                jumlahResep = *//*editJumlahResep.getText().toString().trim();*//*"";
                satuanResep = *//*editSatuanResep.getText().toString().trim();*//*"";*/
                /*perintahPembuatan = editPerintahPembuatan.getText().toString().trim();
                petunjukPenggunaan = editPetunjukPenggunaan.getText().toString().trim();*/
                pssm = /*editPssm.getText().toString().trim()*/pssm1+","+pssm2+","+pssm3+","+pssm4;
                pengobatan = /*editPengobatan.getText().toString().trim()*/pengobatan1+","+pengobatan2+","+pengobatan3+","+pengobatan4;
                ketPengobatan = /*editKetPengobatan.getText().toString().trim()*/ketPengobatan1+","+ketPengobatan2+","+ketPengobatan3+","+ketPengobatan4;
                statusInap = editStatusInap.getText().toString().trim();

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

                foto1 = "";
                foto2 = "";
                foto3 = "";
                foto4 = "";

                // Respond to a click on the "Up" arrow button in the app bar

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

                /*for (String imagePath : imageList) {
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
                                }
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progress.dismiss();
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
                }*/

                myCommand.execute();
                return true;

            case android.R.id.home:
                // Navigate back to parent activity (PasienActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                    progress.dismiss();
                    Toast.makeText(PasienTambahRekamMedisInap.this, "Post submitted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PasienTambahRekamMedisInap.this, "Post failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InputRmInap> call, Throwable t) {
                Toast.makeText(PasienTambahRekamMedisInap.this, "Post failed", Toast.LENGTH_SHORT).show();
            }
        });

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
    }

    private void setupSpinnerObat(Spinner spinner) {
        String items[] = new String[obatGlobal.size()];
        for (int i = 0; i < obatGlobal.size(); i++) {
            items[i] = obatGlobal.get(i).getNamaObat();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);
    }

    private void setupSpinerPssm(Spinner spinner) {
        ArrayAdapter spinnerAdapterPssm = ArrayAdapter.createFromResource(this,
                R.array.array_pssm, android.R.layout.simple_spinner_item);

        spinnerAdapterPssm.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(spinnerAdapterPssm);

        /*spinnerPssmPasienInap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pssm = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
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
