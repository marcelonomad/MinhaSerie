package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("number")
    @Expose
    var number: Int,
    var episodes: List<Episode>
)