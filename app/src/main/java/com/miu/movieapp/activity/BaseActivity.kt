package com.miu.movieapp.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun onCreateActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateActivity()
    }
}