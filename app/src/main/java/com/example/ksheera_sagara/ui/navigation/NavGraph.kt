package com.example.ksheera_sagara.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.ksheera_sagara.ui.screens.*
import com.example.ksheera_sagara.viewmodel.MainViewModel
import com.example.ksheera_sagara.ui.screens.ReportsScreen
import com.example.ksheera_sagara.ui.screens.SettingsScreen
@Composable
fun NavGraph(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("dashboard") {
            DashboardScreen(viewModel)
        }

        composable("income") {
            IncomeScreen(navController, viewModel)
        }

        composable("expense") {
            AddExpenseScreen(navController, viewModel)
        }

        composable("reports") {

            ReportsScreen(
                viewModel = viewModel
            )
        }

        composable("addCow") {
            AddCowScreen(navController, viewModel)
        }
        composable("cowProfit") {
            CowProfitScreen(viewModel)
        }
        composable("summary") {
            SummaryScreen(navController, viewModel)
        }
        composable("monthly_summary") {
            MonthlySummaryScreen(viewModel)
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}