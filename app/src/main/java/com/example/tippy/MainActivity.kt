package com.example.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

private  const val Initial_Value = 15

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            seekBar.progress = Initial_Value
        tipper.text = "$Initial_Value%"
        billamt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                computetip()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tipper.text = "$p1%"
                if(p1<15)
                    servive.text = "Bad"
                else if(p1 in 15..30)
                    servive.text = "Good"
                else
                    servive.text = "Awesome"
                computetip()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })


    }

    private fun computetip() {
        val bill = billamt.text.toString().toDouble()
        val tippercen = seekBar.progress
        val tipamount = (bill * tippercen)/100
        val totalamt = bill + tipamount
        totalview.text = totalamt.toString()
        tipview.text = tipamount.toString()

    }
}