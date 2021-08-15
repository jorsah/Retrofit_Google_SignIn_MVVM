package com.foreachsoft.TestingGoogleSignIn.Interface

import com.foreachsoft.TestingGoogleSignIn.Model.Message
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("messages1.json")
    fun getMessageList(): Call<MutableList<Message>>
}