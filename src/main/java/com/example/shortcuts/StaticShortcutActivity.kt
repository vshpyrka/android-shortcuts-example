package com.example.shortcuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Added to AndroidManifest.xml
 *
 *   <meta-data
 *      android:name="android.app.shortcuts"
 *      android:resource="@xml/shortcut_static" />
 */
class StaticShortcutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_shortcut)
    }
}
