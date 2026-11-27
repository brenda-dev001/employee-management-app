package com.example.employee_management_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_employee")
data class EmployeeEntity(
    val name: String,
    @PrimaryKey
    val cpf: String,
    val age: Int,
    val salary: Double,
    val sector: String
)
