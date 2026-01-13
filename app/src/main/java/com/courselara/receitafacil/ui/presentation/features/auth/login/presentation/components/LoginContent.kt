package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.presentation.components.iconapp.IconApp
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    paddingValues: PaddingValues,
    onLoginClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onNavigatorToRegisterScreens: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        IconApp(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.login_text),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = uiState.errorMessageLoginProcess.toString(),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LoginContainer(
                isLoading = false,
                emailValue = uiState.emailValue,
                passwordValue = uiState.passwordValue,
                buttonEnabled = uiState.isInputValid,
                isPasswordShown = uiState.isPasswordShow,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onLoginButtonClick = onLoginClick,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier.padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.no_have_account_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFOntFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = stringResource(R.string.register_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFOntFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { onNavigatorToRegisterScreens() }
                )
            }
        }
    }
}

@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginContentPreview() {
    ReceitaFacilAppTheme {
        LoginContent(
          paddingValues = PaddingValues(),
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigatorToRegisterScreens = {},
            onToggleVisualTransformationPassword = {},
            uiState = LoginUiState()
        )
    }
}