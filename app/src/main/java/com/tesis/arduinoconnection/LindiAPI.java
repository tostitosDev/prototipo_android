package com.tesis.arduinoconnection;

/**
 * Created by Lestat on 17-04-2018.
 */
import com.tesis.arduinoconnection.models.Fingerprint;
import com.tesis.arduinoconnection.models.Mark;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface LindiAPI {

    @POST("marks")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> createMark(@Body Mark m);

    @POST("fingerprints")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> createFingerPrint(@Body Fingerprint f);
}
