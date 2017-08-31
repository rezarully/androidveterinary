package com.rsh.android.rekammedisbosimrsh.rest;

import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSingle;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.ModelPasienSpinner;
import com.rsh.android.rekammedisbosimrsh.models.Respond;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by rezar on 30/03/2017.
 */

public interface RestApi {
    @GET("index.php/data_pasien")
    Call<ModelPasien> loadListPasien();

    @GET("index.php/pasien_antrian")
    Call<ModelPasien> loadListAntrian();

    @GET("index.php/data_pasien")
    Call<ModelPasienSpinner> loadListSpinner();

    @GET("index.php/data_pasien/{id}")
    Call<ModelPasienSingle> loadPasien(@Path("id") String id);

    @POST("index.php/data_pasien")
    @FormUrlEncoded
    Call<DataPasien> savePost(@Field("noRegistrasi") String noRegistrasi,
                              @Field("noRm") String noRm,
                              @Field("namaPemilik") String namaPemilik,
                              @Field("namaHewan") String namaHewan,
                              @Field("jenisHewan") String jenisHewan,
                              @Field("signalemenTtl") String signalemenTtl,
                              @Field("signalemenKelamin") String signalemenKelamin,
                              @Field("username") String username,
                              @Field("statusAntrian") String statusAntrian,
                              @Field("fotoProfil") String fotoProfil);


    @PUT("index.php/data_pasien")
    @FormUrlEncoded
    Call<DataPasien> updatePost(@Field("id") String id,
                                /*@Field("noRegistrasi") String noRegistrasi,
                                @Field("noRm") String noRm,
                                @Field("namaPemilik") String namaPemilik,
                                @Field("namaHewan") String namaHewan,
                                @Field("jenisHewan") String jenisHewan,
                                @Field("signalemenTtl") String signalemenTtl,
                                @Field("signalemenKelamin") String signalemenKelamin,
                                @Field("username") String username,*/
                                @Field("statusAntrian") String statusAntrian/*,
                                @Field("fotoProfil") String fotoProfil*/);

    @DELETE("index.php/data_pasien/{id}")
    Call<DataPasien> deletePasien(@Path("id") String id);

    @Multipart
    @POST("uploads/upload.php")
    Call<Respond> uploadImage(@Part MultipartBody.Part file);
}
