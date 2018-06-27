package com.mahesaiqbal.usergithub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.mahesaiqbal.usergithub.api.Service
import com.mahesaiqbal.usergithub.model.Github
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialized gson
        val gson = GsonBuilder().create()

        //Initialized retrofit
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com")
                .build()

        val service: Service = retrofit.create(Service::class.java)

        //Get data from github by username
        service.getUser("mahesaiqbal")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { user -> getData(user)},
                        { error -> Log.e("Error", error.message) })
    }

    private fun getData(user: Github?) {
        val image = findViewById<ImageView>(R.id.image)
        val username = findViewById<TextView>(R.id.username)
        val company = findViewById<TextView>(R.id.company)

        Glide.with(this).load(user?.avatarUrl).into(image)
        username.text = user?.name
        company.text = user?.company
    }
}
