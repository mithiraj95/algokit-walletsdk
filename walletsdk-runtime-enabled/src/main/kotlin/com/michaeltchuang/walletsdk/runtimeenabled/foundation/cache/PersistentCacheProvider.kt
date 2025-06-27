package com.michaeltchuang.walletsdk.runtimeenabled.foundation.cache

import java.lang.reflect.Type

interface PersistentCacheProvider {
    fun <T : Any> getPersistentCache(type: Type, key: String): PersistentCache<T>
}
