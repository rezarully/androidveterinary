package com.rsh.android.rekammedisbosimrsh.rest;

import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.InputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInap;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInapPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrminap.ModelInputRmInapSingle;
import com.rsh.android.rekammedisbosimrsh.models.modelpasien.DataPasien;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by rezar on 02/05/2017.
 */

public interface RestApiRekamMedisInap {
    @GET("index.php/inputrminap")
    Call<ModelInputRmInap> loadListRekamMedis();

    @GET("index.php/inap_status")
    Call<ModelInputRmInap> loadListInap();

    @PUT("index.php/inap_status")
    @FormUrlEncoded
    Call<InputRmInap> updatePost(@Field("idRegistrasi") String idRegistrasi,
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

    @GET("index.php/inputrminap/{id}")
    Call<ModelInputRmInapSingle> loadRmInap(@Path("id") String id);

    @GET("index.php/inputrminap/pasienrminap/{id}")
    Call<ModelInputRmInapPasien> loadListRekamMedisPasienInap(@Path("id") String id);

    @DELETE("index.php/inputrminap/{id}")
    Call<InputRmInap> deleteRekamMedisInap(@Path("id") String id);

    @POST("index.php/inputrminap")
    @FormUrlEncoded
    Call<InputRmInap> savePost(@Field("idRegistrasi") String idRegistrasi,
                               @Field("namaPemilik") String namaPemilik,
                               @Field("namaHewan") String namaHewan,
                               @Field("diagnosis") String diagnosis,
                               @Field("beratBadan") String beratBadan,
                               @Field("namaMahasiswa") String namaMahasiswa,
                               @Field("semesterMahasiswa") String semesterMahasiswa,
                               @Field("namaObat") String namaObat,
                               @Field("jumlahResep") String jumlahResep,
                               @Field("satuanResep") String satuanResep,
                               @Field("perintahPembuatan") String perintahPembuatan,
                               @Field("petunjukPenggunaan") String petunjukPenggunaan,
                               @Field("pssm") String pssm,
                               @Field("pengobatan") String pengobatan,
                               @Field("ketPengobatan") String ketPengobatan,
                               @Field("statusInap") String statusInap,
                               @Field("foto1") String foto1,
                               @Field("foto2") String foto2,
                               @Field("foto3") String foto3,
                               @Field("foto4") String foto4);

}
