package com.example.employee_management_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.employee_management_app.data.repository.EmployeeRepository
import com.example.employee_management_app.ui.navigation.AppNavigation
import com.example.employee_management_app.ui.screens.EmployeeViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = FirebaseFirestore.getInstance()
        val repository = EmployeeRepository(db)
        employeeViewModel = EmployeeViewModel(repository)

        setContent {
            AppNavigation(employeeViewModel)
        }
    }
}

