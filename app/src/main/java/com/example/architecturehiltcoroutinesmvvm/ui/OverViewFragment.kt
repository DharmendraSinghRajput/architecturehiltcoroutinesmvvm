package com.example.architecturehiltcoroutinesmvvm.ui

import android.app.sportapp.remote.APIConst
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.databinding.FragmentOverViewBinding
import com.example.architecturehiltcoroutinesmvvm.ui.home.AddHomeTownActivity
import com.example.architecturehiltcoroutinesmvvm.ui.home.HomeActivity
import com.example.architecturehiltcoroutinesmvvm.ui.home.UserByIdAndAuthcodeResponse
import com.example.architecturehiltcoroutinesmvvm.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverViewFragment : BaseFragment(R.layout.fragment_over_view) {

    private lateinit var mBinding: FragmentOverViewBinding
    private lateinit var userByIdAndAuthCodeResponse: UserByIdAndAuthcodeResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentOverViewBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            (activity as HomeActivity).addHomeTownViewModel.userByIdAndAuthCodeResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    Log.d("ResponseDataUser1","${successResponse.data}")
                    userByIdAndAuthCodeResponse = successResponse.data as UserByIdAndAuthcodeResponse
                    if (userByIdAndAuthCodeResponse.data.vHomeTown.isNullOrEmpty()) {
                        includeAddHomeTown.contactsType.visibility = View.GONE
                        tvAddHomeTown.visibility = View.VISIBLE
                    } else {
                        includeAddHomeTown.contactsType.visibility = View.VISIBLE
                        tvAddHomeTown.visibility = View.GONE
                        includeAddHomeTown.tvCity.visibility = View.GONE
                        includeAddHomeTown.tvCompanyName.visibility = View.GONE
                        includeAddHomeTown.tvUniversity.visibility = View.GONE
                        includeAddHomeTown.tvToDate.visibility = View.GONE
                        includeAddHomeTown.tvEducationTitle.text = userByIdAndAuthCodeResponse.data.vHomeTown
                    }
                }
            }

            tvAddHomeTown.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddHomeTownActivity::class.java).apply {}, 104)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        (activity as HomeActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireActivity(), hashMapOf(Pair(APIConst.iUserId,"158")))

        //  (activity as HomeActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireActivity(), hashMapOf(Pair(APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))

    }
}