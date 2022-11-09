package com.example.dialogs_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dialogs_android.databinding.ActivityThirdBinding
import com.example.dialogs_android.ui.FirstFragment

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supportFragmentManager = supportFragmentManager.beginTransaction()
        supportFragmentManager.add(R.id.container, FirstFragment())
        supportFragmentManager.addToBackStack("")
        supportFragmentManager.commit()
    }
}