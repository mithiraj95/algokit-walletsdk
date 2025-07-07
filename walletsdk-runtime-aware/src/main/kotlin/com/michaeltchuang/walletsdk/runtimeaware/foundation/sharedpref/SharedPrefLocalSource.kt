package com.michaeltchuang.walletsdk.runtimeaware.foundation.sharedpref

import android.content.SharedPreferences

abstract class SharedPrefLocalSource<K>(protected val sharedPref: SharedPreferences) {

    abstract val key: String

    abstract fun getData(defaultValue: K): K

    abstract fun getDataOrNull(): K?

    abstract fun saveData(data: K)

    private var listenerList: MutableSet<OnChangeListener<K>> = mutableSetOf()

    fun clear() {
        sharedPref.edit().remove(key).apply()
        triggerListener { it.onValueChanged(null) }
    }

    fun addListener(listener: OnChangeListener<K>) {
        listenerList.add(listener)
    }

    fun removeListener(listener: OnChangeListener<K>) {
        listenerList.remove(listener)
    }

    protected fun saveData(action: (SharedPreferences.Editor) -> Unit) {
        sharedPref.edit().apply {
            action(this)
        }.apply()
        triggerListener { it.onValueChanged(getDataOrNull()) }
    }

    private fun triggerListener(action: (OnChangeListener<K>) -> Unit) {
        listenerList.forEach { action(it) }
    }

    fun interface OnChangeListener<K> {
        fun onValueChanged(value: K?)
    }
}