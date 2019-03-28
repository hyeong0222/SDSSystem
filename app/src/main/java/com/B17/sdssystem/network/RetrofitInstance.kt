package com.B17.sdssystem.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    lateinit var retrofit: Retrofit
    val BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/"

    fun getRetrofitInstance() : Retrofit {
        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit
    }
}