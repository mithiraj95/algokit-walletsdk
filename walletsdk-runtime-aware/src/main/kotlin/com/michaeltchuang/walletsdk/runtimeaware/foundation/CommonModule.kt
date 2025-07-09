package com.michaeltchuang.walletsdk.runtimeaware.foundation

import com.michaeltchuang.walletsdk.runtimeaware.foundation.manager.LifecycleAwareManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.manager.LifecycleAwareManagerImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val commonModule = module {

    // Provide a Dispatcher.IO instance
    factory<CoroutineDispatcher> { Dispatchers.IO }

    // Provide LifecycleAwareManager via its implementation
    factory<LifecycleAwareManager> { LifecycleAwareManagerImpl() }
}

val delegateModule = module {
    factory { StateDelegate<Any>() } // Generic; use with type casting in ViewModel
    factory { EventDelegate<Any>() }
}
