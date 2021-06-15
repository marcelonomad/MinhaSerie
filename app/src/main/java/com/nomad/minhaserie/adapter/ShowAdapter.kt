package com.nomad.minhaserie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Show
import kotlinx.android.synthetic.main.item_show.view.*

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


}






