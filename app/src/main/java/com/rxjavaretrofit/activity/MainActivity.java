package com.rxjavaretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.rxjavaretrofit.R;
import com.rxjavaretrofit.api.ApiService;

import java.io.IOException;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainActivity mContext;
    private Retrofit retrofit;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * This method is used to initialize the views.
     */
    private void init() {
        mContext = MainActivity.this;

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build();

        //This will create object of ApiService Interface, So that you can call all the methods of the ApiService Interface.
        service = retrofit.create(ApiService.class);

        /**
         * Getting data with the help of lambda expression.
         */
        service.getRetroRxTest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> Log.d(TAG, "JSON RESPONSE USING LAMBDA: " + responseBody.string()));

        /**
         * Getting data using normal anonymous class.
         */
        service.getRetroRxTest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(ResponseBody value) {
                        String json = "";
                        try {
                            json = value.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ((TextView) findViewById(R.id.act_main_tv_json_response)).setText(json);
                        Log.d(TAG, "JSON RESPONSE USING NORMAL METHOD: " + json);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
