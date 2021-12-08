package com.example.makelayout

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1.toFloat() // единичный вес

        // TODO: 3) реализовать переворот карт с "рубашки" на лицевую сторону и обратно
        val colorListener = View.OnClickListener() {
            it.setBackgroundColor(Color.YELLOW)
        }

        val catViews = ArrayList<ImageView>()

        // TODO: 2) случайным образом разместить 8 пар картинок

        for (i in 1..16) {
            catViews.add( // вызываем конструктор для создания нового ImageView
                    ImageView(applicationContext).apply {
                        setImageResource(R.drawable.squarecat)
                        layoutParams = params
                        setOnClickListener(colorListener)
                    })
        }
        val rows = Array(4, { LinearLayout(applicationContext)})
        var count = 0
        for (view in catViews) {
            val row: Int = count / 4
            rows[row].addView(view)
            count ++
        }
        for (row in rows) {
            layout.addView(row)
        }

        // TODO: 1) заполнить 4 строки элементами из массива catViews по 4 штуки в ряду
 /*
        val cat2 = ImageView(applicationContext)
        cat2.setImageResource(R.drawable.squarecat); cat2.layoutParams = params
        val cat3 = ImageView(applicationContext)
        cat3.setImageResource(R.drawable.squarecat)
        val cat4 = ImageView(applicationContext)
        //cat.layoutParams = ViewGroup.LayoutParams(applicationContext, )
        cat4.setImageResource(R.drawable.squarecat)



        val row1 = LinearLayout(applicationContext)
        row1.orientation = LinearLayout.HORIZONTAL
        row1.setBackgroundColor(Color.GRAY)
        row1.addView(cat2); row1.addView(cat);

        val row2 = LinearLayout(applicationContext)
        row2.orientation = LinearLayout.HORIZONTAL
        row2.setBackgroundColor(Color.GRAY)
        row2.addView(cat3); row2.addView(cat4);

        layout.addView(row1); layout.addView(row2)


  */
        setContentView(layout)
        //setContentView(R.layout.activity_main)
    }
}