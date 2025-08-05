package com.michaeltchuang.wallet.ui.navigation

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.michaeltchuang.wallet.ui.screens.AccountListScreen
import com.michaeltchuang.wallet.ui.screens.DiscoverScreen
import com.michaeltchuang.wallet.ui.screens.SettingsScreen
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackBarLayout
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackbarViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.getBottomNavigationGraph(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    composable<Accounts> {
        val backStackEntry = remember(it) { navController.getBackStackEntry<Accounts>() }
        val sharedViewModel: SnackbarViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)
        AccountListScreen(
            tag = backStackEntry.toRoute<Accounts>().details.name,
            navController = navController,
            snackbarViewModel = sharedViewModel
        )
        SnackBarLayout(sharedViewModel, snackbarHostState)
    }
    composable<Discover> {
        val backStackEntry = remember(it) { navController.getBackStackEntry<Discover>() }
        val sharedViewModel: SnackbarViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)
        DiscoverScreen(
            tag = backStackEntry.toRoute<Discover>().details.name,
            navController = navController,
            snackbarViewModel = sharedViewModel,
        )
        SnackBarLayout(sharedViewModel, snackbarHostState)
    }

    composable<Settings> {
        val backStackEntry = remember(it) { navController.getBackStackEntry<Settings>() }
        val sharedViewModel: SnackbarViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)
        SettingsScreen(
            tag = backStackEntry.toRoute<Settings>().details.name,
            navController = navController,
            snackbarViewModel = sharedViewModel,
        )
        SnackBarLayout(sharedViewModel, snackbarHostState)
    }
}
