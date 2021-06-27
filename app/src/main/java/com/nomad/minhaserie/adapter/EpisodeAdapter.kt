package com.nomad.minhaserie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Episode
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
    private val summary: TextView = itemView.txtEpisodeSummary

    @SuppressLint("SetTextI18n")
    fun bindView(episode: Episode, context: Context) {
        numberName.text = "${episode.number} - ${episode.name}"
        summary.text = Html.fromHtml(episode.summary)
    }

}