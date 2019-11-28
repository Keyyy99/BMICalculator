package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.core.text.set
import androidx.core.widget.ImageViewCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(){
            try {
                val status: String
                val weight: Double = editTextWeight.text.toString().toDouble()
                val height: Double = editTextHeight.text.toString().toDouble()
                val bmi: Double = calculateBMI(weight, height)

                when {
                    bmi < 18.5 -> {
                        imageViewProfile.setImageResource(R.drawable.under)
                        status = "Under"
                    }
                    bmi < 25 -> {
                        imageViewProfile.setImageResource(R.drawable.normal)
                        status = "Normal"
                    }
                    else -> {
                        imageViewProfile.setImageResource(R.drawable.over)
                        status = "Over"
                    }
                }
                bmiResult.text = "BMI: %.2f".format(bmi, status)
            }catch(e:Exception){
                val toast:Toast = Toast.makeText(applicationContext,"Weight or height cannot be empty!",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }
        }

        buttonReset.setOnClickListener(){
            editTextWeight.setText(null)
            editTextHeight.setText(null)
            bmiResult.setText(null)
            imageViewProfile.setImageResource(R.drawable.empty)
        }
    }

        private fun calculateBMI(weight:Double,height:Double):Double{
        return weight/ pow(height,2.0)
    }

}
