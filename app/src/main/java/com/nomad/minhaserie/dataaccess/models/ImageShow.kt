package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageShow(
    @SerializedName("medium")
    @Expose
    val medium: String? = null,

    @SerializedName("original")
    @Expose
    val original: String? = null
):Serializable