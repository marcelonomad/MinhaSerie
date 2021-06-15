package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Show(

    @SerializedName("id")
    @Expose
     val id: Integer,

    @SerializedName("name")
    @Expose
     val name: String,

    @SerializedName("summary")
    @Expose
    val summary: String
)


/*
@SerializedName("genres")
@Expose
private List<String> genres = null;
*/
/*
@SerializedName("schedule")
@Expose
private Schedule schedule;*/
/*
@SerializedName("image")
@Expose
private Image image;*/