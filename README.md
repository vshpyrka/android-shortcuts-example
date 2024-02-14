# android-shortcuts-example

Example of Android Shortcuts APIs

* [StaticShortcutActivity.kt](https://github.com/vshpyrka/android-shortcuts-example/blob/main/src/main/java/com/example/shortcuts/StaticShortcutActivity.kt) - Example of Static Shortcuts that are declared on the app level in xml resource file configuration

https://github.com/vshpyrka/android-shortcuts-example/assets/2741602/692fd67a-063b-4dd5-bdc7-874c35ac8974

Example:
```
  // res/xml/shortcuts.xml

  <shortcuts xmlns:android="http://schemas.android.com/apk/res/android">

    <shortcut
        android:enabled="true"
        android:icon="@drawable/ic_shortcut"
        android:shortcutDisabledMessage="@string/shortcut_static_compose_disabled"
        android:shortcutId="compose"
        android:shortcutLongLabel="@string/shortcut_static_compose_long"
        android:shortcutShortLabel="@string/shortcut_static_compose_short">

        <intent
            android:action="android.intent.action.VIEW"
            android:targetClass="com.example.shortcuts.StaticShortcutActivity"
            android:targetPackage="com.example.myapplication" />

        <categories android:name="android.shortcut.conversation" />

        <capability-binding android:key="actions.intent.CREATE_MESSAGE" />

    </shortcut>
```
* [DynamicShortcutActivity.kt](https://github.com/vshpyrka/android-shortcuts-example/blob/main/src/main/java/com/example/shortcuts/DynamicShortcutActivity.kt) - Example of Dynamic Shortcuts then can be set and removed furing the app runtime

https://github.com/vshpyrka/android-shortcuts-example/assets/2741602/3191a806-4c13-40a5-9662-60cedf5871a3

Example:
```
  val shortcut = ShortcutInfoCompat.Builder(this, "SHORTCUT_UNIQUE_IDENTIFIER")
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
```

* [PinnedShortcutActivity.kt](https://github.com/vshpyrka/android-shortcuts-example/blob/main/src/main/java/com/example/shortcuts/PinnedShortcutActivity.kt) - Example of Pinned Shortcut that helps to create desktop shortcut that launches specific intent, and disable specific shortcut

https://github.com/vshpyrka/android-shortcuts-example/assets/2741602/9eac2aae-80bb-47a2-a79c-6691896add62

Example:
```
  // Create Pinned Shortcut

  val intent = Intent(this, ShortcutLauncherActivity::class.java).apply {
            action = Intent.ACTION_CREATE_SHORTCUT
  }
  val shortcutInfo = ShortcutInfoCompat.Builder(this, "SHORTCUT_UNIQUE_IDENTIFIER")
      .setShortLabel("Pinned Shortcut")
      .setLongLabel("Pinned Custom Shortcut")
      .setIntent(intent)
      .setIcon(IconCompat.createWithResource(this, R.drawable.ic_shortcut))
      .build()

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

  // Disable Pinned Shortcut

  ShortcutManagerCompat.disableShortcuts(
      this,
      listOf("SHORTCUT_UNIQUE_IDENTIFIER"),
      "Shortcut Is Disabled"
  )

```

More information about Android [Shortcuts](https://developer.android.com/develop/ui/views/launch/shortcuts)
