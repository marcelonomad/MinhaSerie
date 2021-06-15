package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageShow (
    @SerializedName("medium")
    @Expose
    private val medium: String? = null,

    @SerializedName("original")
    @Expose
    private val original: String? = null
)