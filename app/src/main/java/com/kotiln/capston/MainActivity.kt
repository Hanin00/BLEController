package com.kotiln.capston

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotiln.capston.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

   


    companion object {
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetoothSocket: BluetoothSocket? = null
        lateinit var m_progress: ProgressDialog
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_address: String
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {


                // 세로모드 코드 적용
            }
            Configuration.ORIENTATION_LANDSCAPE -> { // 가로모드 코드 적용
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnMainConnect
        m_address = intent.getStringExtra(SelectDeviceActiviy.EXTRA_ADDRESS).toString()


        btn_main_connect.setOnClickListener {
            disconnect()
            val intent = Intent(this, SelectDeviceActiviy::class.java)
            startActivity(intent)
            finish()
        }

        btn_main_go.setOnClickListener {
            txt_main_clicked.setText("앞으로")
            sendCommand("GO")
        }
        btn_main_back.setOnClickListener {
            txt_main_clicked.setText("뒤로")
            sendCommand("BACK")
        }
        btn_main_left.setOnClickListener {
            txt_main_clicked.setText("왼쪽으로")
            sendCommand("LEFT")
        }
        btn_main_right.setOnClickListener {
            txt_main_clicked.setText("오른쪽으로")
            sendCommand("RIGHT")
        }
        btn_main_stop.setOnClickListener {
            txt_main_clicked.setText("멈춤")
            sendCommand("STOP")
            //Toast.makeText(this, "btn_clicked_Stop", Toast.LENGTH_SHORT).show()
        }


    }

    //ble 모듈에 String 전송
    private fun sendCommand(input: String) {
        if (m_bluetoothSocket != null) {
            try {
                //연결이 됭 있으면 출력
                m_bluetoothSocket!!.outputStream.write(input.toByteArray())
                Log.d("Test", input)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    //ble 연결 해제
    private fun disconnect() {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        finish()
    }

    private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String>() {
        private var connectSuccess: Boolean = true
        private val context: Context

        init {
            this.context = c
        }

        override fun onPreExecute() {
            super.onPreExecute()
            m_progress = ProgressDialog.show(context, "Connecting...", "please wait")
        }

        override fun doInBackground(vararg params: Void?): String? {
            try {
                if (m_bluetoothSocket == null || !m_isConnected) {
                    m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(m_address)
                    m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    m_bluetoothSocket!!.connect()
                }
            } catch (e: IOException) {
                connectSuccess = false
                e.printStackTrace()
            }
            return null
        }

        //ble 연결 지원 기기인지 확인
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!connectSuccess) {
                //연결 불가
                Log.d("Test", "연결 가능기기 아님 / coudln't connect")
            } else {
                //연결 가능
                m_isConnected = true
                Log.d("Test", "연결 가능기기 / Can connect")
            }
            m_progress.dismiss()
        }
    }


}
