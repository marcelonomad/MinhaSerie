package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode(

    @SerializedName("id")
    @Expose
    val id: Integer,

    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("season")
    @Expose
    val season: Integer,
    @SerializedName("number")
    @Expose
    val number: Integer,

    @SerializedName("summary")
    @Expose
    val summary: String
)