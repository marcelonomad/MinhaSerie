package com.nomad.minhaserie.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akiniyalocts.pagingrecycler.PagingDelegate
import com.nomad.minhaserie.R
import com.nomad.minhaserie.adapter.ShowAdapter
import com.nomad.minhaserie.api.TVMazeApi
import com.nomad.minhaserie.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity(), PagingDelegate.OnPageListener {

    private var shows = mutableListOf<Show>()
    private var tempShows = mutableListOf<Show>()
    private lateinit var adapter: ShowAdapter
    private var showsPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getShows(firstLoad = true)
    }

    private fun getShows(firstLoad: Boolean = false) {
        llLoader.visibility = View.VISIBLE

        val client = TVMazeApi.getInstance()
        val tvMaze = client.create(TVMazeEndpoints::class.java)
        val shows = tvMaze.getShowByPage(showsPage)

        shows.enqueue(object : retrofit2.Callback<List<Show>> {
            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                //TODO:falha
                llLoader.visibility = View.INVISIBLE

            }

            override fun onResponse(
                call: Call<List<Show>>,
                response: Response<List<Show>>
            ) {
                if (response.body() != null) {
                    this@MainActivity.tempShows = response.body() as MutableList<Show>
                    this@MainActivity.shows.addAll(this@MainActivity.tempShows)
                    if (firstLoad)
                        setupRecyclerView()
                    showsPage++
                    this@MainActivity.adapter.notifyDataSetChanged()
                    llLoader.visibility = View.INVISIBLE
                }
            }
        })
    }

    fun setupRecyclerView() {
        val lm = LinearLayoutManager(this@MainActivity)
        rcvItemShow.layoutManager = lm
        adapter = ShowAdapter(this@MainActivity.shows, this@MainActivity)
        val pagingDelegate = PagingDelegate.Builder(adapter)
            .attachTo(rcvItemShow)
            .listenWith(this@MainActivity)
            .build()
        rcvItemShow.adapter = adapter
    }

    override fun onDonePaging() {
        llLoader.visibility = View.INVISIBLE
    }

    override fun onPage(p0: Int) {
        if (p0 <= shows.size) {
            getShows()
            val lastItem = adapter!!.pagingItemCount - 1
            adapter.addShows(tempShows)
            rcvItemShow.smoothScrollToPosition(lastItem + 5)
        } else onDonePaging()
    }

}