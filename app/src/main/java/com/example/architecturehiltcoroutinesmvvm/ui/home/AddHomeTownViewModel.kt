package com.example.architecturehiltcoroutinesmvvm.ui.home

import android.app.sportapp.remote.Resource
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.remote.RemoteService
import com.example.architecturehiltcoroutinesmvvm.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHomeTownViewModel @Inject constructor(private val workService: RemoteService) : ViewModel() {

    private var _userByIdAndAuthCodeResponse = MutableLiveData<Resource<Any>>()
    var userByIdAndAuthCodeResponse: LiveData<Resource<Any>> = _userByIdAndAuthCodeResponse
    fun getUserByIdAndAuthCodeApi(context: Context, params: HashMap<String, Any>) = viewModelScope.launch {
        _userByIdAndAuthCodeResponse.value = Resource.Loading()
        if (!NetworkUtil.isNetworkConnected(context))
            _userByIdAndAuthCodeResponse.value = Resource.NoInternet(context.getString(R.string.no_internet_connection))
        try {
            val response = workService.getUserByIdAndAuthCode(params)

            if (response.isSuccessful)
                _userByIdAndAuthCodeResponse.value = Resource.Success(response.body()!!)
            else
                _userByIdAndAuthCodeResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _userByIdAndAuthCodeResponse.value = Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }


    private var _updateHomeTownApiResponse = MutableLiveData<Resource<Any>>()
    var updateHomeTownApiResponse: LiveData<Resource<Any>> = _updateHomeTownApiResponse
    fun homeTownProfileUpdate(context: Context, params: HashMap<String, Any>) = viewModelScope.launch {
        _updateHomeTownApiResponse.value = Resource.Loading()
        if (!NetworkUtil.isNetworkConnected(context))
            _updateHomeTownApiResponse.value = Resource.NoInternet(context.getString(R.string.no_internet_connection))
        try {
            val response = workService.profileProfileUpdate(params)
            if (response.isSuccessful)
                _updateHomeTownApiResponse.value = Resource.Success(response.body()!!)
            else
                _updateHomeTownApiResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _updateHomeTownApiResponse.value = Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }
}