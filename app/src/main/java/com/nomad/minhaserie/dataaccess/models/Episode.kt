package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode(

    @SerializedName("id")
    @Expose
    private val id: Integer,

    @SerializedName("name")
    @Expose
    private val name: String,
    @SerializedName("season")
    @Expose
    private val season: Integer,
    @SerializedName("number")
    @Expose
    private val number: Integer,

    @SerializedName("summary")
    @Expose
    private val summary: String
)

/*@SerializedName("image")
@Expose
private Image image;*/