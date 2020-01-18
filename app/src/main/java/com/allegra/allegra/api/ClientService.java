package com.allegra.allegra.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface ClientService {

    @GET("v1/music/artists/{id_artist}/albums/{id_album}/tracks/{id_track}/lyrics")
    Call<ResponseBody> lyrics(@Path("id_artist") int id_artist,
                              @Path("id_album") int id_album,
                              @Path("id_track") int id_track,
                            @Query("apikey") String api_key);

    @GET("v1/music")
    Call<ResponseBody> search(@Query("q") String q,
                              @Query("apikey") String api_key);
}
