package com.nyinyi.nw.sfc.network;

import com.nyinyi.nw.sfc.network.response.GetNewsResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by User on 12/3/2017.
 */

public interface MMNewsAPI {

    /**Form data nl htate py ya lo @FormUrlEncoded ko use tr*/
    @FormUrlEncoded
    @POST("v1/getMMNews.php")
    Call<GetNewsResponse> loadMMNews(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken
    );


}
