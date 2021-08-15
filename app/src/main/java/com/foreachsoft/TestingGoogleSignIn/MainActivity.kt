package com.foreachsoft.TestingGoogleSignIn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.foreachsoft.TestingGoogleSignIn.Adapter.MyMessageAdapter
import com.foreachsoft.TestingGoogleSignIn.Common.Common
import com.foreachsoft.TestingGoogleSignIn.Interface.RetrofitServices
import com.foreachsoft.TestingGoogleSignIn.MessageViewModel.MessageViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var googleSignInAccount: GoogleSignInAccount? = null
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var myMessageAdapter: MyMessageAdapter
    private lateinit var viewModel: MessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "started", Toast.LENGTH_LONG).show()
        mService = Common.retrofitService
        myMessageAdapter = MyMessageAdapter(mutableListOf())
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerMessageList.layoutManager = layoutManager
        recyclerMessageList.adapter = myMessageAdapter
        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        viewModel.viewModelRequest()
        viewModel.messageListLiveData.observe(this, Observer {
            myMessageAdapter.setMessagesList(it)
        })

        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(applicationContext)
        val mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        if (googleSignInAccount != null) {
            name.text = googleSignInAccount!!.displayName
            email.text = googleSignInAccount!!.email
        } else
            Toast.makeText(this, "null", Toast.LENGTH_LONG).show()

//        fun getAllMessageList() {
//            val mService = com.foreachsoft.TestingGoogleSignIn.Common.retrofitService
//            mService.getMessageList().enqueue(object : Callback<MutableList<Message>> {
//                override fun onFailure(call: Call<MutableList<Message>>, t: Throwable) {
//                    messageList.postValue(null)
//                }
//
//                override fun onResponse(
//                    call: Call<MutableList<Message>>,
//                    response: Response<MutableList<Message>>
//                ) {
//                    if (response.isSuccessful) {
//                        messageList.postValue(response.body())
//                        response.body()?.let { myMessageAdapter.setMessagesList(it) }
//                    }
//                }
//            })
//          }

        button.setOnClickListener {
            mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, OnCompleteListener<Void?> {
                    // ...
                })
            val user = Firebase.auth
            user.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }


}