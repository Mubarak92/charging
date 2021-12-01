package com.example.charging

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var BeteryLevel: TextView? = null
private var batterycharching: TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BeteryLevel = findViewById(R.id.battery_level)
        batterycharching = findViewById(R.id.charging)
        val intent = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver,intent)
    }

    private val batteryBroadcastReceiver: BroadcastReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.action.BATTERY_CHANGED"){
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)

                    if (level>15){
                        BeteryLevel?.text = "BATTERY NORMAL"
                    }else BeteryLevel?.text = "BATTERY LOW"
                }
            val status: Int = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
            val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL


            if(isCharging) {
                batterycharching?.text= "Charging"
            } else {
                batterycharching?.text = "Not Charging"
            }
                }
            }





    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryBroadcastReceiver)
    }
}