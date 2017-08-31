package com.rsh.android.rekammedisbosimrsh.rest;

import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelLab;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelObat;
import com.rsh.android.rekammedisbosimrsh.models.modelspinner.ModelTindakan;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rezar on 26/05/2017.
 */

public interface RestApiSpinner {
    @GET("index.php/obat")
    Call<ModelObat> loadListObat();

    @GET("index.php/tindakan")
    Call<ModelTindakan> loadListTindakan();

    @GET("index.php/lab")
    Call<ModelLab> loadListLab();
}
