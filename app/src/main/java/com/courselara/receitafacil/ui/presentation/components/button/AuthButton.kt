package com.courselara.receitafacil.ui.presentation.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean,
    contentColor: Color,
    enabled: Boolean= true,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onButtonClick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray
        ),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFOntFamily,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun AuthButtonPreview() {
    ReceitaFacilAppTheme {
        AuthButton(
            text = "Login",
            isLoading = true,
            contentColor = Color.White,
            onButtonClick = {}
        )
    }
}