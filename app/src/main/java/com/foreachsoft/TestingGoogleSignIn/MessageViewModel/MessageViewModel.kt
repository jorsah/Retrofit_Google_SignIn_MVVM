package com.foreachsoft.TestingGoogleSignIn.MessageViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreachsoft.TestingGoogleSignIn.Common.Common
import com.foreachsoft.TestingGoogleSignIn.Model.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageViewModel : ViewModel() {
    private val _messageListLiveData = MutableLiveData<MutableList<Message>>()
    val messageListLiveData: LiveData<MutableList<Message>>
        get() = _messageListLiveData


    fun viewModelRequest() {
        val mService = Common.retrofitService
        mService.getMessageList().enqueue(object : Callback<MutableList<Message>> {
            override fun onFailure(call: Call<MutableList<Message>>, t: Throwable) {
                _messageListLiveData.postValue(null)
            }

            override fun onResponse(
                call: Call<MutableList<Message>>,
                response: Response<MutableList<Message>>
            ) {
                if (response.isSuccessful) {
                    _messageListLiveData.postValue(response.body())
                }
            }
        })
    }

}