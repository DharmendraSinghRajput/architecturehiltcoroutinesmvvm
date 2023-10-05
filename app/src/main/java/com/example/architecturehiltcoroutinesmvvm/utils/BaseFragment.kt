package com.example.architecturehiltcoroutinesmvvm.utils

import android.app.sportapp.remote.Resource
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

open class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    // Calling Base Activity's handleLoader
    fun handleLoader(resource: Resource<Any>, showLoader: Boolean = true, swipeRefreshLayout: SwipeRefreshLayout? = null, successResponse: (Resource<Any>) -> Unit) {
        (this.activity as BaseActivity).handleLoader(resource, showLoader, swipeRefreshLayout = swipeRefreshLayout) { response ->
            successResponse(response)
        }
    }
}