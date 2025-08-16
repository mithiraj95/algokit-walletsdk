package com.michaeltchuang.wallet.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.michaeltchuang.wallet.R
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import kotlinx.serialization.Serializable

@Composable
fun AppNavigationBar(
    navController: NavController,
    displayCoreActionsBottomSheet: () -> Unit,
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    NavigationBar(
        containerColor = AlgoKitTheme.colors.tabBarBackground,
    ) {
        topLevelRoutes.forEachIndexed { _, navigationItem ->
            NavigationBarItem(
                selected = navigationItem::class.qualifiedName == currentDestination?.route,
                label = {
                    (navigationItem.type as? TopLevelRoute.Type.NavButton)?.let {
                        Text(it.label)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.details.icon),
                        contentDescription = "",
                    )
                },
                onClick = {
                    when (navigationItem.type) {
                        TopLevelRoute.Type.CircularButton -> displayCoreActionsBottomSheet()
                        is TopLevelRoute.Type.NavButton -> {
                            navController.navigate(navigationItem) {
                                popUpTo(navController.graph.findStartDestination().navigatorName) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                },
                colors =
                    NavigationBarItemDefaults.colors().copy(
                        selectedTextColor = AlgoKitTheme.colors.tabBarIconActive,
                        unselectedTextColor = AlgoKitTheme.colors.tabBarIconNonActive,
                        selectedIconColor = AlgoKitTheme.colors.tabBarIconActive,
                        unselectedIconColor = AlgoKitTheme.colors.tabBarIconNonActive,
                        selectedIndicatorColor = Color.Transparent,
                    ),
            )
        }
    }
}

data class TopLevelRouteDetails<T : Any>(
    val name: String,
    val route: T,
    @DrawableRes val icon: Int,
)

private val topLevelRoutes: List<TopLevelRoute> = listOf(Accounts, Discover)

sealed interface TopLevelRoute {
    val type: Type
    val details: TopLevelRouteDetails<*>

    sealed interface Type {
        data class NavButton(
            val label: String,
        ) : Type

        data object CircularButton : Type
    }
}

@Serializable
data object Accounts : TopLevelRoute {
    override val type: TopLevelRoute.Type = TopLevelRoute.Type.NavButton("Accounts")
    override val details =
        TopLevelRouteDetails(
            name = "Accounts",
            route = this,
            icon = R.drawable.ic_home,
        )
}

@Serializable
data object Discover : TopLevelRoute {
    override val type: TopLevelRoute.Type = TopLevelRoute.Type.NavButton("GitHub Repo")
    override val details =
        TopLevelRouteDetails(
            name = "Discover",
            route = this,
            icon = R.drawable.ic_global,
        )
}
