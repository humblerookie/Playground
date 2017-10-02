package com.bariski.playground

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RcAdapter(val centerPivot: Int, val recyclerView: RecyclerView, val lm: LinearLayoutManager) : RecyclerView.Adapter<HomeCarouselHolder>() {
    var i = 0
    override fun onBindViewHolder(holder: HomeCarouselHolder, position: Int) {
        setViewAttributes(holder.container)
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeCarouselHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_home_carousel, parent, false)
        if (i++ == 1) {
            recyclerView.scrollToPosition(1)
        }

        return HomeCarouselHolder(view)

    }

    private fun setViewAttributes(view: View) {

        val minAlpha = 0.5f
        val minScale = 0.75f
        val center = if (lm.orientation == LinearLayoutManager.HORIZONTAL) (view!!.left + view.right) / 2 else (view!!.top + view.bottom) / 2
        var difference = Math.abs(centerPivot - center)
        if (difference > centerPivot) {
            difference = centerPivot
        }
        view.scaleY = minScale + (1 - minScale) * (centerPivot - difference) / centerPivot.toFloat()
        view.scaleX = minScale + (1 - minScale) * (centerPivot - difference) / centerPivot.toFloat()
        view.alpha = minAlpha + (1 - minAlpha) * (centerPivot - difference) / centerPivot.toFloat()
    }

}

