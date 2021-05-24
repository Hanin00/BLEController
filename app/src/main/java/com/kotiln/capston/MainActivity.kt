package com.kotiln.capston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.kotiln.capston.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        btn_main_go.setOnClickListener {
            txt_main_clicked.setText("앞으로")
            Log.d("Test","Clicked btn_Stop")
        }
        btn_main_back.setOnClickListener {
            txt_main_clicked.setText("뒤로")
            Log.d("Test","Clicked btn_Stop")
        }
        btn_main_left.setOnClickListener {
            txt_main_clicked.setText("왼쪽으로")
            Log.d("Test","Clicked btn_Stop")
        }
        btn_main_right.setOnClickListener {
            txt_main_clicked.setText("오른쪽으로")
            Log.d("Test","Clicked btn_Stop")
        }
        btn_main_stop.setOnClickListener {
            txt_main_clicked.setText("멈춤")
            Log.d("Test","Clicked btn_Stop")
            //Toast.makeText(this, "btn_clicked_Stop", Toast.LENGTH_SHORT).show()
        }


    }
}