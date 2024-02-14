package com.example.shortcuts

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.shortcuts.databinding.ActivityPinnedShortcutBinding

class PinnedShortcutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPinnedShortcutBinding

    private val shortcutPlaceBroadcastReceiver = ShortcutPlaceBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinnedShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supported = ShortcutManagerCompat.isRequestPinShortcutSupported(this)
        val maxSupported = ShortcutManagerCompat.getMaxShortcutCountPerActivity(this)
        val isRateLimitActive = ShortcutManagerCompat.isRateLimitingActive(this)
        val dynamicShortcuts = ShortcutManagerCompat.getDynamicShortcuts(this).size
        val text = "Is Pin Shortcut Supported: $supported \n" +
                "Max Static&Dynamic Shortcuts Supported: $maxSupported \n" +
                "Is Rate Limit Active: $isRateLimitActive \n" +
                "Dynamic Shortcuts Count: $dynamicShortcuts \n" +
                ""
        binding.text.text = text

        binding.requestPin.setOnClickListener {
            createShortcut()
        }

        ContextCompat.registerReceiver(
            this,
            shortcutPlaceBroadcastReceiver,
            IntentFilter("example.intent.action.SHORTCUT_CREATED"),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        binding.disable.setOnClickListener {
            updateShortcut()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(shortcutPlaceBroadcastReceiver)
    }

    private fun createShortcut() {
        val intent = Intent(this, ShortcutLauncherActivity::class.java).apply {
            action = Intent.ACTION_CREATE_SHORTCUT
        }
        val shortcutInfo = ShortcutInfoCompat.Builder(this, "pinned-shortcut")
            .setShortLabel("Pinned Shortcut")
            .setLongLabel("Pinned Custom Shortcut")
            .setIntent(intent)
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_shortcut))
            .build()

//        val pinnedShortcutCallbackIntent = ShortcutManagerCompat.createShortcutResultIntent(
//            this,
//            shortcutInfo
//        )

        val pinnedShortcutCallbackIntent = Intent("example.intent.action.SHORTCUT_CREATED")

        val successCallback = PendingIntent.getBroadcast(
            this,
            1000,
            pinnedShortcutCallbackIntent,
            FLAG_IMMUTABLE
        )

        ShortcutManagerCompat.requestPinShortcut(
            this,
            shortcutInfo,
            successCallback.intentSender
        )
    }

    private fun updateShortcut() {
        ShortcutManagerCompat.disableShortcuts(
            this,
            listOf("pinned-shortcut"),
            "Shortcut Is Disabled"
        )
    }

    inner class ShortcutPlaceBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            binding.text.text = "Pinned"
        }
    }

}
