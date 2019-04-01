package com.B17.sdssystem.authentication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Patterns
import android.util.Range
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
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validNumber
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*
import org.jetbrains.anko.*


/**
 * A simple [Fragment] subclass.
 *
 */
class RegistrationFragment : Fragment(), AnkoLogger, AuthContract.view {

    lateinit var btn_Register : Button
    lateinit var tv_gotAccount : TextView
    lateinit var awesomeValidation: AwesomeValidation
    lateinit var fagcontainer : ViewGroup

    private val authPresenter = AuthPresenter(this)


    override fun showRegInfo(msg: Msg?) {
        activity?.longToast(msg!!.msg.get(0))

        var fg : Fragment = LoginFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(fagcontainer.id, fg)!!.addToBackStack(null)
            .commit();
    }

    override fun showLoginInfo(login: Login?) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                 fagcontainer = container!!
        // Inflate the layout for this fragment
       var v =  inflater.inflate(R.layout.fragment_registration, container, false)
        btn_Register = v.findViewById(R.id.btn_Register)
        btn_Register.setOnClickListener {

            awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
            awesomeValidation.addValidation(et_email, Patterns.EMAIL_ADDRESS, "Invalid Email Id")
            awesomeValidation.addValidation( et_fname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Invalid Name");
            awesomeValidation.addValidation( et_lname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Invalid Name");
            awesomeValidation.addValidation(et_phone, Patterns.PHONE, "Invalid Phone Number");
            awesomeValidation.addValidation(et_password,".{8,}","Invalid Password")
            awesomeValidation.addValidation(et_comSize, com.google.common.collect.Range.closed(0,1000000), "Invalid Input")


            if (awesomeValidation.validate() ){
                info{"checking if it got here"}
                if(rdn_manager.isChecked){
                    authPresenter.registerUser(et_fname.editText?.text.toString(),et_lname.editText?.text.toString(),
                        et_email.editText?.text.toString(), et_phone.editText?.text.toString(),
                        et_password.editText?.text.toString(),et_comSize.editText?.text.toString(),"1")
                }else if(rdn_developer.isChecked){
                    authPresenter.registerUser(et_fname.editText?.text.toString(),et_lname.editText?.text.toString(),
                        et_email.editText?.text.toString(), et_phone.editText?.text.toString(),
                        et_password.editText?.text.toString(),"0","0")
                }else{
                    activity?.longToast("CHOOSE A ROLE")
                }
            }
        }


        tv_gotAccount = v.findViewById(R.id.tv_gotAccount)
        tv_gotAccount.setOnClickListener {
            var fg : Fragment = LoginFragment()
            //  Fragment fg = new LoginFragment();
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(fagcontainer.id, fg)?.addToBackStack(null)
                ?.commit();
        }
        return v
    }
}