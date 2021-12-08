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
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1.toFloat() // единичный вес

        val cat = ImageView(applicationContext)
        cat.setImageResource(R.drawable.squarecat); cat.layoutParams = params
        val cat2 = ImageView(applicationContext)
        cat2.setImageResource(R.drawable.squarecat); cat2.layoutParams = params
        val cat3 = ImageView(applicationContext)
        cat3.setImageResource(R.drawable.squarecat)
        val cat4 = ImageView(applicationContext)
        //cat.layoutParams = ViewGroup.LayoutParams(applicationContext, )
        cat4.setImageResource(R.drawable.squarecat)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val row1 = LinearLayout(applicationContext)
        row1.orientation = LinearLayout.HORIZONTAL
        row1.setBackgroundColor(Color.GRAY)
        row1.addView(cat2); row1.addView(cat);

        val row2 = LinearLayout(applicationContext)
        row2.orientation = LinearLayout.HORIZONTAL
        row2.setBackgroundColor(Color.GRAY)
        row2.addView(cat3); row2.addView(cat4);

        layout.addView(row1); layout.addView(row2)
        setContentView(layout)
        //setContentView(R.layout.activity_main)
    }
}