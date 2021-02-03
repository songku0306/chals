package com.example.mealgame

import android.os.Looper
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealgame.Mealworm.power
import pl.droidsonroids.gif.GifImageView


open class Gold: ViewModel() {

    private lateinit var lbl_gold: TextView


    internal val _gold = MutableLiveData<Int>()
    val gold: LiveData<Int>
        get() = _gold

//        fun onGold() {
//            ViewModel._gold.value = (ViewModel._gold.value)?.plus(Mealworm.power)
//        }


    init {
        _gold.value = 0


    }
}

private lateinit var gif_worm: GifImageView

object Mealworm {


    var power = 1



    val mealworms = mutableListOf(
        "wormlv1",
        "wormlv2",
        "wormlv3"
    )


    fun wormPower() {

        }

    }
