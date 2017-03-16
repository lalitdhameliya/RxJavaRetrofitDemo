package com.rxjavaretrofit.api;

import io.reactivex.Maybe;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Lalit on 28-Nov-16.
 */

public interface ApiService {

    String BASE_URL = "http://echo.jsontest.com/";

    /**
     * By defining may be of ResponseBody you can get json response.
     * if you want Gson to directly parse your data than Replace ResponseBody with your pojo/model class.
     * @return json string.
     */
    @GET("title/ipsum/content/blah")
    Maybe<ResponseBody> getRetroRxTest();
}
