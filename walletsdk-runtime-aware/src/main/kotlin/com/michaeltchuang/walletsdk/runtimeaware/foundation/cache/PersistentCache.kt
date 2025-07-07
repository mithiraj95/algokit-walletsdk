package com.michaeltchuang.walletsdk.runtimeaware.foundation.cache

interface PersistentCache<T> {
    fun put(data: T)
    fun get(): T?
    fun clear()
}
