package com.michaeltchuang.wallet.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val isBottomSheetVisible = remember { mutableStateOf(false) }

    Scaffold(
        modifier =
            Modifier
                .background(color = AlgoKitTheme.colors.background)
                .fillMaxSize(),
        topBar = {
            TopBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState, modifier = Modifier)
        },
        bottomBar = {
            AppNavigationBar(navController) {
                isBottomSheetVisible.value = true
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Accounts,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            getBottomNavigationGraph(navController, snackbarHostState)
        }
    }
}
