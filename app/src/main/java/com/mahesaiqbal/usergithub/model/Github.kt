package com.mahesaiqbal.usergithub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Github {

    @SerializedName("avatar_url")
    @Expose
    open var avatarUrl: String? = null

    @SerializedName("name")
    @Expose
    open var name: String? = null

    @SerializedName("company")
    @Expose
    open var company: String? = null
}