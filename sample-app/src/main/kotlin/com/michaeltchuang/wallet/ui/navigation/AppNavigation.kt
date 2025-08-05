package com.michaeltchuang.wallet.ui.navigation

import android.util.Log
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
    var showTransactionSheet = remember { mutableStateOf(false) }

    Scaffold(
        modifier =
            Modifier
                .background(color = AlgoKitTheme.colors.background)
                .fillMaxSize(),
        topBar = {
            TopBar {
                if (showTransactionSheet.value.not()){
                    showTransactionSheet.value = true
                } else {
                    showTransactionSheet.value = false
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState, modifier = Modifier)
        },
        bottomBar = {
            AlgoKitNavigationBar(navController) {
                isBottomSheetVisible.value = true
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Accounts,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            if (showTransactionSheet.value){
                Log.d("Mithi",showTransactionSheet.value.toString())
            } else {
                Log.d("Mithi",showTransactionSheet.value.toString())
            }
            getBottomNavigationGraph(navController, snackbarHostState, showTransactionSheet)
        }
    }
}
