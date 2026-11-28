package com.example.employee_management_app.data.repository

import com.example.employee_management_app.data.model.Employee
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EmployeeRepository(
    private val db: FirebaseFirestore
) {
    suspend fun insertEmployee(employee: Employee){
        db.collection("employees")
            .document(employee.cpf)
            .set(employee)
            .await()
    }

    suspend fun searchEmployees(search: String) : List<Employee>{
        val list =  db.collection("employees")
            .get()
            .await()
            .toObjects(Employee::class.java)

        return if(search.isEmpty()){
            list
        }else{
            list.filter { employee ->
                employee.cpf.contains(search) ||
                employee.sector.contains(search, ignoreCase = true) ||
                employee.name.contains(search, ignoreCase = true)
            }
        }
    }
}