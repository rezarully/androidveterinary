package com.rsh.android.rekammedisbosimrsh.rest;

import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.InputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRm;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRmPasien;
import com.rsh.android.rekammedisbosimrsh.models.modelinputrm.ModelInputRmSingle;
import com.rsh.android.rekammedisbosimrsh.models.Respond;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by rezar on 20/04/2017.
 */

public interface RestApiRekamMedis {

    @GET("index.php/inputrm")
    Call<ModelInputRm> loadListRekamMedis();

    @GET("index.php/inputrm/{id}")
    Call<ModelInputRmSingle> loadRm(@Path("id") String id);

    @GET("index.php/inputrm/pasienrm/{id}")
    Call<ModelInputRmPasien> loadListRekamMedisPasien(@Path("id") String id);

    @Multipart
    @POST("uploads/upload2.php")
    Call<Respond> uploadImageMultiple(@Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2,
                                      @Part MultipartBody.Part file3,
                                      @Part MultipartBody.Part file4);

    @POST("index.php/inputrm")
    @FormUrlEncoded
    Call<InputRm> savePost(@Field("idRegistrasi") String idRegistrasi,
                           @Field("namaPemilik") String namaPemilik,
                           @Field("namaHewan") String namaHewan,
                           @Field("namaTenagaMedis") String namaTenagaMedis,
                           @Field("namaMahasiswa") String namaMahasiswa,
                           @Field("anamnesis") String anamnesis,
                           @Field("keadaanUmum") String keadaanUmum,
                           @Field("frekNafas") String frekNafas,
                           @Field("frekPulsus") String frekPulsus,
                           @Field("temperaturTubuh") String temperaturTubuh,
                           @Field("kulitRambut") String kulitRambut,
                           @Field("selaputLendir") String selaputLendir,
                           @Field("kelenjarLimfe") String kelenjarLimfe,
                           @Field("pernafasan") String pernafasan,
                           @Field("peredaranDarah") String peredaranDarah,
                           @Field("pencernaan") String pencernaan,
                           @Field("kelaminPerkencingan") String kelaminPerkencingan,
                           @Field("syaraf") String syaraf,
                           @Field("anggotaGerak") String anggotaGerak,
                           @Field("beratBadan") String beratBadan,
                           @Field("lainAnamnesis") String lainAnamnesis,
                           @Field("namaPemeriksaan") String namaPemeriksaan,
                           @Field("ketLab") String ketLab,
                           @Field("diagnosis") String diagnosis,
                           @Field("prognosis") String prognosis,
                           @Field("namaObat") String namaObat,
                           @Field("jumlahResep") String jumlahResep,
                           @Field("satuanResep") String satuanResep,
                           @Field("perintahPembuatan") String perintahPembuatan,
                           @Field("petunjukPenggunaan") String petunjukPenggunaan,
                           @Field("namaTindakan") String namaTindakan,
                           @Field("qtyTindakan") String qtyTindakan,
                           @Field("ketTindakan") String ketTindakan,
                           @Field("foto1") String foto1,
                           @Field("foto2") String foto2,
                           @Field("foto3") String foto3,
                           @Field("foto4") String foto4);

    @DELETE("index.php/inputrm/{id}")
    Call<InputRm> deleteRekamMedis(@Path("id") String id);
}
