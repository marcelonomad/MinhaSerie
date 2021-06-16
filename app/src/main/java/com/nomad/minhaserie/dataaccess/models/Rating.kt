package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    @Expose
    var average: Double
)