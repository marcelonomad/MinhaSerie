package com.nomad.minhaserie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nomad.minhaserie.R
import com.nomad.minhaserie.adapter.ShowAdapter
import com.nomad.minhaserie.api.TVMazeApi
import com.nomad.minhaserie.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getShows()
    }

    private fun getShows() {
        val client = TVMazeApi.getInstance()
        val tvMaze = client.create(TVMazeEndpoints::class.java)
        val shows = tvMaze.getShowByPage(1)

        shows.enqueue(object : retrofit2.Callback<List<Show>> {
            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                //TODO:falha
            }

            override fun onResponse(
                call: Call<List<Show>>,
                response: Response<List<Show>>
            ) {
                if (response.body() != null) {
                    val lm = LinearLayoutManager(this@MainActivity)

                    rcvItemShow.layoutManager = lm
                    val adapter =
                        ShowAdapter(
                            response.body()!!.toList(),
                            this@MainActivity
                        )
                    rcvItemShow.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

}