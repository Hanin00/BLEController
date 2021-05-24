package com.kotiln.capston

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.ToggleButton
import androidx.viewbinding.ViewBinding
import com.kotiln.capston.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val REQUEST_ENABLE_BT=1
    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if(bluetoothAdapter!=null){
            // Device doesn't support Bluetooth
            if(bluetoothAdapter?.isEnabled==false){
                //어댑터 연결 불가능할 때,
                binding.btnMainConnect.setText("연결 해제 하기")
            } else{
                binding.btnMainConnect.setText("연결 하기")
            }
        }

        binding.btnMainConnect.setOnClickListener {
            bluetoothOnOff()
        }



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
    fun bluetoothOnOff(){
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.d("bluetoothAdapter","Device doesn't support Bluetooth")
        }else{
            if (bluetoothAdapter?.isEnabled == false) { // 블루투스 꺼져 있으면 블루투스 활성화
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                binding.btnMainConnect.setText("연결 해제 하기")
            } else{ // 블루투스 켜져있으면 블루투스 비활성화
                bluetoothAdapter?.disable()
                binding.btnMainConnect.setText("연결 하기")
            }
        }
    }

}
