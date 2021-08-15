package com.foreachsoft.TestingGoogleSignIn.Common

import com.foreachsoft.TestingGoogleSignIn.Interface.RetrofitServices
import com.foreachsoft.TestingGoogleSignIn.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://rawgit.com/startandroid/data/master/messages/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}