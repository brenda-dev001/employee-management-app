package com.example.employee_management_app.data.repository

import com.example.employee_management_app.data.local.EmployeeDao
import com.example.employee_management_app.data.local.EmployeeEntity
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

    suspend fun getAll() : List<Employee>{
        return db.collection("employees")
            .get()
            .await()
            .toObjects(Employee::class.java)
    }
}