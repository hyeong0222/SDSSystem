package com.B17.sdssystem.authentication

import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthPresenter(var authView : AuthContract.view) : AnkoLogger, AuthContract.presenter {

    override fun loginUser(et_email: String, et_password: String) {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var userLogin = apiInterface.submitLogin(et_email,et_password)

        userLogin.enqueue(object: Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                //  info {"smiths ----->" + response?.body()?.regList?.get(0)?.msg }
                     authView.showLoginInfo(response.body())
            }

            override fun onFailure(call: Call<Login>?, t: Throwable?) {

                info { t?.message }
            }
        })
    }

    override fun registerUser(
        et_fname: String,
        et_lname: String,
        et_email: String,
        et_phone: String,
        et_password: String,
        et_comSize: String,
        role: String
    ) {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var userReg = apiInterface.submitReg(et_fname,et_lname,et_email,et_phone,et_password,et_comSize, role)

        info { et_fname + et_lname + " " + et_email + " " + et_password + " " +   et_phone + " " + role + " " + et_comSize}

         userReg.enqueue(object: Callback<Msg> {
             override fun onResponse(call: Call<Msg>, response: Response<Msg>) {
               //  info {"smiths ----->" + response?.body()?.regList?.get(0)?.msg }
                 authView.showRegInfo(response.body())
             }
             override fun onFailure(call: Call<Msg>?, t: Throwable?) {
                info { t?.message }
             }
         })

    }


}


