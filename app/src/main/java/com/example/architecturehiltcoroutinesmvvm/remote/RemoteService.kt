
package com.example.architecturehiltcoroutinesmvvm.remote

import com.example.architecturehiltcoroutinesmvvm.ui.home.UserByIdAndAuthcodeResponse
import com.example.architecturehiltcoroutinesmvvm.ui.login.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface RemoteService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(@FieldMap params: HashMap<String, Any>): Response<LoginResponse>

    @FormUrlEncoded
    @POST("profile/getUserByIdAndAuthcode")
    suspend fun getUserByIdAndAuthCode(@FieldMap params: HashMap<String, Any>): Response<UserByIdAndAuthcodeResponse>

    @FormUrlEncoded
    @POST("profile/profileUpdate")
    suspend fun profileProfileUpdate(@FieldMap params: HashMap<String, Any>): Response<Status>

}