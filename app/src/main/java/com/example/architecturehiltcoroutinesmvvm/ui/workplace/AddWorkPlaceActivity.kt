/*
package com.example.architecturehiltcoroutinesmvvm.ui.workplace

import android.app.Activity
import android.app.sportapp.remote.APIConst
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.utils.BaseActivity
import com.example.architecturehiltcoroutinesmvvm.utils.GeneralFunctions
import com.google.gson.Gson

class AddWorkPlaceActivity  : BaseActivity() {

    private val mBinding by lazy { ActivityAddWorkplaceBinding.inflate(layoutInflater) }
    private val addWorkplaceViewModel by viewModels<AddWorkPlaceViewModel>()
    private var updateEmployment: AllWorkPlaceDataResponse.Data? = null
    private var currentlyWorkingValue = "No"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            toolbar.apply {
                tvTitle.text = getString(R.string.add_a_workplace)
                ivBack.setOnClickListener { finish() }
                tvCancel.setOnClickListener { finish() }
            }

            addWorkplaceViewModel.updateWorkPlaceAPIResponse.observe(this@AddWorkPlaceActivity) {
                handleLoader(it) { successResponse ->
                    Log.d("ResponseDataUserUpdate", "${successResponse.data}")
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                      //  showToast(message)
                    }
                }
            }

            addWorkplaceViewModel.insertPlayerAPIResponse.observe(this@AddWorkPlaceActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as AddWorkPlaceResponse).apply {
                        if (status.equals(APIConst.success, true)) {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                       // showToast(message)
                    }
                }
            }

            cbxICurrentlyWorkHere.setOnCheckedChangeListener { compoundButton, _ ->
                if (compoundButton!!.isPressed) {
                    printErrorLog(compoundButton.isChecked.toString())
                    compoundButton.isChecked
                    if (compoundButton.isChecked) {
                        clTo.visibility = View.GONE
                        currentlyWorkingValue = "Yes"

                    } else {
                        clTo.visibility = View.VISIBLE
                        currentlyWorkingValue = "No"

                    }
                }
            }

            mBinding.clFrom.setOnClickListener {
                GeneralFunctions.getDate(this@AddWorkPlaceActivity, {
                    tvFromDate.text = GeneralFunctions.changeDateFormatStr(it, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)
                })
            }

            clTo.setOnClickListener {
                GeneralFunctions.getDate(this@AddWorkPlaceActivity, {
                    if (!it.isNullOrEmpty()) {
                        tvToDate.text = GeneralFunctions.changeDateFormatStr(it, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)
                    }
                })
            }

            intent.extras?.let {
                updateEmployment = Gson().fromJson(it.getString(Constants.EMPLOYMENT_LIST_RESPONSE), AllWorkPlaceDataResponse.Data::class.java)
                val fromDateList = updateEmployment!!.vStartDay + "-" + updateEmployment!!.vStartMonth + "-" + updateEmployment!!.vStartYear
                val toDateList = updateEmployment!!.vEndDay.toString() + "-" + updateEmployment!!.vEndMonth + "-" + updateEmployment!!.vEndYear
                etCompanyName.setText(updateEmployment!!.vCompany)
                etPosition.setText(updateEmployment!!.vPosition)
                etCityTown.setText(updateEmployment!!.vCity)
                etDescription.setText(updateEmployment!!.tDescription)
                tvFromDate.text = GeneralFunctions.changeDateFormatStr(fromDateList, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)
                tvSave.text = getString(R.string.update)
                if (updateEmployment?.eCurrentlyWorking.equals("No") && updateEmployment?.vEndDay == null) {
                    clTo.visibility = View.GONE
                    cbxICurrentlyWorkHere.isChecked = true
                } else {
                    cbxICurrentlyWorkHere.isChecked = false
                    clTo.visibility = View.VISIBLE
                    tvToDate.text = GeneralFunctions.changeDateFormatStr(toDateList, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)
                }
            }

            tvSave.setOnClickListener {
                val fromDateList: List<String> = tvFromDate.text.split("/")
                val toDateList: List<String> = tvToDate.text.split("/")
                if (validate()) {
                    val params = hashMapOf<String, Any>()
                    if (updateEmployment != null) {
                        params[APIConst.vCompany] = etCompanyName.text.toString()
                        params[APIConst.vPosition] = etPosition.text.toString()
                        params[APIConst.vCity] = etCityTown.text.toString()
                        params[APIConst.tDescription] = etDescription.text.toString()
                        params[APIConst.vStartMonth] = fromDateList[0]
                        params[APIConst.vStartDay] = fromDateList[1]
                        params[APIConst.vStartYear] = fromDateList[2]
                        params[APIConst.vEndMonth] = if (clTo.visibility == View.GONE) "" else toDateList[0]
                        params[APIConst.vEndDay] = if (clTo.visibility == View.GONE) "" else toDateList[1]
                        params[APIConst.vEndYear] = if (clTo.visibility == View.GONE) "" else toDateList[2]
                        params[APIConst.iUserId] = prefUtil.getInt(PrefUtil.iUserId)
                        params[APIConst.eCurrentlyWorking] = currentlyWorkingValue
                        params[APIConst.iWorkplaceId] = updateEmployment!!.iWorkplaceId
                        addWorkplaceViewModel.updateWorkPlaceApi(this@AddWorkPlaceActivity, params)
                    } else {
                        params[APIConst.vCompany] = etCompanyName.text.toString()
                        params[APIConst.vPosition] = etPosition.text.toString()
                        params[APIConst.vCity] = etCityTown.text.toString()
                        params[APIConst.tDescription] = etDescription.text.toString()
                        params[APIConst.vStartMonth] = fromDateList[0]
                        params[APIConst.vStartDay] = fromDateList[1]
                        params[APIConst.vStartYear] = fromDateList[2]
                        params[APIConst.vEndMonth] = if (clTo.visibility == View.GONE) "" else toDateList[0]
                        params[APIConst.vEndDay] = if (clTo.visibility == View.GONE) "" else toDateList[1]
                        params[APIConst.vEndYear] = if (clTo.visibility == View.GONE) "" else toDateList[2]
                        params[APIConst.iUserId] = prefUtil.getInt(PrefUtil.iUserId)
                        params[APIConst.eCurrentlyWorking] = currentlyWorkingValue
                        addWorkplaceViewModel.callCreateWorkPlaceAPI(this@AddWorkPlaceActivity, params)
                    }
                }
            }

        }
    }

    private fun validate(): Boolean {
        mBinding.apply {
            if (etCompanyName.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tilCompanyName, getString(R.string.company_required))
            else
                GeneralFunctions.hideTextInputLayoutError(tilCompanyName)

            if (etPosition.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tilPosition, getString(R.string.position_required))
            else
                GeneralFunctions.hideTextInputLayoutError(tilPosition)

            if (etCityTown.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tilCityTown, getString(R.string.city_town_required))
            else
                GeneralFunctions.hideTextInputLayoutError(tilCityTown)

            if (etDescription.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tilDescription, getString(R.string.description_required))
            else
                GeneralFunctions.hideTextInputLayoutError(tilDescription)

            if (tvFromDate.text.toString().isEmpty()) {
                GeneralFunctions.validEmptyTextView(this@AddWorkPlaceActivity, listOf(Pair(tvFromDate, getString(R.string.from_date_required))))
                return false
            }

            if (clTo.visibility != View.GONE && tvToDate.text.isEmpty()) {
                GeneralFunctions.validEmptyTextView(this@AddWorkPlaceActivity, listOf(Pair(tvToDate, getString(R.string.to_date_required))))
                return false
            }

            return true
        }
    }

}
*/
