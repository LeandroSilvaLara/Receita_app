package com.courselara.receitafacil.ui.presentation.features.register.presentation.components

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
import com.courselara.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun RegisterContent (
    modifier: Modifier = Modifier,
    uiState: RegisterUserState,
    paddingValues: PaddingValues,
    onRegisterClick: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeated: () -> Unit,
    ) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        IconApp(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.register_text),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()

                )

                Text(
                    text = uiState.errorMessageRegisterProcess ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFOntFamily,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)

                )
            }

            RegisterContainer(
                isLoading = uiState.isLoading,
                nameValue = uiState.nameValue,
                emailValue = uiState.emailValue,
                phoneValue = uiState.phoneValue,
                passwordValue = uiState.passwordValue,
                passwordRepeatedValue = uiState.passwordRepeatedValue,
                buttonEnabled = uiState.isInputValid,
                isPasswordShown = uiState.isPasswordShow,
                isPasswordRepeatedShown = uiState.isPasswordRepeatedShow,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onButtonClick = onRegisterClick,
                onTrailingPasswordIconClick = onToggleVisualTransformationPassword,
                onTrailingPasswordRepeatedIconClick = onToggleVisualTransformationPasswordRepeated,
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
                    text = stringResource(R.string.login_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFOntFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { onNavigateToLoginScreen() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContentPreview() {
    RegisterContent(
        uiState = RegisterUserState(),
        paddingValues = PaddingValues(16.dp),
        onRegisterClick = {},
        onNameChanged = {},
        onEmailChanged = {},
        onPhoneChanged = {},
        onPasswordChanged = {},
        onPasswordRepeatedChanged = {},
        onToggleVisualTransformationPassword = {},
        onToggleVisualTransformationPasswordRepeated = {},
        onNavigateToLoginScreen = {},
    )
}