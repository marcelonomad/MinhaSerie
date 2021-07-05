package com.nomad.minhaserie.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nomad.minhaserie.R
import com.nomad.minhaserie.adapter.EpisodeAdapter
import com.nomad.minhaserie.dataaccess.api.TVMazeApi
import com.nomad.minhaserie.dataaccess.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Episode
import kotlinx.android.synthetic.main.activity_season_episodes.*
import retrofit2.Call
import retrofit2.Response

class SeasonEpisodes : AppCompatActivity() {
    var seasonId: Int = 0
    var seasonName: String = ""
    private val client = TVMazeApi.getInstance()
    private val tvMaze = client.create(TVMazeEndpoints::class.java)
    private lateinit var episodes: List<Episode>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_episodes)
        seasonId = intent.getSerializableExtra("season_id") as Int
        seasonName = intent.getSerializableExtra("season_name_number") as String

        try {
            supportActionBar?.title = seasonName
        } catch (e: Exception) {
            Log.d("Erro", e.toString())
        }
        getEpisodes()

    }


    private fun getEpisodes() {
        pgbEpisodeList.visibility = View.VISIBLE
        rcvEpisodes.visibility = View.GONE
        val episode = tvMaze.getEpisodesBySeasonId(seasonId)

        episode.enqueue(object : retrofit2.Callback<List<Episode>> {
            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                pgbEpisodeList.visibility = View.GONE
                rcvEpisodes.visibility = View.VISIBLE
            }

            override fun onResponse(
                call: Call<List<Episode>>,
                response: Response<List<Episode>>
            ) {
                if (response.body() != null) {
                    episodes = response.body()!!
                    loadData()
                    pgbEpisodeList.visibility = View.GONE
                    rcvEpisodes.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun loadData() {
        val lm = LinearLayoutManager(this)
        rcvEpisodes.layoutManager = lm
        val adapter = EpisodeAdapter(episodes, this)
        rcvEpisodes.adapter = adapter
    }


}

