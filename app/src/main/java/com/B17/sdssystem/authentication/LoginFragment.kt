package com.B17.sdssystem.authentication


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.B17.sdssystem.BuildConfig
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
import com.B17.sdssystem.developer.DeveloperActivity
import com.B17.sdssystem.manager.ManagerActivity
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.fragment_login.*
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validNumber
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import org.jetbrains.anko.*


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment(), AuthContract.view , AnkoLogger {


    private val authPresenter = AuthPresenter(this)
    lateinit var btnLogin : Button
    lateinit var tv_createAccount : TextView
    lateinit var awesomeValidation: AwesomeValidation

    //msg is a list
    override fun showRegInfo(msg : Msg?) {

    }

    override fun showLoginInfo(login: Login?) {
        info {"LoginFragment "  + login?.useremail + " " + login?.userlastname + " " + login?.msg?.get(0) }
        val editor = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE).edit()
        editor.putString("userid", login?.userid).apply()
        editor.putString("appapikey", login?.appapikey).apply()
        editor.putString("userrole", login?.userrole).apply()


        if (BuildConfig.FLAVOR.equals("manager")) {
            startActivity(context?.intentFor<ManagerActivity>()?.singleTop())
        } else {
            startActivity(context?.intentFor<DeveloperActivity>()?.singleTop())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_login, container, false)


     //   awesomeValidation.addValidation(et_logPW,)

        btnLogin = v.findViewById(R.id.btnLogin )
        btnLogin.setOnClickListener {

            awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
            awesomeValidation.addValidation(et_lgnEmail, Patterns.EMAIL_ADDRESS, "Invalid Email Id")



            if (awesomeValidation.validate()){
                authPresenter.loginUser(et_lgnEmail.toString(), et_logPW.toString())
            }



       }

        tv_createAccount = v.findViewById(R.id.tv_createAccount)

        tv_createAccount.setOnClickListener {
            var fg: Fragment = RegistrationFragment()
            //  Fragment fg = new LoginFragment();
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.auth_container, fg)?.addToBackStack(null)
                ?.commit();
        }
        return v
    }

}
