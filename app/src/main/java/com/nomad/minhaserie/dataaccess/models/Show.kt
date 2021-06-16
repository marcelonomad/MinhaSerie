package com.nomad.minhaserie.dataaccess.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Show(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("summary")
    @Expose
    val summary: String,

    @SerializedName("image")
    @Expose
    val image: ImageShow,

    @SerializedName("genres")
    @Expose
    val genres: List<String>,

    @SerializedName("rating")
    @Expose
    val rating: Rating
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