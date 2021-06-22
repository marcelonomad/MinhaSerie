package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("time")
    @Expose
    val time: String,
    @SerializedName("days")
    @Expose
    val days: List<String>
)