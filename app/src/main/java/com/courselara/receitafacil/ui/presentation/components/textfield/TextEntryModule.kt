package com.courselara.receitafacil.ui.presentation.components.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    description: String,
    hint: String,
    textColor: Color,
    cursorColor: Color,
    leadingIcon: ImageVector,
    textValue: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    trailingIcon: ImageVector?= null,
    onTrailingIconClick: (() -> Unit)? = {}
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = poppinsFOntFamily,
                textAlign = TextAlign.Start
            ),
            label = {
                Text(
                    text = description,
                    color = textColor,
                    fontFamily = poppinsFOntFamily
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    fontFamily = poppinsFOntFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Icone do text field",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Icone do text field",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable {
                            if (onTrailingIconClick != null) {
                                onTrailingIconClick()
                            }
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                imeAction = imeAction,
            ),
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = cursorColor,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextEntryModulePreview() {
    ReceitaFacilAppTheme {
        TextEntryModule(
            description = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 5.dp),
            hint = "Digite seu email",
            leadingIcon = Icons.Default.Email,
            textValue = "123456",
            textColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChange = {},
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClick = {},
            visualTransformation = PasswordVisualTransformation()


        )
    }
}