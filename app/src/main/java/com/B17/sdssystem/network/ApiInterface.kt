package com.B17.sdssystem.network


import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

  //  @FormUrlEncoded
    @GET("pms_reg.php")
     fun submitReg(
      @Query("first_name") fname: String,
      @Query("last_name") lname : String,
      @Query("email") email: String,
      @Query("mobile") mobile: String,
      @Query("password") password: String,
      @Query("company_size") companySize: String,
      @Query("your_role") role: String
    ): Call<Msg>


    @GET("pms_login.php")
    fun submitLogin( @Query("email") email: String,
                     @Query("password") password: String) : Call<Login>

}