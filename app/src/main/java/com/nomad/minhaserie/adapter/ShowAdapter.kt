package com.nomad.minhaserie.adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akiniyalocts.pagingrecycler.PagingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.item_show.view.*


class ShowAdapter(var shows: MutableList<Show>, var context: Context) : PagingAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_show, parent, false)
        return ShowViewHolder(view)
        //return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val show = shows[position]
        (holder as ShowViewHolder).let { it.bindView(show, context) }

    }

    fun addShows(shows: List<Show>) {
        var lastSize = this.shows.size
        var newItems = shows.size
        this.shows.toMutableList().addAll(shows)
        notifyItemRangeInserted(lastSize, newItems)
    }

    fun removeAllShows() {
        val showCount = shows.size
        this.shows.clear()
        notifyItemRangeRemoved(0, showCount)
    }

    override fun getPagingLayout(): Int {
        return R.layout.item_show
    }

    override fun getPagingItemCount(): Int {
        return shows.size
    }
}

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.txtNameShow
    private val image: ImageView = itemView.imgItemShow
    private val tagOne: TextView = itemView.txtShowTagOne
    private val showRate: TextView = itemView.txtShowRate

    fun bindView(show: Show, context: Context) {
        name.text = show.name
        if (show.genres.isNotEmpty())
            tagOne.text = show.genres[0]
        else tagOne.visibility = View.GONE

        showRate.text = show.rating.average.toString()

        if (show.image.medium != null)
            Glide.with(context)
                .load(show.image.medium)
                .placeholder(R.drawable.popcorn)
                .apply(RequestOptions().override(200, 300))
                .into(image)
    }
}

/*

class ShowAdapter(private val shows: List<Show>, val context: Context) :
    RecyclerView.Adapter<ShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]
        holder.let { it.bindView(show, context) }
    }
}


class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.txtNameShow
    private val image: ImageView = itemView.imgItemShow
    private val tagOne: TextView = itemView.txtShowTagOne

    fun bindView(show: Show, context: Context) {
        name.text = show.name
        if (show.genres.isNotEmpty())
            tagOne.text = show.genres[0]

        if (show.image.medium != null)
            Glide.with(context)
                .load(show.image.medium)
                .apply(RequestOptions().override(200, 300))

                .into(image)
    }


}*/






