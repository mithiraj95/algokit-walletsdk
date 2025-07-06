package com.michaeltchuang.walletsdk.runtimeaware.cache

import android.content.SharedPreferences
import com.google.gson.Gson
import com.michaeltchuang.walletsdk.runtimeaware.foundation.cache.FlowPersistentCache
import com.michaeltchuang.walletsdk.runtimeaware.foundation.cache.PersistentCache
import com.michaeltchuang.walletsdk.runtimeaware.foundation.cache.PersistentCacheProvider
import java.lang.reflect.Type

internal class PersistentCacheProviderImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PersistentCacheProvider {

    override fun <T : Any> getPersistentCache(type: Type, key: String): PersistentCache<T> {
        return SharedPrefPersistentCache(type, key, sharedPreferences, gson)
    }

    override fun <T : Any> getFlowPersistentCache(
        type: Type,
        key: String,
        defaultValue: T
    ): FlowPersistentCache<T> {
        return DefaultFlowPersistentCache(
            SharedPrefPersistentCache(type, key, sharedPreferences, gson),
            defaultValue
        )
    }
}
