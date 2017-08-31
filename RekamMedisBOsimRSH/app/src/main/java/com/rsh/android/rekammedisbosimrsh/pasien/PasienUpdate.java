package com.rsh.android.rekammedisbosimrsh.pasien;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienUpdate extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText mNoRegistrasi;
    private EditText mNoRekamMedis;
    private EditText mNamaPemilik;
    private EditText mNamaHewan;
    private EditText mJenisHewan;
    private EditText mTtlHewan;
    private EditText mUsername;
    private EditText mStatAntrian;
    private EditText mGender;

    private String
            idPasien,
            noRegistrasi,
            noRm,
            namaPemilik,
            namaHewan,
            jenisHewan,
            signalemenTtl,
            username,
            statusAntrian, signalemenKelamin, fotoProfil;

    private static final String JENIS_ANJING = "Anjing";
    private static final String JENIS_KUCING = "Kucing";
    private static final String JENIS_UNGGAS = "Unggas";
    private static final String JENIS_EKSOTIS = "Eksotis";

    private Spinner spinnerJenisHewan;

    private Calendar calendar;
    private DatePicker datePicker;
    private int day, month, year;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter, dateFormatKirim;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_update);

        mNoRegistrasi = (EditText) findViewById(R.id.update_pasien_no_registrasi);
        mNoRekamMedis = (EditText) findViewById(R.id.update_pasien_no_rm);
        mNamaPemilik = (EditText) findViewById(R.id.update_pasien_nama_pemilik);
        mNamaHewan = (EditText) findViewById(R.id.update_pasien_nama_hewan);
        /*mJenisHewan = (EditText) findViewById(R.id.update_pasien_jenis_hewan);*/

        mTtlHewan = (EditText) findViewById(R.id.update_pasien_ttl);
        mTtlHewan.setInputType(InputType.TYPE_NULL);

        mUsername = (EditText) findViewById(R.id.update_pasien_username);
        mStatAntrian = (EditText) findViewById(R.id.update_pasien_status_antrian);
        /*mGender = (EditText) findViewById(R.id.update_pasien_gender);*/

        final Intent intent = getIntent();

        mNoRegistrasi.setText(intent.getStringExtra(PasienLihat.NO_REGISTRASI));
        mNoRekamMedis.setText(intent.getStringExtra(PasienLihat.NO_REKAM_MEDIS));
        mNamaPemilik.setText(intent.getStringExtra(PasienLihat.NAMA_PEMILIK));
        mNamaHewan.setText(intent.getStringExtra(PasienLihat.NAMA_HEWAN));
        /*mJenisHewan.setText(intent.getStringExtra(PasienLihat.JENIS_HEWAN));*/
        mTtlHewan.setText(intent.getStringExtra(PasienLihat.SIGNALEMEN_TTL));
        mUsername.setText(intent.getStringExtra(PasienLihat.USERNAME));
        mStatAntrian.setText(intent.getStringExtra(PasienLihat.STATUS_ANTRIAN));
        /*mGender.setText(intent.getStringExtra(PasienLihat.SIGNALEMEN_KELAMIN));*/

        radioSexGroup = (RadioGroup) findViewById(R.id.radio_sex_group_update);

        spinnerJenisHewan = (Spinner) findViewById(R.id.spinner_update_jenis_hewan);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatKirim = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        setDateTimeField();

        setupSpiner();

        mTtlHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
                setDateTimeField();
            }
        });
    }

    /*public void updateAntrian(String idPasien, String noRegistrasi, String noRm, String namaPemilik, String namaHewan, String jenisHewan, String signalemenTtl, String signalemenKelamin, String username, String statusAntrian, String fotoProfil) {
        RestApi updateService = ApiClient.getClient().create(RestApi.class);
        Call<DataPasien> call = updateService.updateAntrian(idPasien, noRegistrasi, noRm, namaPemilik, namaHewan, jenisHewan, signalemenTtl, signalemenKelamin, username, statusAntrian, fotoProfil);
        call.enqueue(new Callback<DataPasien>() {
            @Override
            public void onResponse(Call<DataPasien> call, Response<DataPasien> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(PasienUpdate.this, "Update submitted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PasienUpdate.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataPasien> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(PasienUpdate.this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void setupSpiner() {
        ArrayAdapter spinnerAdapterJenisHewan = ArrayAdapter.createFromResource(this,
                R.array.array_jenis_hewan, android.R.layout.simple_spinner_item);

        spinnerAdapterJenisHewan.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinnerJenisHewan.setAdapter(spinnerAdapterJenisHewan);

        spinnerJenisHewan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jenisHewan = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mTtlHewan.setText(dateFormatter.format(newDate.getTime()));
                signalemenTtl = dateFormatKirim.format(newDate.getTime());
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_input.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_simpan:
                progress = new ProgressDialog(PasienUpdate.this);
                progress.setCancelable(false);
                progress.setMessage("Loading...");
                progress.show();

                final Intent intent = getIntent();

                idPasien = intent.getStringExtra(PasienLihat.ID);
                noRegistrasi = mNoRegistrasi.getText().toString().trim();
                noRm = mNoRekamMedis.getText().toString().trim();
                namaPemilik = mNamaPemilik.getText().toString().trim();
                namaHewan = mNamaHewan.getText().toString().trim();
                /*jenisHewan = mJenisHewan.getText().toString().trim();*/
                username = mUsername.getText().toString().trim();
                statusAntrian = mStatAntrian.getText().toString().trim();
                /*String signalemenKelamin = mGender.getText().toString().trim();*/

/**/

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);

                if (jenisHewan.equals(JENIS_ANJING)) {
                    fotoProfil = "http://testrsh.xyz/uploads/profile/profil_dog.png";
                }
                if (jenisHewan.equals(JENIS_KUCING)) {
                    fotoProfil = "http://testrsh.xyz/uploads/profile/profil_cat.png";
                }
                if (jenisHewan.equals(JENIS_UNGGAS)) {
                    fotoProfil = "http://testrsh.xyz/uploads/profile/profil_bird.png";
                }
                if (jenisHewan.equals(JENIS_EKSOTIS)) {
                    fotoProfil = "http://testrsh.xyz/uploads/profile/profil_exotic.png";
                }

                signalemenKelamin = radioSexButton.getText().toString();

                Log.i("TEST", signalemenKelamin + signalemenTtl + jenisHewan + fotoProfil);

/*
                updateAntrian(idPasien, noRegistrasi, noRm, namaPemilik, namaHewan, jenisHewan, signalemenTtl, signalemenKelamin, username, statusAntrian, fotoProfil);
*/

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
