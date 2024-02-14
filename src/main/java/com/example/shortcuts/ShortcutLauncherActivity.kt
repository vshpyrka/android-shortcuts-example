package com.example.shortcuts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shortcuts.databinding.ActivityShortcutBinding

class ShortcutLauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.staticShortcut.setOnClickListener {
            startActivity(Intent(this, StaticShortcutActivity::class.java))
        }

        binding.dynamicShortcut.setOnClickListener {
            startActivity(Intent(this, DynamicShortcutActivity::class.java))
        }

        binding.pinnedShortcut.setOnClickListener {
            startActivity(Intent(this, PinnedShortcutActivity::class.java))
        }
    }
}
