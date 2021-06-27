package com.nomad.minhaserie.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akiniyalocts.pagingrecycler.PagingDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nomad.minhaserie.BuildConfig
import com.nomad.minhaserie.R
import com.nomad.minhaserie.adapter.ShowAdapter
import com.nomad.minhaserie.dataaccess.api.TVMazeApi
import com.nomad.minhaserie.dataaccess.api.TVMazeEndpoints
import com.nomad.minhaserie.dataaccess.models.Show
import com.nomad.minhaserie.dataaccess.models.ShowByName
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_search_show.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity(), PagingDelegate.OnPageListener {

    private var shows = mutableListOf<Show>()
    private var tempShows = mutableListOf<Show>()
    private lateinit var adapter: ShowAdapter
    private var showsPage = 1
    private val client = TVMazeApi.getInstance()
    private val tvMaze = client.create(TVMazeEndpoints::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getShows(firstLoad = true)
    }

    private fun getShows(firstLoad: Boolean = false) {
        llLoader.visibility = View.VISIBLE
        val shows = tvMaze.getShowsByPage(showsPage)

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

    private fun getShowsByName(name: String) {
        adapter.removeAllShows()
        this@MainActivity.tempShows.clear()
        this@MainActivity.adapter.notifyDataSetChanged()

        llLoader.visibility = View.VISIBLE
        val shows = tvMaze.getShowsByName(name)
        shows.enqueue(object : retrofit2.Callback<List<ShowByName>> {
            override fun onFailure(call: Call<List<ShowByName>>, t: Throwable) {
                llLoader.visibility = View.INVISIBLE
            }

            override fun onResponse(
                call: Call<List<ShowByName>>,
                response: Response<List<ShowByName>>
            ) {
                if (response.body() != null) {
                    var showsByName = response.body() as MutableList<ShowByName>
                    showsByName.forEach {
                        this@MainActivity.tempShows.add(it.show)
                    }
                    this@MainActivity.shows.addAll(this@MainActivity.tempShows)
                    this@MainActivity.adapter.notifyItemRangeInserted(
                        0,
                        this@MainActivity.shows.size
                    )
                    llLoader.visibility = View.INVISIBLE
                    this@MainActivity.adapter.notifyDataSetChanged()
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
            val lastItem = adapter.pagingItemCount - 1
            adapter.addShows(tempShows)
            rcvItemShow.smoothScrollToPosition(lastItem + 5)
        } else onDonePaging()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_show, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnSearchShow) {
            showSearchOptions()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearchOptions() {
        val dialog = BottomSheetDialog(this, R.style.DialogStyle)
        dialog.setContentView(R.layout.item_search_show)
        if (BuildConfig.DEBUG) {
            dialog.txtSearchShow.setText("Supernatural")
        }
        dialog.btnSearch.setOnClickListener {
            getShowsByName(dialog.txtSearchShow.text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }
}