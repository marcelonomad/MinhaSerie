package com.nomad.minhaserie.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.api.TVMazeApi
import com.nomad.minhaserie.dataaccess.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Episode
import com.nomad.minhaserie.dataaccess.models.Season
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.activity_episode_detail.*
import kotlinx.android.synthetic.main.activity_show_detail.*
import retrofit2.Call
import retrofit2.Response

class EpisodeDetail : AppCompatActivity() {
    private val client = TVMazeApi.getInstance()
    private val tvMaze = client.create(TVMazeEndpoints::class.java)
    private lateinit var episode: Episode

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        episode = (intent.getSerializableExtra("episode") as Episode)!!

        lblNomeNumeroEpisodioDetalhe.text = "T${episode.season}xE${episode.number} - ${episode.name}"
        txtResumoEpisodioDetalhe.text = Html.fromHtml(episode.summary)
        if (episode.image?.medium != null) {
            Glide.with(this)
                .load(episode.image.medium)
                .placeholder(R.drawable.popcorn)
                .apply(RequestOptions().override(300, 400))
                .into(imgEpisodioDetalhe)
        } else {
            Glide.with(this)
                .load(R.drawable.popcorn)
                .apply(RequestOptions().override(200, 300))
                .into(imgEpisodioDetalhe)

        }

    }

}