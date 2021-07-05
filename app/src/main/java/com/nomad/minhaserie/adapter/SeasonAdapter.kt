package com.nomad.minhaserie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Season
import com.nomad.minhaserie.ui.SeasonEpisodes
import kotlinx.android.synthetic.main.item_season.view.*

class SeasonAdapter(var seasons: List<Season>, var context: Context) :
    RecyclerView.Adapter<SeasonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_season, parent, false)
        return SeasonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasons[position]
        holder.bindView(season, context)
    }
}


class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val number: TextView = itemView.txtSeasonNumber
    private val crdSeason: CardView = itemView.crdSeason

    @SuppressLint("SetTextI18n")
    fun bindView(season: Season, context: Context) {
        number.text = "${season.number}ª Temporada"
        crdSeason.setOnClickListener {
            val i = Intent(context, SeasonEpisodes::class.java)
            i.putExtra("season_id", season.id)
            i.putExtra("season_name_number", number.text.toString())
            context.startActivity(i)
        }
    }


}