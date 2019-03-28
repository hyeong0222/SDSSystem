package com.B17.sdssystem.authentication


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
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

    //msg is a list
    override fun showRegInfo(msg : Msg?) {

    }

    override fun showLoginInfo(login: Login?) {
        info {"LoginFragment "  + login?.useremail + " " + login?.userlastname + " " + login?.msg?.get(0) }
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

        btnLogin = v.findViewById(R.id.btnLogin )
        btnLogin.setOnClickListener {
           if (et_lgnEmail.text.isEmpty() || !et_lgnEmail.validEmail()) {
               activity?.longToast("Invalid Email Address")
           } else if (!et_logPW.validator().nonEmpty().minLength(6).check()) {
               activity?.longToast("invalid password")
           } else {
               authPresenter.loginUser(et_lgnEmail.text.toString(), et_logPW.text.toString())
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
