package com.nomad.minhaserie.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nomad.minhaserie.R
import com.nomad.minhaserie.adapter.SeasonAdapter
import com.nomad.minhaserie.dataaccess.api.TVMazeApi
import com.nomad.minhaserie.dataaccess.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Episode
import com.nomad.minhaserie.dataaccess.models.Season
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.activity_show_detail.*
import retrofit2.Call
import retrofit2.Response

class ShowDetail : AppCompatActivity() {
    var id: Int = 0
    private val client = TVMazeApi.getInstance()
    private val tvMaze = client.create(TVMazeEndpoints::class.java)
    private lateinit var show: Show
    private lateinit var seasons: List<Season>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        id = intent.getSerializableExtra("id") as Int

        getShowById()
    }


    private fun getShowById() {
        val show = tvMaze.getShowById(id)
        pgbShowDetalhe.visibility = View.VISIBLE
        srvShowDetalhe.visibility = View.GONE

        show.enqueue(object : retrofit2.Callback<Show> {
            override fun onFailure(call: Call<Show>, t: Throwable) {
                //TODO:falha
                pgbShowDetalhe.visibility = View.GONE
                srvShowDetalhe.visibility = View.VISIBLE

            }

            override fun onResponse(
                call: Call<Show>,
                response: Response<Show>
            ) {
                if (response.body() != null) {
                    this@ShowDetail.show = response.body()!!
                    loadShowData()
                    getShowSeasons(id)
                }
            }
        })
    }

    private fun getShowSeasons(showId: Int) {
        val season = tvMaze.getSeasonsByShowId(showId)
        season.enqueue(object : retrofit2.Callback<List<Season>> {
            override fun onFailure(call: Call<List<Season>>, t: Throwable) {
                pgbShowDetalhe.visibility = View.GONE
                srvShowDetalhe.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<List<Season>>, response: Response<List<Season>>) {
                if (response.body() != null) {
                    this@ShowDetail.seasons = response.body()!!
                    loadSeasonsData()
                    pgbShowDetalhe.visibility = View.GONE
                    srvShowDetalhe.visibility = View.VISIBLE
                }
            }
        })
    }


    private fun loadSeasonsData() {
        val lm = LinearLayoutManager(this)
        rcvSeasons.layoutManager = lm
        val adapter = SeasonAdapter(seasons, this)
        rcvSeasons.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun loadShowData() {
        lblNomeShow.text = show.name
        lblShowRateDetalhe.text = show.rating.average.toString()
        if (show.image?.medium != null) {
            Glide.with(this)
                .load(show.image.medium)
                .placeholder(R.drawable.popcorn)
                .apply(RequestOptions().override(200, 300))
                .into(imgShowDetalhe)
        } else {
            Glide.with(this)
                .load(R.drawable.popcorn)
                .apply(RequestOptions().override(200, 300))
                .into(imgShowDetalhe)

        }
        if (show.genres.isNotEmpty()) {
            var showGeneros = show.genres
            var generos = showGeneros[0]
            if (showGeneros.size > 1)
                for (i in 1 until showGeneros.size)
                    generos = generos.plus(", ${showGeneros[i]}")
            lblShowGeneros.text = generos
        }

        lblHoraShowDetalhe.text = "${getString(R.string.hour)}: ${show.schedule.time}"
        if (show.schedule.days.isNotEmpty()) {
            var dias = show.schedule.days
            var programacaoDias = "${getString(R.string.days)}: ${dias[0]}"
            if (dias.size > 1)
                for (i in 1 until dias.size)
                    programacaoDias = programacaoDias.plus(", ${dias[i]}")
            lblDiaShowDetalhe.text = programacaoDias
        }
        if (show.summary.isNullOrEmpty())
            lblShowResumoDetalheTexto.text = ""
        else
            lblShowResumoDetalheTexto.text = Html.fromHtml(show.summary)
    }
}













