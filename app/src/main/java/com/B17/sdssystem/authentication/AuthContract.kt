package com.B17.sdssystem.authentication

import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
//import java.util.List

interface AuthContract {

    interface view {
        fun showRegInfo(msg : Msg?)
        fun showLoginInfo(login : Login?)

    }

    interface presenter{
        fun registerUser(et_fname : String, et_lname : String, et_email : String, et_phone : String, et_password : String,
                         et_comSize : String, role : String)
        fun loginUser(et_email : String, et_password : String )
    }

}