package com.mahesaiqbal.usergithub.api

import com.mahesaiqbal.usergithub.model.Github
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface Service {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<Github>
}