package com.example.ea2appmoviles.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ea2appmoviles.R

@Composable
fun Inicio(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Futbolito Chile",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.futbol_chileno),
            contentDescription = "Fútbol Chileno",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("lista_equipos/Primera División")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Primera División", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("lista_equipos/Primera B")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Primera B", fontSize = 16.sp)
        }
    }
}
