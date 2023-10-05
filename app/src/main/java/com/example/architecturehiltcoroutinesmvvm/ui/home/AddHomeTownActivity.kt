package com.example.architecturehiltcoroutinesmvvm.ui.home

import android.app.sportapp.remote.APIConst
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.databinding.ActivityAddHomeTownBinding
import com.example.architecturehiltcoroutinesmvvm.remote.Status
import com.example.architecturehiltcoroutinesmvvm.utils.BaseActivity
import com.example.architecturehiltcoroutinesmvvm.utils.Constants
import com.example.architecturehiltcoroutinesmvvm.utils.GeneralFunctions
import com.example.architecturehiltcoroutinesmvvm.utils.PrefUtil
import com.google.gson.Gson

class AddHomeTownActivity : BaseActivity() {
    private val addHomeTownViewModel by viewModels<AddHomeTownViewModel>()
    private var userByIdAndAuthcodeResponse: UserByIdAndAuthcodeResponse.Data? = null
    private val mBinding by lazy { ActivityAddHomeTownBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            /*toolbar.apply {
                toolbar.tvTitle.text = getString(R.string.add_home_town)
                ivBack.setOnClickListener { finish() }
                tvCancel.setOnClickListener { finish() }
            }*/

          addHomeTownViewModel.userByIdAndAuthCodeResponse.observe(this@AddHomeTownActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            Toast.makeText(this@AddHomeTownActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                            Log.d("ResponseDataUserUpdate2","${it.data}")

                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
            }

            addHomeTownViewModel.updateHomeTownApiResponse.observe(this@AddHomeTownActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            Log.d("ResponseDataUserUpdate1","${it.data}")

                            Toast.makeText(this@AddHomeTownActivity, "${it.message}", Toast.LENGTH_SHORT).show()

                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
            }

         /*   intent.extras?.let {
                userByIdAndAuthcodeResponse = Gson().fromJson(
                    it.getString(Constants.EMPLOYMENT_LIST_RESPONSE),
                    UserByIdAndAuthcodeResponse.Data::class.java
                )
                etAddHomeTown.setText(userByIdAndAuthcodeResponse!!.vHomeTown)
                tvSave.text = getText(R.string.update)
            }*/

            mBinding.tvSave.setOnClickListener {
                if (GeneralFunctions.validEmptyTextInputLayout(
                        listOf(
                            Triple(
                                tilAddHomeTown,
                                etAddHomeTown,
                                getString(R.string.home_town_is_required)
                            )
                        )
                    )
                ) {
                    if (userByIdAndAuthcodeResponse != null) {
                        addHomeTownViewModel.homeTownProfileUpdate(
                            this@AddHomeTownActivity, hashMapOf(
                                Pair(APIConst.iUserId, prefUtil.getInt("158")),
                                Pair(APIConst.vHomeTown, etAddHomeTown.text.toString())
                            )
                        )

                    } else {
                        addHomeTownViewModel.homeTownProfileUpdate(
                            this@AddHomeTownActivity, hashMapOf(
                                Pair(APIConst.iUserId, prefUtil.getInt("158")),
                                Pair(APIConst.vHomeTown, etAddHomeTown.text.toString())
                            )
                        )
                    }
                }
            }
        }
    }
}
