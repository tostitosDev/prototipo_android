package com.tesis.arduinoconnection;

/**
 * Created by Lestat on 17-04-2018.
 */
import com.tesis.arduinoconnection.models.Fingerprint;
import com.tesis.arduinoconnection.models.Mark;
import com.tesis.arduinoconnection.models.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LindiAPI {

    @POST("marks")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Result> createMark(@Body Mark m);

    @POST("fingerprints")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Result> createFingerPrint(@Body Fingerprint f);
}
