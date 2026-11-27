package com.example.employee_management_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employee_management_app.ui.screens.AddEmployeeScreen
import com.example.employee_management_app.ui.screens.HomeScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME){
            HomeScreen(
                onInsertEmployeeClick = {
                    navController.navigate(Routes.INSERT)
                }
            )
        }

        composable(Routes.INSERT){
            AddEmployeeScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}