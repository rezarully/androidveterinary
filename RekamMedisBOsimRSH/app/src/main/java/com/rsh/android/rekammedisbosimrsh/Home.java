package com.rsh.android.rekammedisbosimrsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rsh.android.rekammedisbosimrsh.inappasien.InapPasienActivity;
import com.rsh.android.rekammedisbosimrsh.inaprm.InapRekamMedis;
import com.rsh.android.rekammedisbosimrsh.pasien.Pasien;
import com.rsh.android.rekammedisbosimrsh.rekammedis.RekamMedis;

public class Home extends AppCompatActivity {

    SessionManager session;

    private Button btnDataPasien, btnPasienInap, btnRekamMedis, btnRekamMedisInap,btnLogout;

    private TextView textLoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(getApplicationContext());

/*
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
*/

        session.checkLogin();

        btnDataPasien = (Button) findViewById(R.id.btn_data_pasien);
        btnPasienInap = (Button) findViewById(R.id.btn_pasien_inap);
        btnRekamMedis = (Button) findViewById(R.id.btn_rekam_medis);
        btnRekamMedisInap = (Button) findViewById(R.id.btn_rekam_medis_inap);
        btnLogout = (Button) findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });

        btnDataPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDataPasien = new Intent(Home.this, Pasien.class);
                startActivity(intentDataPasien);
            }
        });

        btnPasienInap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPasienInap = new Intent(Home.this, InapPasienActivity.class);
                startActivity(intentPasienInap);
            }
        });

        btnRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRekamMedis = new Intent(Home.this, RekamMedis.class);
                startActivity(intentRekamMedis);
            }
        });

        btnRekamMedisInap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRekamMedisInap = new Intent(Home.this, InapRekamMedis.class);
                startActivity(intentRekamMedisInap);
            }
        });
    }
}
