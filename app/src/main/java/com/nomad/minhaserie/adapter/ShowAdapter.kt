package com.nomad.minhaserie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nomad.minhaserie.R
import com.nomad.minhaserie.dataaccess.models.Show

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
    private val name: TextView = itemView.findViewById(R.id.txtNameShow)
    private val image: ImageView = itemView.findViewById(R.id.imgItemShow)

    fun bindView(show: Show, context: Context) {
        name.text = show.name
    }


}






