/*
package com.example.architecturehiltcoroutinesmvvm.ui.workplace

import android.app.Activity
import android.app.sportapp.remote.APIConst
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.ui.home.HomeActivity
import com.example.architecturehiltcoroutinesmvvm.utils.BaseFragment
import com.example.architecturehiltcoroutinesmvvm.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkPlaceFragment : BaseFragment(R.layout.fragment_workplace) {

    private lateinit var mBinding: FragmentWorkplaceBinding
    private lateinit var layoutManagerSubComment: LinearLayoutManager
    private val addWorkPlaceViewModelData by viewModels<AddWorkPlaceViewModel>()
    private var allWorkPlaceDataResponse: AllWorkPlaceDataResponse? = null

    private val workPlaceAdapter by lazy {
        WorkPlaceAdapter { position, viewId ->
            if (viewId == R.id.imgEdit) {

                startActivityForResult(Intent(requireContext(), AddWorkPlaceActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(allWorkPlaceDataResponse!!.data[position]))
                    putExtra(APIConst.updates, "update")
                }, 102)
            } else if (viewId == R.id.imgDelete) {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?",
                    positiveButtonText = "Yes",
                    positiveOnClick = {
                        (activity as AboutActivity).addWorkplaceViewModel.deleteWorkPlaceApi(requireContext(), hashMapOf(Pair(
                            APIConst.iWorkplaceId, allWorkPlaceDataResponse!!.data[position].iWorkplaceId)))
                    },
                    negativeButtonText = "No",
                    negativeOnClick = {
                    },
                    neutralOnClick = {})
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentWorkplaceBinding.inflate(inflater, container, false)
        addWorkPlaceViewModelData.callAllWorkPlaceDataByUserIdAPI(requireContext(), hashMapOf(Pair(
            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            (activity as AboutActivity).addWorkplaceViewModel.deleteRecordPlaceApi.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        (activity as AboutActivity).addWorkplaceViewModel.callAllWorkPlaceDataByUserIdAPI(requireContext(), hashMapOf(Pair(
                            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))

                    }
                }
            }

            (activity as AboutActivity).addWorkplaceViewModel.getAllWorkPlaceDataByUserIdAPIResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as AllWorkPlaceDataResponse).apply {
                        allWorkPlaceDataResponse = successResponse.data
                        workPlaceAdapter.setData(allWorkPlaceDataResponse!!.data ?: arrayListOf())
                    }
                }
            }

            layoutManagerSubComment = LinearLayoutManager(requireContext())
            recyclerWork.layoutManager = layoutManagerSubComment
            recyclerWork.adapter = workPlaceAdapter
        }

        mBinding.apply {
            tvAddWorkplace.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddWorkPlaceActivity::class.java).apply {}, 102)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            (activity as HomeActivity).addWorkplaceViewModel.callAllWorkPlaceDataByUserIdAPI(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        }
    }
}*/
