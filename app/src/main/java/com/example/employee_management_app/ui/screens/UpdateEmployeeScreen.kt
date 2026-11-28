package com.example.employee_management_app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employee_management_app.ui.theme.Primary
import com.example.employee_management_app.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEmployeeScreen(
    onBackClick: () -> Unit,
    viewModel: EmployeeViewModel
){

    val employee = viewModel.employee
    var name by remember { mutableStateOf(employee?.name ?: "") }
    var age by remember { mutableStateOf(employee?.age.toString() ?: "") }
    var salary by remember { mutableStateOf(employee?.salary.toString() ?: "") }
    var expanded by remember { mutableStateOf(false) }
    var sector by remember { mutableStateOf(employee?.sector ?: "") }
    val context = LocalContext.current
    val sectors = listOf(
        "IT",
        "Human Resources",
        "Finance",
        "Marketing",
        "Sales"
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Surface)
        .verticalScroll(rememberScrollState()),

        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Primary)
            .padding(5.dp)
        ) {
            Button(onClick = {onBackClick()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Surface
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "icon arrow back"
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))

        Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Update employee", fontSize = 19.sp, color = Primary, fontWeight = FontWeight.Bold)
            OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),
                value = name,
                onValueChange = { name = it },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(text = "Your name") },
                label = {Text("Name")}
            )

            OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),
                value = employee?.cpf ?: "",
                onValueChange = {  },
                shape = RoundedCornerShape(20.dp),
                label = {Text("CPF")},
                readOnly = true
            )

            OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),
                value = age,
                onValueChange = { age = it },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(text = "Your age") },
                label = {Text("Age")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),
                value = salary,
                onValueChange = { salary = it },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(text = "Your salary") },
                label = {Text("Salary")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {expanded = it}
            ) {
                OutlinedTextField(
                    value = sector,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text(text = "Sector")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier
                        .menuAnchor(
                            ExposedDropdownMenuAnchorType.PrimaryNotEditable
                        )
                        .fillMaxWidth(.9f),
                    shape = RoundedCornerShape(20.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sectors.forEach { selectedSector ->
                        DropdownMenuItem(
                            text = { Text(selectedSector) },
                            onClick = {
                                sector = selectedSector
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(modifier = Modifier.fillMaxWidth()
            .padding(end = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Button(onClick = {
                if(name.isNotEmpty() &&
                    age.isNotEmpty() &&
                    salary.isNotEmpty()
                ){
                    viewModel.employee?.let { employee ->
                        val newAge = age.toIntOrNull()
                        val newSalary = salary.toDoubleOrNull()

                        if(newAge != null && newSalary != null){
                            val employeeUpdate = employee.copy(name = name, age = newAge, salary = newSalary, sector = sector)
                            viewModel.updateEmployee(employeeUpdate)
                        }
                    }

                }
            },
                modifier = Modifier.width(120.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Surface,
                ),
                elevation = ButtonDefaults.buttonElevation(3.dp)
            ) {
                Text(text = "Update", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
        }
        LaunchedEffect(viewModel.message) {
            if(viewModel.message.isNotEmpty()){
                Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                viewModel.clearMessage()
                onBackClick.invoke()
            }
        }
    }
}