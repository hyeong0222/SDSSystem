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
        info{"checking login details ----> " + login?.userid}

       if(login?.userid ==null){
           activity!!.longToast("Invalid Login Details")
       }else if (BuildConfig.FLAVOR.equals("manager")) {
        startActivity(context?.intentFor<ManagerActivity>()?.singleTop())
            } else {
        startActivity(context?.intentFor<DeveloperActivity>()?.singleTop())
            }


    }

    override fun onResume() {
        super.onResume()
        activity!!.title = "LOGIN"
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
             var v = inflater.inflate(R.layout.fragment_login, container, false)
            //   awesomeValidation.addValidation(et_logPW,)

            btnLogin = v.findViewById(R.id.btnLogin )
            btnLogin.setOnClickListener {

                awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
                awesomeValidation.addValidation(et_lgnEmail, Patterns.EMAIL_ADDRESS, "Invalid Email Id")
                awesomeValidation.addValidation(et_logPW,".{8,}","Invalid Password")

                if (awesomeValidation.validate()){
                    info { "Checking user login details " + et_lgnEmail.toString() + " " + et_logPW.toString() }
                    authPresenter.loginUser(et_lgnEmail.editText?.text.toString(), et_logPW.editText?.text.toString())
                }
            }

        var button : Button = v.findViewById(R.id.button)

        button.setOnClickListener {
            textView21.setText(25/0)
        }

        tv_createAccount = v.findViewById(R.id.tv_createAccount)
        tv_createAccount.setOnClickListener {

            var fg: Fragment = RegistrationFragment()
            //  Fragment fg = new LoginFragment();

            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(container!!.id, fg)?.addToBackStack(null)
                ?.commit();
        }
        return v
    }

}
