package com.bariski.playground

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class HomeCarouselHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView
    var image: ImageView
    var rating: RatingBar
    var year: TextView
    var container = itemView

    init {
        title = itemView.findViewById(R.id.name)
        image = itemView.findViewById(R.id.image)
        rating = itemView.findViewById(R.id.rating)
        year = itemView.findViewById(R.id.year)
    }
}