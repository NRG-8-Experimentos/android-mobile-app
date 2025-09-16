package com.example.synhub.shared.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synhub.shared.application.dto.SignUpRequest
import com.example.synhub.shared.icons.abcSVG
import com.example.synhub.shared.icons.linkSVG
import com.example.synhub.shared.icons.lockSVG
import com.example.synhub.shared.icons.mailSVG
import com.example.synhub.shared.icons.personSVG
import com.example.synhub.shared.viewmodel.LogInViewModel
import com.example.synhub.shared.viewmodel.RegisterViewModel
import kotlin.math.log

@Composable
fun Register(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF)
    ){
            innerPadding -> RegisterScreen(modifier =Modifier.padding(innerPadding), nav)
    }
}

@Composable
fun RegisterScreen(modifier: Modifier, nav: NavHostController){

    var txtName by remember { mutableStateOf("") }
    var txtSurname by remember { mutableStateOf("") }
    var txtUser by remember { mutableStateOf("") }
    var txtMail by remember { mutableStateOf("") }
    var txtUrlPfp by remember { mutableStateOf("") }
    var txtPass1 by remember { mutableStateOf("") }
    var txtPass2 by remember { mutableStateOf("") }

    val registerViewModel : RegisterViewModel = viewModel()
    val logInViewModel: LogInViewModel = viewModel()
    val signUpResult by registerViewModel.signUpResult.collectAsState()
    val loginSuccess by logInViewModel.loginSuccess.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Register",
            fontSize = 60.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            OutlinedTextField(
                value = txtName,
                singleLine = true,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Nombre") },
                placeholder = { Text(text = "Nombre") },
                leadingIcon = {
                    Icon(
                        imageVector = abcSVG,
                        tint = Color.Gray,
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3F3F3),
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Cyan
                ),
                onValueChange = { txtName = it }
            )

            OutlinedTextField(
                value = txtSurname,
                singleLine = true,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Apellido") },
                placeholder = { Text(text = "Apellido") },
                leadingIcon = {
                    Icon(
                        imageVector = abcSVG,
                        tint = Color.Gray,
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3F3F3),
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Cyan
                ),
                onValueChange = { txtSurname = it }
            )
        }

        OutlinedTextField(
            value = txtUser,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Usuario") },
            placeholder = { Text(text = "Usuario") },
            leadingIcon = {
                Icon(
                    imageVector = personSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = { txtUser = it }
        )
        OutlinedTextField(
            value = txtMail,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Mail") },
            placeholder = { Text(text = "Mail") },
            leadingIcon = {
                Icon(
                    imageVector = mailSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = { txtMail = it }
        )
        OutlinedTextField(
            value = txtUrlPfp,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Url Pfp") },
            placeholder = { Text(text = "Url Pfp") },
            leadingIcon = {
                Icon(
                    imageVector = linkSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = { txtUrlPfp = it }
        )
        OutlinedTextField(
            value = txtPass1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = lockSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = { txtPass1 = it }
        )
        OutlinedTextField(
            value = txtPass2,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = lockSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = { txtPass2 = it }
        )

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(0xFF4A90E2)),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                if(txtPass1 != txtPass2) {
                    // Show error message for password mismatch
                    Log.d("Register", "Passwords do not match")
                }else{
                    Log.d("Register", "Attempting to sign up with user: $txtUser")
                    registerViewModel.signUp(
                        SignUpRequest(
                            username = txtUser,
                            name = txtName,
                            surname = txtSurname,
                            imgUrl = txtUrlPfp,
                            email = txtMail,
                            password = txtPass1
                        )
                    )
                }
            }
        ) {
            Text(
                text = "Registrarse", fontSize = 20.sp,
                color = Color.White, fontWeight = FontWeight.Bold
            )
        }

        if(signUpResult?.body()?.username == txtUser){
            logInViewModel.signIn(txtUser, txtPass1)
        }

        if (loginSuccess == true){
            logInViewModel.resetLoginState()
            nav.navigate("Home") {
                popUpTo("register") { inclusive = true }
            }
        }
    }
}