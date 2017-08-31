package com.rsh.android.rekammedisbosimrsh.pasien;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;

import java.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.R;
import com.rsh.android.rekammedisbosimrsh.data.ApiClient;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.rest.RestApi;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienTambah extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText mNoRegistrasi;
    private EditText mNoRekamMedis;
    private EditText mNamaPemilik;
    private EditText mNamaHewan;
    private EditText mTtlHewan;
    private EditText mUsername;
    private EditText mStatAntrian;
    private EditText mGender;

    private Calendar calendar;
    private DatePicker datePicker;
    private int day, month, year;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter, dateFormatKirim;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    String noRegistrasi;
    String noRm;
    String namaPemilik;
    String namaHewan;
    String jenisHewan;
    String signalemenTtl;
    String username;
    String statusAntrian;
    String signalemenKelamin;
    String fotoProfil;

    private static final String JENIS_ANJING = "Anjing";
    private static final String JENIS_KUCING = "Kucing";
    private static final String JENIS_UNGGAS = "Unggas";
    private static final String JENIS_EKSOTIS = "Eksotis";

    private Spinner spinnerJenisHewan;

    private String fotoPasien;

    private Button buttonUpload;
    private ImageView imageView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private static final int REQUEST_CAMERA = 2;
    private static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA};
//    String mCurrentPhotoPath;
//    private Uri filePath;
//    private StorageReference storageReference;
//
//    private static final int PICK_IMAGE_REQUEST = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.rsh.android.rekammedisbosimrsh.R.layout.activity_pasien_tambah);

        verifyStoragePermissions(PasienTambah.this);
        verifyCameraPermissions(PasienTambah.this);

        mNoRegistrasi = (EditText) findViewById(R.id.edit_pasien_no_registrasi);
        mNoRekamMedis = (EditText) findViewById(R.id.edit_pasien_no_rm);
        mNamaPemilik = (EditText) findViewById(R.id.edit_pasien_nama_pemilik);
        mNamaHewan = (EditText) findViewById(R.id.edit_pasien_nama_hewan);
        mTtlHewan = (EditText) findViewById(R.id.edit_pasien_ttl);

        mTtlHewan.setInputType(InputType.TYPE_NULL);

        mUsername = (EditText) findViewById(R.id.edit_pasien_username);
        mStatAntrian = (EditText) findViewById(R.id.edit_pasien_status_antrian);
//        mGender = (EditText) findViewById(R.id.edit_pasien_gender);
        radioSexGroup = (RadioGroup) findViewById(R.id.radio_sex_group);

        spinnerJenisHewan = (Spinner) findViewById(R.id.spinner_jenis_hewan);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatKirim = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        setDateTimeField();

        setupSpiner();

//        buttonUpload = (Button) findViewById(R.id.btn_upload_image);
        /*imageView = (ImageView) findViewById(R.i);*/
//        storageReference = FirebaseStorage.getInstance().getReference();


        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

            }
        });

        mTtlHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
                setDateTimeField();
            }
        });
    }

    public void sendPost(String noRegistrasi, String noRm, String namaPemilik, String namaHewan, String jenisHewan, String signalemenTtl, String signalemenKelamin, String username, String statusAntrian, String fotoProfil) {
        RestApi apiService = ApiClient.getClient().create(RestApi.class);
        Call<DataPasien> call = apiService.savePost(noRegistrasi, noRm, namaPemilik, namaHewan, jenisHewan, signalemenTtl, signalemenKelamin, username, statusAntrian, fotoProfil);
        call.enqueue(new Callback<DataPasien>() {
            @Override
            public void onResponse(Call<DataPasien> call, Response<DataPasien> response) {
                progress.dismiss();
                Toast.makeText(PasienTambah.this, "Post submitted", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<DataPasien> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(PasienTambah.this, "Unable to submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public static void verifyCameraPermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_CAMERA,
                    REQUEST_CAMERA
            );

        }
    }

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
                progress = new ProgressDialog(PasienTambah.this);
                progress.setCancelable(false);
                progress.setMessage("Loading...");
                progress.show();

                noRegistrasi = mNoRegistrasi.getText().toString().trim();
                noRm = mNoRekamMedis.getText().toString().trim();
                namaPemilik = mNamaPemilik.getText().toString().trim();
                namaHewan = mNamaHewan.getText().toString().trim();
                /*signalemenTtl = mTtlHewan.getText().toString().trim();*/
                username = mUsername.getText().toString().trim();
                statusAntrian = mStatAntrian.getText().toString().trim();

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                radioSexButton.setChecked(true);

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

                Log.i("jenis hewan", jenisHewan);
                Log.i("Foto Profil", fotoProfil);


                signalemenKelamin = radioSexButton.getText().toString();

                sendPost(noRegistrasi, noRm, namaPemilik, namaHewan, jenisHewan, signalemenTtl, signalemenKelamin, username, statusAntrian, fotoProfil);


//                if(!TextUtils.isEmpty(noRegistrasi)) {
//                    Toast.makeText(PasienTambah.this, "No Registrasi belum dimasukkan", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    sendPost(noRegistrasi, noRm, namaPemilik, namaHewan,jenisHewan,signalemenTtl, signalemenKelamin, username,statusAntrian );
//                }
                //                saveFile(bitmapRotate,file);

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //this method will upload the file
//    private void uploadFile() {
//        //if there is a file to upload
//        if (filePath != null) {
//            //displaying a progress dialog while upload is going on
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading");
//            progressDialog.show();
//
//            StorageReference riversRef = storageReference.child("images/pic.jpg");
//            riversRef.putFile(filePath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            //if the upload is successfull
//                            //hiding the progress dialog
//                            progressDialog.dismiss();
//
//                            //and displaying a success toast
//                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            //if the upload is not successfull
//                            //hiding the progress dialog
//                            progressDialog.dismiss();
//
//                            //and displaying error message
//                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            //calculating progress percentage
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//
//                            //displaying percentage in progress dialog
//                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
//                        }
//                    });
//        }
//        //if there is not any file
//        else {
//            //you can display an error toast
//        }
//    }

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
}
