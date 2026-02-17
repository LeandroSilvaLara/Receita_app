package com.courselara.receitafacil.ui.presentation.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.courselara.receitafacil.ui.presentation.components.UiModePreviews
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@UiModePreviews
@Composable
private fun ActionButtonPreview() {
    ReceitaFacilAppTheme {
        AuthButton(
            modifier = Modifier,
            text = "Login",
            isLoading = true,
            contentColor = Color.White,
            onButtonClick = {}
        )
    }
}