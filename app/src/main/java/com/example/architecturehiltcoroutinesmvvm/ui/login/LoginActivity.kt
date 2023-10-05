package com.example.architecturehiltcoroutinesmvvm.ui.login

import android.app.sportapp.remote.APIConst
import android.content.Intent
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.databinding.ActivityLoginBinding
import com.example.architecturehiltcoroutinesmvvm.ui.home.HomeActivity
import com.example.architecturehiltcoroutinesmvvm.utils.BaseActivity
import com.example.architecturehiltcoroutinesmvvm.utils.GeneralFunctions

class LoginActivity : BaseActivity() {

    private val mBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

            loginViewModel.loginAPIResponse.observe(this@LoginActivity) { it ->
                handleLoader(it) { successResponse ->
                    (successResponse.data as LoginResponse).apply {
                        Log.d("UserIdDisplay","${it.data}")
                        if (status.equals(APIConst.success, true)) {
                            if (data.eTwoStepVerification == "Yes") {

                            } else if (data.eInterest.equals("No", true)) {

                                startActivity(Intent(this@LoginActivity, LoginActivity::class.java).apply {
                                    finish()
                                })

                            } else if (data.eInterest.equals("Yes", true)) {
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                                    finish()
                                })
                            }


                        }


                      /*  if (status.equals(APIConst.success, true)) {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            })
                        }*/

                            /* Toast.makeText(this@LoginActivity, "${message}", Toast.LENGTH_SHORT).show()
                             Log.d("Response","${message}")*/
                      /*  if (data.eTwoStepVerification == "Yes") {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            })
                        }*/

                    }
                }
            }

            tvLogin.setOnClickListener {
                if (validate()) {
                    val params = hashMapOf<String, Any>()

                    if (tnEmailPassword.visibility == View.VISIBLE){
                        Log.d("Response","Email Data Not Empty")
                        params[APIConst.vEmail] = etEmailPassword.text.toString()

                    }
                    if (tnPassword.visibility == View.VISIBLE) {
                        params[APIConst.vPassword] = etPassword.text.toString()

                    }
                    loginViewModel.callLoginAPI(this@LoginActivity, params)

                }


            }
        }
    }

    private fun validate(): Boolean {
        mBinding.apply {

            if (tnEmailPassword.visibility == View.VISIBLE) {
                if (!GeneralFunctions.validateEmail(this@LoginActivity, tnEmailPassword, etEmailPassword))
                    return false
                else
                    GeneralFunctions.hideTextInputLayoutError(tnEmailPassword)
            }

            if (tnPassword.visibility == View.VISIBLE) {
                if (etPassword.text.toString().isEmpty())
                    return GeneralFunctions.showTextInputLayoutError(tnPassword, getString(R.string.number_required))
                else
                    GeneralFunctions.hideTextInputLayoutError(tnPassword)
            }

            if (!GeneralFunctions.validatePassword(this@LoginActivity, tnPassword, etPassword))
                return false

        }
        return true
    }


}