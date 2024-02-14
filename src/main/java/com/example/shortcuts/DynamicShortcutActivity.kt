package com.example.shortcuts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.shortcuts.databinding.ActivityDynamicShortcutBinding

class DynamicShortcutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDynamicShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.push.setOnClickListener {

            val shortcut = ShortcutInfoCompat.Builder(this, "id1")
                .setShortLabel("Website")
                .setLongLabel("Open the website")
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_shortcut))
                .setIntent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com")
                    )
                ).build()
            ShortcutManagerCompat.pushDynamicShortcut(this, shortcut)
        }

        binding.remove.setOnClickListener {
            ShortcutManagerCompat.removeDynamicShortcuts(this, listOf("id1"))
        }

        binding.removeAll.setOnClickListener {
            ShortcutManagerCompat.removeAllDynamicShortcuts(this)
        }
    }
}
