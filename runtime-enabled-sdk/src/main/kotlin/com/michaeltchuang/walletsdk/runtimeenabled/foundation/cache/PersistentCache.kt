package com.michaeltchuang.walletsdk.runtimeenabled.foundation.cache

interface PersistentCache<T> {
    fun put(data: T)
    fun get(): T?
    fun clear()
}
