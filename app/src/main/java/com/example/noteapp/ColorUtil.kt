package com.example.noteapp



object ColorUtil {
    fun getColorByPosition(position: Int): Int {
        val colorVal = position % 5
        var color = 0

        when (colorVal) {
            0 -> {
                color =
                    R.color.orange
            }
            1 -> {
                color =
                    R.color.newblue
            }
            2 -> {
                color =
                    R.color.newred
            }
            3 -> {
                color =
                    R.color.newblue
            }
            4 ->{
                color = R.color.newyellow
            }
        }
        return color
    }



}