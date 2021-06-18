package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShowByName(
    @SerializedName("score")
    @Expose
    val score: Double,

    @SerializedName("show")
    @Expose
    val show: Show
)