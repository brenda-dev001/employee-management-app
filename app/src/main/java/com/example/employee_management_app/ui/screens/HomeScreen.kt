package com.example.employee_management_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employee_management_app.ui.theme.Background
import com.example.employee_management_app.ui.theme.Primary
import com.example.employee_management_app.ui.theme.PrimaryLight
import com.example.employee_management_app.ui.theme.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.example.employee_management_app.ui.theme.Purple80
import com.example.employee_management_app.ui.theme.TextPrimary
import com.google.apps.card.v1.OnClick
import kotlin.contracts.contract

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(
    onInsertEmployeeClick: () -> Unit,
    viewModel: EmployeeViewModel
){

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Background),
    ) {
        TopAppBar(
            title = { Text("Employees", fontSize = 19.sp, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Primary,
                titleContentColor = Surface
            )
        )

        LaunchedEffect(Unit) {
            viewModel.getAll()
        }

        LazyColumn() {
            items(viewModel.listEmployees){ employee ->
                Column() {
                    Column(modifier = Modifier.fillMaxWidth()
                        .background(color = Surface, RoundedCornerShape(12.dp))
                        .padding(10.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Row(modifier = Modifier.size(50.dp)
                                    .background(color = Purple80, shape = RoundedCornerShape(100.dp)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(modifier = Modifier.size(35.dp),
                                        imageVector = Icons.Default.PermIdentity,
                                        contentDescription = "Person icon",
                                        tint = Primary
                                    )
                                }
                                Spacer(modifier = Modifier.size(20.dp))
                                Column() {
                                    Text(text = employee.name, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "CPF: " + employee.cpf)
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "Sector: " + employee.sector)
                                }
                            }
                            Row() {
                                Button(onClick = {},
                                    modifier = Modifier.size(50.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Primary
                                    )
                                ) {
                                    Icon(modifier = Modifier.size(20.dp),
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit icon",
                                    )
                                }

                                Button(onClick = {},
                                    modifier = Modifier.size(50.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Red
                                    )
                                ) {
                                    Icon(modifier = Modifier.size(25.dp),
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = "Remove icon"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom)
        {

            Column(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(end = 10.dp)
                    .align(alignment = Alignment.End),
                ) {
                    Button(onClick = {onInsertEmployeeClick()},
                        modifier = Modifier.size(55.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = Surface
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 3.dp
                        )

                    ) {
                        Icon(modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add icon"
                        )
                    }
                }
            }
            Spacer(Modifier.size(20.dp))
            Row(modifier = Modifier.fillMaxWidth()
                .background(color = Surface)
                .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {},
                        modifier = Modifier.width(90.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple80,
                            contentColor = Primary,
                            disabledContainerColor = Color.Transparent
                        ),
                        enabled = false
                    ) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Icon people"
                        )
                    }
                    Text(text = "Employees", color = Primary, fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {},
                        modifier = Modifier.width(90.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple80,
                            contentColor = Primary,
                            disabledContainerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudQueue,
                            contentDescription = "Icon cloud"
                        )
                    }
                    Text(text = "Backup", color = Primary, fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {},
                        modifier = Modifier.width(90.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple80,
                            contentColor = Primary,
                            disabledContainerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = "Icon statistics"
                        )
                    }
                    Text(text = "Statistics", color = Primary, fontWeight = FontWeight.Bold)
                }

            }
        }
    }
}