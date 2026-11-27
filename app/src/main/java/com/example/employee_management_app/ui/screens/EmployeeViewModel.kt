package com.example.employee_management_app.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_management_app.data.model.Employee
import com.example.employee_management_app.data.repository.EmployeeRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch

class EmployeeViewModel(
    val repository: EmployeeRepository
) : ViewModel(){

    var message by mutableStateOf("")
        private set

    var listEmployees by mutableStateOf<List<Employee>>(emptyList())
        private set

    fun insertEmployee(employee: Employee){
        viewModelScope.launch {
            try {
                repository.insertEmployee(employee)
                message = "Employee registered!"

            }catch (e: FirebaseFirestoreException){
                Log.e("EmployeeViewModel", "Firestore error!", e)
            }catch (e: Exception){
                Log.e("EmployeeViewModel", "Unexpected error!", e)
            }
        }
    }

    fun getAll(){
        viewModelScope.launch {
            try {
                listEmployees = repository.getAll()
            }catch(e: FirebaseFirestoreException){
                Log.e("EmployeeViewModel", "Firestore error!", e)
            }catch (e: Exception){
                Log.e("EmployeeViewModel", "Unexpected error!", e)
            }
        }
    }

    fun clearMessage(){
        message = ""
    }
}