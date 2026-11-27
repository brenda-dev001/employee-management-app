package com.example.employee_management_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmployeeEntity::class], version = 1, exportSchema = false)
abstract class EmployeeDataBase : RoomDatabase(){

    abstract fun EmployeeDao() : EmployeeDao
}