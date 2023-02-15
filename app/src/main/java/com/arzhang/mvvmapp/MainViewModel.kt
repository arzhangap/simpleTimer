package com.arzhang.mvvmapp
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _time = MutableStateFlow(0)
    val time: StateFlow<Int>
        get() = _time

    var start = MutableStateFlow(false)
    suspend fun start() {
        start.value = true
        while(start.value) {
            _time.update { second ->
                second + 1
            }
            delay(1000)
        }
    }

    fun stop() {
        start.value = false
    }

    fun formatTime(seconds: Int): String {
        return String.format("%02d:%02d:%02d", seconds / 3600,
            (seconds % 3600) / 60, (seconds % 60))
    }

    fun reset() {
        stop()
        _time.update {
            0
        }
    }

}