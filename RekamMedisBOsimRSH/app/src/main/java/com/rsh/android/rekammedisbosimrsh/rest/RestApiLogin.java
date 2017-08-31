package com.rsh.android.rekammedisbosimrsh.rest;

import com.rsh.android.rekammedisbosimrsh.models.modeluser.ModelUser;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rezar on 17/05/2017.
 */

public interface RestApiLogin {
    @GET("index.php/user")
    Call<ModelUser> loadUser();
}
