package com.natalia.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiTempo {
    @GET("https://api.hgbrasil.com/weather?woeid=455827")
    Call<ApiPojo> getInfTempo();
}
