package com.bariski.playground


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View


class CarouselListener(//The pivot to be snapped to
        private var centerPivot: Int) : RecyclerView.OnScrollListener() {

    //Flag to avoid recursive calls
    private var isCascadedCall = true


    /**
     * Overrides the scroll state changes and
     * repositions the scroll to lock the view
     * to the center
     *
     * @param recyclerView
     * @param newState
     */
    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        val lm = recyclerView!!.layoutManager as LinearLayoutManager

        // Default pivot , Its a bit inaccurate .
        // Better pass the center pivot as your Center Indicator view's
        // calculated center on it OnGlobalLayoutListener event

        if (centerPivot == 0) {
            centerPivot = if (lm.orientation == LinearLayoutManager.HORIZONTAL) recyclerView.left + recyclerView.right else recyclerView.top + recyclerView.bottom
        }

        if (!isCascadedCall) {

            //Scroll has stopped we can now proceed with computations
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                val view = findCenterView(lm)//get the view nearest to center
                val viewCenter = if (lm.orientation == LinearLayoutManager.HORIZONTAL) (view!!.left + view.right) / 2 else (view!!.top + view.bottom) / 2

                // Compute scroll from center
                // Add or subtract any offsets you need here
                val scrollNeeded = viewCenter - centerPivot

                if (lm.orientation == LinearLayoutManager.HORIZONTAL) {
                    recyclerView.smoothScrollBy(scrollNeeded, 0)
                } else {
                    recyclerView.smoothScrollBy(0, scrollNeeded)
                }

                isCascadedCall = true
            }
        }


        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            isCascadedCall = false
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        setViewAttributes(recyclerView?.layoutManager as LinearLayoutManager)
    }

    /**
     * This method iterates through the visible views and
     * figures out the
     *
     * @param lm - The LinearLayoutManager of the RecyclerView
     * @return View nearest to the center
     */
    private fun findCenterView(lm: LinearLayoutManager): View? {
        var view: View? = null
        var returnView: View? = null

        var minDistance = 0
        var isNotFound = true

        var i = lm.findFirstVisibleItemPosition()
        while (i <= lm.findLastVisibleItemPosition() && isNotFound) {

            view = lm.findViewByPosition(i)

            val center = if (lm.orientation == LinearLayoutManager.HORIZONTAL) (view!!.left + view.right) / 2 else (view!!.top + view.bottom) / 2
            val leastDifference = Math.abs(centerPivot - center)

            if (leastDifference <= minDistance || i == lm.findFirstVisibleItemPosition()) {
                minDistance = leastDifference
                returnView = view
            } else {
                isNotFound = false

            }
            i++
        }
        return returnView
    }

    private fun setViewAttributes(lm: LinearLayoutManager) {


        val minAlpha = 0.5f
        val minScale = 0.75f
        var i = lm.findFirstVisibleItemPosition()
        var j = lm.findLastVisibleItemPosition()
        while (i > -1 && i <= j) {

            val view = lm.findViewByPosition(i)
            val center = if (lm.orientation == LinearLayoutManager.HORIZONTAL) (view!!.left + view.right) / 2 else (view!!.top + view.bottom) / 2
            var difference = Math.abs(centerPivot - center)
            if (difference > centerPivot) {
                difference = centerPivot
            }

            view.scaleY = minScale + (1 - minScale) * (centerPivot - difference) / centerPivot.toFloat()
            view.scaleX = minScale + (1 - minScale) * (centerPivot - difference) / centerPivot.toFloat()
            view.alpha = minAlpha + (1 - minAlpha) * (centerPivot - difference) / centerPivot.toFloat()
            i++
        }

    }


}