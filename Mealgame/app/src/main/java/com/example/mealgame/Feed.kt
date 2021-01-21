package com.example.mealgame

data class Feed(val imageId: Int, val stage: Int, val round: Int, val healthPoint: Int) {



    val feeds = mutableListOf(
        Feed(R.drawable.beans, 1, 1, 100),
        Feed(R.drawable.gif_ciz, 1, 2, 500),
        Feed(R.drawable.tuna, 1, 3, 2500)
    )




}