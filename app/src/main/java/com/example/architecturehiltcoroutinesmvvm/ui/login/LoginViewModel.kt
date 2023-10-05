package com.example.architecturehiltcoroutinesmvvm.ui.login

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
class LoginViewModel @Inject constructor(private val remoteService: RemoteService) : ViewModel() {

    private var _loginAPIResponse = MutableLiveData<Resource<Any>>()
    var loginAPIResponse: LiveData<Resource<Any>> = _loginAPIResponse

    fun callLoginAPI(context: Context, params: HashMap<String, Any>) = viewModelScope.launch {

        _loginAPIResponse.value = Resource.Loading()

        if (!NetworkUtil.isNetworkConnected(context))
            _loginAPIResponse.value = Resource.NoInternet(context.getString(R.string.no_internet_connection))

        try {
            val response = remoteService.login(params)
            if (response.isSuccessful)
                _loginAPIResponse.value = Resource.Success(response.body()!!)
            else
                _loginAPIResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _loginAPIResponse.value = Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }

}