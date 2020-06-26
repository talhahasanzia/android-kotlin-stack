package com.example.kotlinstack.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotlinstack.R
import com.example.kotlinstack.home.vm.DefaultHomeViewModel
import com.example.kotlinstack.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels<DefaultHomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Just to check activity and fragment are using same viewmodel instance
        viewModel.weatherUpdate
            .observe(this, Observer { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() })
    }
}