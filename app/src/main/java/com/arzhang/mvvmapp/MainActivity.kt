package com.arzhang.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arzhang.mvvmapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val myViewModel = MainViewModel()
        binding.viewModel = myViewModel
        val startBtn = binding.srtBtn
        val resetBtn = binding.stpBtn
        val timer = binding.timer

       startBtn.setOnClickListener {
           if(!myViewModel.start.value) {
               startBtn.setText("STOP")
               lifecycleScope.launch {
                   repeatOnLifecycle(Lifecycle.State.STARTED) {
                       myViewModel.start()
                   }
               }
           } else {
               startBtn.setText("RESUME")
               myViewModel.stop()
           }
       }
        resetBtn.setOnClickListener {
            myViewModel.reset()
            startBtn.setText("START")
        }
    }
}
