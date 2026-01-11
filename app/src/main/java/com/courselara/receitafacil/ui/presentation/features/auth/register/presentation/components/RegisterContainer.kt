package com.courselara.receitafacil.ui.presentation.features.auth.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.presentation.components.button.AuthButton
import com.courselara.receitafacil.ui.presentation.components.textfield.TextEntryModule
import com.courselara.receitafacil.ui.presentation.components.transformations.PhoneVisualTransformation
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun RegisterContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    nameValue: String,
    emailValue: String,
    phoneValue: String,
    passwordValue: String,
    passwordRepeatedValue: String,
    isPasswordShown: Boolean,
    buttonEnabled: Boolean,
    isPasswordRepeatedShown: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTrailingPasswordIconClick: () -> Unit,
    onTrailingPasswordRepeatedIconClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_name_recipe_text),
            hint = stringResource(R.string.hint_name_recipe_text),
            leadingIcon = Icons.Default.Person,
            textValue = nameValue,
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChange = onNameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textValue = emailValue,
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChange = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_phone_text),
            hint = stringResource(R.string.hint_phone_text),
            leadingIcon = Icons.Default.PhoneAndroid,
            textValue = phoneValue,
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            imeAction = ImeAction.Next,
            onValueChange = {
                if (it.length <= 11) {
                    onPhoneChanged(it)
                }
            },
            keyboardType = KeyboardType.Phone,
            visualTransformation = PhoneVisualTransformation(),
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue,
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            onValueChange = onPasswordChanged,
            visualTransformation = if (isPasswordShown) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingPasswordIconClick
        )

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_repeat_password_text),
            hint = stringResource(R.string.hint_repeat_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordRepeatedValue,
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            onValueChange = onPasswordRepeatedChanged,
            visualTransformation = if (isPasswordRepeatedShown) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingPasswordRepeatedIconClick
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                text = stringResource(R.string.register_button_text),
                contentColor = Color.White,
                enabled = buttonEnabled,
                onButtonClick = onButtonClick,
                isLoading = isLoading
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {
    ReceitaFacilAppTheme {
        RegisterContainer(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            nameValue = "",
            emailValue = "",
            phoneValue = "",
            passwordValue = "",
            passwordRepeatedValue = "",
            buttonEnabled = false,
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onPasswordRepeatedChanged = {},
            onButtonClick = {},
            isPasswordShown = false,
            isPasswordRepeatedShown = false,
            onTrailingPasswordIconClick = {},
            onTrailingPasswordRepeatedIconClick = {},
            isLoading = false
        )
    }
}
