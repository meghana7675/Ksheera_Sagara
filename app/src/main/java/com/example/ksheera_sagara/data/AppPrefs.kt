package com.example.ksheera_sagara.data

import android.content.Context

class AppPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    // Language
    fun setLanguage(lang: String) {
        prefs.edit().putString("language", lang).apply()
    }

    fun getLanguage(): String {
        return prefs.getString("language", "English") ?: "English"
    }

    // Font size
    fun setFontSize(size: Float) {
        prefs.edit().putFloat("font_size", size).apply()
    }

    fun getFontSize(): Float {
        return prefs.getFloat("font_size", 16f)
    }

    // App lock PIN
    fun setPin(pin: String) {
        prefs.edit().putString("pin", pin).apply()
    }

    fun getPin(): String? {
        return prefs.getString("pin", null)
    }

    fun setLockEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("lock", enabled).apply()
    }

    fun isLockEnabled(): Boolean {
        return prefs.getBoolean("lock", false)
    }

    // Milk price
    fun setMilkPrice(price: Float) {
        prefs.edit().putFloat("milk_price", price).apply()
    }

    fun getMilkPrice(): Float {
        return prefs.getFloat("milk_price", 50f)
    }
}