package com.example.employee_management_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employee_management_app.data.model.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity)

    @Query("SELECT * FROM employees")
    suspend fun getAll() : List<EmployeeEntity>
}