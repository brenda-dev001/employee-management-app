package com.example.employee_management_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employee_management_app.ui.screens.AddEmployeeScreen
import com.example.employee_management_app.ui.screens.EmployeeViewModel
import com.example.employee_management_app.ui.screens.HomeScreen
import com.example.employee_management_app.ui.screens.UpdateEmployeeScreen

@Composable
fun AppNavigation(
    viewModel: EmployeeViewModel
){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME){
            HomeScreen(
                onInsertEmployeeClick = {
                    navController.navigate(Routes.INSERT)
                },
                onUpdateEmployeeClick = {
                    navController.navigate(Routes.UPDATE)
                },
                viewModel = viewModel
            )
        }

        composable(Routes.INSERT){
            AddEmployeeScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }

        composable(Routes.UPDATE){
            UpdateEmployeeScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}