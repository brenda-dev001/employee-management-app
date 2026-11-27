package com.example.employee_management_app.data.model

data class Employee(
    val name: String = "",
    val cpf: String = "",
    val age: Int = 0,
    val salary: Double = 0.0,
    val sector: String = ""
)