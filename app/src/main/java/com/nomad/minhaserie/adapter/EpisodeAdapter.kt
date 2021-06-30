package com.nomad.minhaserie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Episode
import com.nomad.minhaserie.ui.EpisodeDetail
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodeAdapter(var episodes: List<Episode>, var context: Context) :
    RecyclerView.Adapter<EpisodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bindView(episode, context)

    }
}

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val numberName: TextView = itemView.txtEpisodeNumberName

    //private val summary: TextView = itemView.txtEpisodeSummary
    private val crdEpisode: CardView = itemView.crdEpisode

    @SuppressLint("SetTextI18n")
    fun bindView(episode: Episode, context: Context) {
        numberName.text = "${episode.number} - ${episode.name}"
        //summary.text = Html.fromHtml(episode.summary)]
        crdEpisode.setOnClickListener {
            val i = Intent(context, EpisodeDetail::class.java)
            i.putExtra("episode", episode)
            context.startActivity(i)
        }
    }

}