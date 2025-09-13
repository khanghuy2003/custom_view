package com.example.learncustomview

import android.content.Intent
import com.example.learncustomview.bases.BaseActivity
import com.example.learncustomview.databinding.ActivityMainBinding
import com.example.learncustomview.exercise1.Exercise1Activity
import com.example.learncustomview.exercise2.Exercise2Activity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initActionView() {
        binding.btn1.setOnClickListener {
            startActivity(Intent(this@MainActivity, Exercise1Activity::class.java))
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity, Exercise2Activity::class.java))
        }
    }
}