package com.example.otherwork.common

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    companion object {
        private const val PREF = "MyAppPrefName"
        private const val IS_ADMIN = "IS_ADMIN"

    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveIsAdmin(isAdmin: Boolean) {
        put(IS_ADMIN, isAdmin)
    }

    fun getIsAdmin(): Boolean {
        return get(IS_ADMIN, Boolean::class.java)
    }

    fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }


}