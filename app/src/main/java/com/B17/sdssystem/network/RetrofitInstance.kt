package com.B17.sdssystem.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private lateinit var retrofit : Retrofit


        val BASEURI = "http://rjtmobile.com/aamir/pms/android-app/"

        fun getRetrofitInstance() : Retrofit {







            retrofit = Retrofit.Builder()
                .baseUrl(BASEURI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()




            return retrofit




        }
    }
}