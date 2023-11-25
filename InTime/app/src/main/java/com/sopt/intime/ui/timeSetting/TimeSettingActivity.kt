package com.sopt.intime.ui.timeSetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.intime.R

class TimeSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_setting)
    }

    companion object {
        fun from(context: Context): Intent = Intent(context, TimeSettingActivity::class.java)
    }
}
