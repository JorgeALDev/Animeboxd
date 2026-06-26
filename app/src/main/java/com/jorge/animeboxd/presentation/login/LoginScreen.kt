package com.jorge.animeboxd.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.animeboxd.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Animeboxd",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = White,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(0.5.dp, SurfaceBorder, RoundedCornerShape(8.dp))
                .background(SurfaceCard, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (usuario.isEmpty()) {
                Text(
                    text = "Usuário",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextDim)
                )
            }
            BasicTextField(
                value = usuario,
                onValueChange = { usuario = it },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(0.5.dp, SurfaceBorder, RoundedCornerShape(8.dp))
                .background(SurfaceCard, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (senha.isEmpty()) {
                Text(
                    text = "Senha",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextDim)
                )
            }
            BasicTextField(
                value = senha,
                onValueChange = { senha = it },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (erro != null) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = erro!!,
                style = MaterialTheme.typography.bodySmall.copy(color = GreenDone),
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(Modifier.height(24.dp))

        AniButton(
            label = "Entrar",
            filled = true,
            onClick = {
                if (usuario == "admin" && senha == "admin") {
                    erro = null
                    onLoginSuccess()
                } else {
                    erro = "Usuário ou senha inválidos"
                }
            },
            modifier = Modifier.fillMaxWidth(0.6f)
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = "Use: admin / admin",
            style = MaterialTheme.typography.labelSmall.copy(color = TextDim)
        )
    }
}