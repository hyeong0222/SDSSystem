package com.B17.sdssystem.authentication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.B17.sdssystem.R
import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validNumber
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.fragment_registration.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast


/**
 * A simple [Fragment] subclass.
 *
 */
class RegistrationFragment : Fragment(), AnkoLogger, AuthContract.view {

    lateinit var btn_Register : Button
    lateinit var tv_gotAccount : TextView

    private val authPresenter = AuthPresenter(this)


    override fun showRegInfo(msg: Msg) {
       info {"Registration Fragment --> " + msg.msg.get(0) }
        activity?.longToast(msg.msg.get(0))
        //navigate to project list activity
    }

    override fun showLoginInfo(login: Login) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var v =  inflater.inflate(R.layout.fragment_registration, container, false)
        btn_Register = v.findViewById(R.id.btn_Register)
        btn_Register.setOnClickListener {

            if(et_fname.text.isEmpty() || et_lname.text.isEmpty()){
               activity?.longToast("Names cannot be empty")
            } else if(et_email.text.isEmpty() || !et_email.validEmail()){
                activity?.longToast("Please enter a valid Email Address")
            } else if(!et_phone.validNumber() || et_phone.text.isEmpty()){
                activity?.longToast("Invalid phone Number")
            } else if(!et_password.validator().nonEmpty().minLength(6).check()){
               activity?.longToast("invalid password")
            }else if(et_comSize.text.isEmpty()){
                activity?.longToast("Company Size Field CANNOT be empty")
            }
            else if(rdn_manager.isChecked){
                authPresenter.registerUser(et_fname.text.toString(),et_lname.text.toString(),et_email.text.toString(),
                    et_phone.text.toString(),et_password.text.toString(),et_comSize.text.toString(),"1")
            }else if(rdn_developer.isChecked){
                authPresenter.registerUser(et_fname.text.toString(),et_lname.text.toString(),et_email.text.toString(),
                    et_phone.text.toString(),et_password.text.toString(),et_comSize.text.toString(),"0")
            }else{
                //
                //  longToast("CHOOSE A ROLE")
            }
        }



        tv_gotAccount = v.findViewById(R.id.tv_gotAccount)
        tv_gotAccount.setOnClickListener {
            var fg : Fragment = LoginFragment()
            //  Fragment fg = new LoginFragment();
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.auth_container, fg)?.addToBackStack(null)
                ?.commit();
        }
        return v
    }
}