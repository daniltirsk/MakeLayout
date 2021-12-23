package com.example.makelayout

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        params.weight = 1.toFloat()

        val fronts = arrayOf(R.drawable.bee,R.drawable.butterfly,R.drawable.butterfly_1,R.drawable.butterfly_2,
            R.drawable.chick, R.drawable.flower, R.drawable.ladybug, R.drawable.sunflower
        )

        var firstCard: View = View(this)
        var openCardsCount = 0 // число открытых карт
        var guessedRightCount = 0

        val catViews = ArrayList<ImageView>()
        fronts.shuffle()

        val colorListener = View.OnClickListener() {
            when (openCardsCount) {
                0 -> {
                    firstCard = it;
                    openCardsCount++;
                    GlobalScope.launch (Dispatchers.Main) { flipOver(firstCard as ImageView,fronts[it.tag.toString().toInt()]) }
                }
                1 -> {
                    if (it.tag == firstCard.tag) {
                        GlobalScope.launch (Dispatchers.Main) {
                            openCardsCount++;
                            flipOver(it as ImageView,fronts[it.tag.toString().toInt()])
                            delay(1000)
                            hide(it as ImageView)
                            hide(firstCard as ImageView)
                            openCardsCount = 0
                            guessedRightCount += 1
                            if (guessedRightCount == fronts.size){
                                val text = "You've Won!"
                                val duration = Toast.LENGTH_SHORT
                                val toast = Toast.makeText(applicationContext, text, duration)
                                toast.show()
                            }
                        }
                    } else {
                        GlobalScope.launch (Dispatchers.Main) {
                            openCardsCount++;
                            flipOver(it as ImageView,fronts[it.tag.toString().toInt()])
                            delay(1000)
                            flipOver(firstCard as ImageView,R.drawable.back)
                            flipOver(it as ImageView, R.drawable.back)
                            openCardsCount = 0
                        }
                    }
                }
                else -> Log.d("mytag", "two cards are open already") // ничего не делаем
            }
        }

        for (i in 1..16) {
            catViews.add( // вызываем конструктор для создания нового ImageView
                    ImageView(applicationContext).apply {
                        setImageResource(R.drawable.back)
                        tag = (i-1)/2
                        layoutParams = params
                        setOnClickListener(colorListener)
                    })
        }
        catViews.shuffle()

        val rows = Array(4, { LinearLayout(applicationContext)})
        var count = 0

        for (view in catViews) {
            val row: Int = count / 4
            rows[row].addView(view)
            count ++
        }

        for (row in rows) {
            row.apply{
                orientation = LinearLayout.HORIZONTAL
                layoutParams = params
            }
                layout.addView(row)
        }

        setContentView(layout)
    }

    suspend fun setBackgroundWithDelay(v: View) {
        delay(1000)
        v.setBackgroundColor(Color.YELLOW)
        delay(1000)
        v.visibility = View.INVISIBLE
        v.isClickable = false
    }


    suspend fun flipOver(v: ImageView,r: Int) {
        v.setImageResource(r)
        v.isClickable = !v.isClickable
    }

    suspend fun hide(v: ImageView) {
        v.visibility = View.INVISIBLE
        v.isClickable = false
    }
}