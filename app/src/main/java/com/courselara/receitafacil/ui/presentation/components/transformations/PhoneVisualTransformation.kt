package com.courselara.receitafacil.ui.presentation.components.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
/**
 * Máscara para telefone celular brasileiro: (XX) XXXXX-XXXX
 */
class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // (XX) XXXXX-XXXX
        val out = StringBuilder()
        for (i in text.indices) {
            when (i) {
                0 -> out.append("(").append(text[i])
                2 -> out.append(") ").append(text[i])
                7 -> out.append("-").append(text[i])
                else -> out.append(text[i])
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return offset
                if (offset <= 2) return offset + 1 // (XX
                if (offset <= 7) return offset + 3 // (XX) XXXXX
                if (offset <= 11) return offset + 4 // (XX) XXXXX-XXXX
                return 15 // Tamanho máximo da máscara
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return offset
                if (offset <= 3) return (offset - 1).coerceAtLeast(0) // (XX
                if (offset <= 9) return (offset - 3).coerceAtLeast(0) // (XX) XXXXX
                if (offset <= 15) return (offset - 4).coerceAtLeast(0) // (XX) XXXXX-XXXX
                return text.length
            }
        }

        return TransformedText(AnnotatedString(out.toString()), offsetMapping)
    }
}
