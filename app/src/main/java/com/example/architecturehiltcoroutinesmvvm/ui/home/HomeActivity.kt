package com.example.architecturehiltcoroutinesmvvm.ui.home

import android.app.sportapp.remote.APIConst
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.architecturehiltcoroutinesmvvm.databinding.ActivityHomeBinding
import com.example.architecturehiltcoroutinesmvvm.ui.OverViewFragment
import com.example.architecturehiltcoroutinesmvvm.utils.BaseActivity
import com.example.architecturehiltcoroutinesmvvm.utils.PrefUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val tabItems = arrayListOf("Overview", "Employment", "Education", "Basic Info")
    private val tabFragments = arrayListOf<Fragment>(OverViewFragment())
    val addUniversityViewModel by viewModels<AddHomeTownViewModel>()
    val addHomeTownViewModel by viewModels<AddHomeTownViewModel>()

    val mBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

            addHomeTownViewModel.getUserByIdAndAuthCodeApi(this@HomeActivity, hashMapOf(Pair(APIConst.iUserId, prefUtil.getInt("158"))))

            tlEditProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.text.toString() == "Home")
                        vpEditProfile.isUserInputEnabled = true
                    else {
                        vpEditProfile.isUserInputEnabled = true
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
            vpEditProfile.adapter = MyProfilePagerAdapter(supportFragmentManager, lifecycle, tabFragments)
            TabLayoutMediator(tlEditProfile, vpEditProfile) { tab, position ->
                tab.text = tabItems[position]
            }.attach()
        }
    }
}