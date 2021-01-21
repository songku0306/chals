package com.example.mealgame

import android.os.Looper
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealgame.Mealworm.power
import pl.droidsonroids.gif.GifImageView
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.timer

class Gold: ViewModel() {

    private lateinit var lbl_gold: TextView

//    var timerTask: Timer? = null

    internal val _gold = MutableLiveData<Int>()
    val gold: LiveData<Int>
        get() = _gold

    private var handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable



    fun onGold() {

        _gold.value = (_gold.value)?.plus(power)
        handler.postDelayed(runnable, 1000)
        }


//    fun offTimer() {
//        timerTask?.cancel()
//    }
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
        power++
    }
}